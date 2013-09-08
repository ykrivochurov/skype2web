package ru.ykey.service.impl;

import com.skype.ChatMessage;
import com.skype.ChatMessageAdapter;
import com.skype.Skype;
import com.skype.SkypeException;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ykey.dao.ChatDateRepository;
import ru.ykey.dao.ChatRepository;
import ru.ykey.dao.FeedItemRepository;
import ru.ykey.model.Chat;
import ru.ykey.model.ChatDate;
import ru.ykey.model.FeedItem;
import ru.ykey.service.ISkypeService;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * User: yurikrivochurov
 * Date: 9/7/13
 */
@Service
@Transactional
public class SkypeService implements ISkypeService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private FeedItemRepository feedItemRepository;

    @Autowired
    private ChatDateRepository chatDateRepository;

    @PostConstruct
    public void init() throws SkypeException {
        Skype.addChatMessageListener(new ChatMessageAdapter() {
            public void chatMessageReceived(ChatMessage received) throws SkypeException {
                if (received.getType().equals(ChatMessage.Type.SAID)) {
                    Chat chat = createChatIfNotExists(received);
                    createFeedItem(received, chat);
                    createChatDateIfNotExists(chat);
                    System.out.println(received);
                }
            }
        });
    }

    //todo сделать отсылку сообщения сразу в webSocket
    //todo прасить текст запроса
    //todo check if message Edited!!
    //todo спец сообщение в чат для плюсования на сайте и тп
    private Chat createChatIfNotExists(ChatMessage chatMessage) throws SkypeException {
        String chatId = chatMessage.getChat().getId();
        Chat chat = chatRepository.findByChatId(chatId);
        if (chat == null) {
            chat = new Chat();
            chat.setCreationDate(new Date());
            chat.setChatId(chatId);
            chat.setWindowTitle(chatMessage.getChat().getWindowTitle());
            chat = chatRepository.save(chat);
        }
        return chat;
    }

    private FeedItem createFeedItem(ChatMessage chatMessage, Chat chat) throws SkypeException {
        FeedItem feedItem = new FeedItem();
        feedItem.setCreationDate(new Date());
        feedItem.setCreator(StringUtils.isNotBlank(chatMessage.getSenderDisplayName()) ?
                chatMessage.getSenderDisplayName() : chatMessage.getSender().getId());
        feedItem.setText(chatMessage.getContent());
        feedItem.setChat(chat);
        feedItem.setMessageId(chatMessage.getId());
        return feedItemRepository.save(feedItem);
    }

    private ChatDate createChatDateIfNotExists(Chat chat) {
        DateTime dayStartDateTime = new DateTime().withTime(0, 0, 0, 0);
        ChatDate chatDate = chatDateRepository.findByDateAndChatId(dayStartDateTime.toDate(), chat.getId());
        if (chatDate == null) {
            chatDate = new ChatDate();
            chatDate.setChat(chat);
            chatDate.setDate(dayStartDateTime.toDate());
            chatDate.setCount(0);
        } else {
            chatDate.setCount(chatDate.getCount() + 1);
        }
        return chatDateRepository.save(chatDate);
    }
}