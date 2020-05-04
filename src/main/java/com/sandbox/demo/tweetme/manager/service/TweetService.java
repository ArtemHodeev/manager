package com.sandbox.demo.tweetme.manager.service;

import com.sandbox.demo.tweetme.manager.entity.Tweet;
import com.sandbox.demo.tweetme.manager.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {
    @Autowired
    TweetRepository tweetRepository;

    public List<Tweet> getAll() {
        return tweetRepository.findAll();
    }

    public Tweet updateTweet(Tweet tweet) {
        Tweet savedTweet = null;
        if (tweet.getId() == null) {
            savedTweet = tweetRepository.save(tweet);
        } else {
            String err = String.format("Could not find tweet #%d",tweet.getId());
            savedTweet = tweetRepository.findById(tweet.getId())
                    .orElseThrow(() -> new IllegalArgumentException(err));
            savedTweet.setBody(tweet.getBody());
            tweetRepository.save(savedTweet);
        }
        return savedTweet;
    }
}
