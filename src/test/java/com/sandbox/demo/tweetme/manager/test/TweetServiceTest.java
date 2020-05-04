package com.sandbox.demo.tweetme.manager.test;

import com.sandbox.demo.tweetme.manager.entity.Tweet;
import com.sandbox.demo.tweetme.manager.repository.TweetRepository;
import com.sandbox.demo.tweetme.manager.service.TweetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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
    public void addTest() {
        Tweet t1 = new Tweet();
        Tweet t2 =  new Tweet();

        t1.setBody("First");
        t2.setBody("Second");

        Tweet updatedT1 = tweetServiceTest.updateTweet(t1);
        Tweet updatedT2 = tweetServiceTest.updateTweet(t2);
        List<Tweet> all = tweetServiceTest.getAll();
        assertThat(all.size()).isEqualTo(2);
    }
}
