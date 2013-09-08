package ru.ykey.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.catalina.websocket.WsOutbound;
import ru.ykey.model.Chat;
import ru.ykey.model.FeedItem;

/**
 * User: yurikrivochurov
 * Date: 9/8/13
 */
public interface IWebSocketService {
    void send(Chat chat, FeedItem feedItem) throws JsonProcessingException;

    void receive(WsOutbound wsOutbound, String message);
}
