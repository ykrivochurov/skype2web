package ru.ykey.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ykey.model.FeedItem;

import java.util.Date;
import java.util.List;

/**
 * User: yurikrivochurov
 * Date: 9/6/13
 */
public interface FeedItemRepository extends PagingAndSortingRepository<FeedItem, Long> {

    List<FeedItem> findByChatIdAndCreationDateBetweenOrderByCreationDateDesc(Long chatId, Date startDate, Date endDate);

}
