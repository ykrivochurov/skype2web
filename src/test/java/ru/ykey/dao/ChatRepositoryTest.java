package ru.ykey.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ykey.config.BaseConfiguration;
import ru.ykey.config.PersistenceJPAConfig;
import ru.ykey.config.PropHolderConfig;
import ru.ykey.model.Chat;

import java.util.Date;

/**
 * User: yurikrivochurov
 * Date: 9/7/13
 */
@ContextConfiguration(classes = {BaseConfiguration.class, PersistenceJPAConfig.class, PropHolderConfig.class})
public class ChatRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private ChatRepository chatRepository;

    @Test
    public void testSimple() throws Exception {
        Chat chat = new Chat();
        chat.setChatId("test id");
        chat.setCreationDate(new Date());
        chat = chatRepository.save(chat);
        Assert.assertNotNull(chat.getId());
    }
}
