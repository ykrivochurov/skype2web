package ru.ykey.service;

import ru.ykey.model.FeedItem;

import java.util.Date;
import java.util.List;

/**
 * User: yurikrivochurov
 * Date: 9/7/13
 */
public interface IFeedItemService {

    List<FeedItem> findByChatAndDate(Long chatId, Long date);

}
