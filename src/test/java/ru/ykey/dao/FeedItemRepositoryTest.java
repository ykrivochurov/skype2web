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
import ru.ykey.model.FeedItem;

import java.util.Date;
import java.util.List;

/**
 * User: yurikrivochurov
 * Date: 9/6/13
 */
@ContextConfiguration(classes = {BaseConfiguration.class, PersistenceJPAConfig.class, PropHolderConfig.class})
public class FeedItemRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private FeedItemRepository feedItemRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Test
    public void testSimple() throws Exception {
        Chat chat = new Chat();
        chat.setCreationDate(new Date());
        chat.setChatId("test chat");
        chat = chatRepository.save(chat);

        FeedItem feedItem = new FeedItem();
        feedItem.setText("test text");
        feedItem.setCreationDate(new Date());
        feedItem.setCreator("author");
        feedItem.setChat(chat);

        feedItem = feedItemRepository.save(feedItem);
        Assert.assertNotNull(feedItem.getId());
    }

    @Test
    public void testItemsForDate() throws Exception {
        Date date = new Date();
        DateTime dayStartDateTime = new DateTime(date).withTime(0, 0, 0, 0);
        DateTime dayEndDateTime = new DateTime(date).withTime(23, 59, 59, 0);

        Chat chat = new Chat();
        chat.setCreationDate(new Date());
        chat.setChatId("test chat");
        chat = chatRepository.save(chat);

        FeedItem feedItem = new FeedItem();
        feedItem.setText("test text");
        feedItem.setCreationDate(new Date());
        feedItem.setCreator("author");
        feedItem.setChat(chat);

        feedItem = feedItemRepository.save(feedItem);
        List<FeedItem> items = feedItemRepository.
                findByChatIdAndCreationDateBetweenOrderByCreationDateDesc(chat.getId(),
                        dayStartDateTime.toDate(), dayEndDateTime.toDate());
        Assert.assertEquals(items.size(), 1);
    }
}
