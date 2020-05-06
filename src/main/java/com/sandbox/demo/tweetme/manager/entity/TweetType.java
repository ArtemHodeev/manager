package com.sandbox.demo.tweetme.manager.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TweetType {
    ORIGINAL("ORIGINAL", 0),
    COMMENT("COMMENT", 1),
    RETWEET("RETWEET", 2);

    private final String name;
    private final int value;

    TweetType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @JsonCreator
    public static TweetType forValues(@JsonProperty("name") String name, @JsonProperty("value") int value) {
        return TweetType.valueOf(name);

    }
}
