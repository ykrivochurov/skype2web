package ru.ykey.dao;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ykey.config.BaseConfiguration;
import ru.ykey.config.PersistenceJPAConfig;
import ru.ykey.config.PropHolderConfig;
import ru.ykey.model.Chat;
import ru.ykey.model.ChatDate;

import java.util.Date;

/**
 * User: yurikrivochurov
 * Date: 9/8/13
 */
@ContextConfiguration(classes = {BaseConfiguration.class, PersistenceJPAConfig.class, PropHolderConfig.class})
public class ChatDateRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private ChatDateRepository chatDateRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Test
    public void test() throws Exception {
        Chat chat = new Chat();
        chat.setChatId("test id");
        chat.setCreationDate(new Date());
        chat = chatRepository.save(chat);

        DateTime dayStartDateTime = new DateTime().withTime(0, 0, 0, 0);
        ChatDate chatDate = new ChatDate();
        chatDate.setChat(chat);
        chatDate.setDate(dayStartDateTime.toDate());
        chatDate.setCount(0);
        chatDateRepository.save(chatDate);

        chatDate = chatDateRepository.findByDateAndChatId(dayStartDateTime.toDate(), chat.getId());
        Assert.assertNotNull(chatDate);
    }
}
