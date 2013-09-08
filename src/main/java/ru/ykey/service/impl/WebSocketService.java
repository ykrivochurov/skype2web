package ru.ykey.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.websocket.WsOutbound;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.ykey.model.Chat;
import ru.ykey.model.FeedItem;
import ru.ykey.service.IWebSocketService;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * User: yurikrivochurov
 * Date: 9/8/13
 */
@Service
public class WebSocketService implements IWebSocketService {

    private static Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Map<Long, List<WsOutbound>> connections = new HashMap<>();

    @Override
    public void send(Chat chat, FeedItem feedItem) throws JsonProcessingException {
        logger.debug(" >> send to socket " + feedItem.getText());
        List<WsOutbound> chatListeners = connections.get(chat.getId());
        if (CollectionUtils.isEmpty(chatListeners)) {
            return;
        }
        Iterator<WsOutbound> iterator = chatListeners.iterator();
        while (iterator.hasNext()) {
            WsOutbound chatListener = iterator.next();
            CharBuffer buffer = CharBuffer.wrap(OBJECT_MAPPER.writeValueAsString(feedItem));
            try {
                chatListener.writeTextMessage(buffer);
            } catch (IOException e) {
                iterator.remove();
            }
        }
    }

    @Override
    public void receive(WsOutbound wsOutbound, String message) {
        Long chatId = Long.valueOf(message);
        List<WsOutbound> chatListeners = connections.get(chatId);
        if (chatListeners == null) {
            chatListeners = new ArrayList<>();
            connections.put(chatId, chatListeners);
        }
        chatListeners.add(wsOutbound);
    }
}