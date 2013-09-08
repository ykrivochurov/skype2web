package ru.ykey.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ykey.dao.ChatDateRepository;
import ru.ykey.dao.ChatRepository;
import ru.ykey.model.Chat;
import ru.ykey.model.ChatDate;

import java.util.List;

/**
 * User: yurikrivochurov
 * Date: 9/7/13
 */
@Controller
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatDateRepository chatDateRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Chat> getChats() {
        return chatRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Chat getChat(@PathVariable("id") Long id) {
        return chatRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}/dates", method = RequestMethod.GET)
    @ResponseBody
    public List<ChatDate> getChatDates(@PathVariable("id") Long id) {
        return chatDateRepository.findByChatId(id);
    }


}