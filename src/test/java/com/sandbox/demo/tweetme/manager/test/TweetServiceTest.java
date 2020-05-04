package com.sandbox.demo.tweetme.manager.test;

import com.sandbox.demo.tweetme.manager.entity.Tweet;
import com.sandbox.demo.tweetme.manager.entity.TweetType;
import com.sandbox.demo.tweetme.manager.service.TweetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


@DataJpaTest
@ExtendWith(SpringExtension.class)
public class TweetServiceTest {
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public TweetService tweetServiceTest() {
            return new TweetService();
        }
    }

    @Autowired
    TweetService tweetServiceTest;

    @Test
    public void addNewTweetsTest() {
        Tweet t1 = new Tweet();
        Tweet t2 =  new Tweet();

        t1.setBody("First");
        t2.setBody("Second");

        tweetServiceTest.updateTweet(t1);
        tweetServiceTest.updateTweet(t2);
        List<Tweet> all = tweetServiceTest.getAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    public void updateExistedTweetTest() {
        Tweet t1 = new Tweet();

        t1.setBody("Before");
        t1.setTweetType(TweetType.ORIGINAL);
        Tweet originalTweet = tweetServiceTest.updateTweet(t1);

        originalTweet.setBody("After");
        Tweet updatedTweet = tweetServiceTest.updateTweet(originalTweet);

        assertThat(originalTweet.getId()).isEqualTo(updatedTweet.getId());
        assertThat(originalTweet.getCreationDate()).isEqualTo(updatedTweet.getCreationDate());
        assertThat(originalTweet.getTweetType()).isEqualTo(updatedTweet.getTweetType());
        assertThat("After").isEqualTo(updatedTweet.getBody());
    }

    @Test
    public void getMissedTweet() {
        assertThat(tweetServiceTest.getById(1L)).isNull();
    }

    @Test
    public void deleteExistedTweet() {
        Tweet t1 = new Tweet();

        t1.setBody("Before");
        t1.setTweetType(TweetType.ORIGINAL);
        Tweet originalTweet = tweetServiceTest.updateTweet(t1);

        assertThat(tweetServiceTest.deleteById(originalTweet.getId())).isEqualTo(originalTweet);
    }

    @Test
    public void deleteUnrealTweet() {
        Long unrealId = 2L;
        String err = String.format("Tweet #%d does not exist", unrealId);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            tweetServiceTest.deleteById(unrealId);
        }).withMessage(err);
    }
}
