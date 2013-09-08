package ru.ykey.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ykey.model.Chat;

/**
 * User: yurikrivochurov
 * Date: 9/7/13
 */
public interface ChatRepository extends PagingAndSortingRepository<Chat, Long> {

    Chat findByChatId(String chatId);

}
