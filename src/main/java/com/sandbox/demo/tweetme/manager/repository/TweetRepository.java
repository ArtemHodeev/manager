package com.sandbox.demo.tweetme.manager.repository;

import com.sandbox.demo.tweetme.manager.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
