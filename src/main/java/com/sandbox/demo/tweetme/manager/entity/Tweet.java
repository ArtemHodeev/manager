package com.sandbox.demo.tweetme.manager.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tweet")
public class Tweet {
    @SequenceGenerator(name = "tweetme_id_seq",
            sequenceName = "tweetme_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tweetme_id_seq")
    @Id
    private Long id;

    @Column(name = "body")
    private String body;

    @Column(name = "creation_name")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "type")
    private TweetType tweetType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public TweetType getTweetType() {
        return tweetType;
    }

    public void setTweetType(TweetType tweetType) {
        this.tweetType = tweetType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Tweet)) return false;

        Tweet tweet = (Tweet) o;

        return new EqualsBuilder()
                .append(id, tweet.id)
                .append(creationDate, tweet.creationDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(creationDate)
                .toHashCode();
    }
}
