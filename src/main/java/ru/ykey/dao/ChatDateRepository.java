package ru.ykey.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ykey.model.ChatDate;

import java.util.Date;
import java.util.List;

/**
 * User: yurikrivochurov
 * Date: 9/7/13
 */
public interface ChatDateRepository extends PagingAndSortingRepository<ChatDate, Long> {

    ChatDate findByDateAndChatId(Date date, Long chatId);

    List<ChatDate> findByChatId(Long chatId);

}