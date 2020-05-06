package com.sandbox.demo.tweetme.manager.listener;

import com.sandbox.demo.tweetme.manager.entity.Tweet;
import com.sandbox.demo.tweetme.manager.service.TweetService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModifyQueueRabbitListener {
    @Autowired
    TweetService tweetService;

    @RabbitListener(queues = {"modifyQueue"})
    public void receiveTweet(Tweet tweet) {
        tweetService.updateTweet(tweet);
    }
}
