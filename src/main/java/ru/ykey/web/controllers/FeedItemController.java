package ru.ykey.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ykey.dao.FeedItemRepository;
import ru.ykey.model.FeedItem;
import ru.ykey.service.IFeedItemService;

import java.util.Date;

/**
 * User: yurikrivochurov
 * Date: 9/7/13
 */
@Controller
@RequestMapping("/api/feedItem")
public class FeedItemController {

    @Autowired
    private IFeedItemService feedItemService;

    @Autowired
    private FeedItemRepository feedItemRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<FeedItem> getFeedItems(@RequestParam("chatId") Long chatId,
                                           @RequestParam("date") Long date) {
        return feedItemService.findByChatAndDate(chatId, date);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public FeedItem getFeedItem(@PathVariable("id") Long id) {
        return feedItemRepository.findOne(id);
    }

}
