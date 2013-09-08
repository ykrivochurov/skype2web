package ru.ykey.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ykey.dao.FeedItemRepository;
import ru.ykey.model.FeedItem;
import ru.ykey.service.IFeedItemService;

import java.util.List;

/**
 * User: yurikrivochurov
 * Date: 9/7/13
 */
@Service
@Transactional
public class FeedItemService implements IFeedItemService {

    @Autowired
    private FeedItemRepository feedItemRepository;

    @Override
    public List<FeedItem> findByChatAndDate(Long chatId, Long date) {
        Assert.notNull(chatId);
        Assert.notNull(date);
        DateTime dayStartDateTime = new DateTime(date).withTime(0, 0, 0, 0);
        DateTime dayEndDateTime = new DateTime(date).withTime(23, 59, 59, 0);
        return feedItemRepository.findByChatIdAndCreationDateBetweenOrderByCreationDateDesc(chatId,
                dayStartDateTime.toDate(), dayEndDateTime.toDate());
    }
}
