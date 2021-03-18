package com.adiaconitei.codechallenge.tweet.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TweetOutResource {

    @JsonProperty("id")
    private String id;

    @JsonProperty("user")
    private String user;

    @JsonProperty("content")
    private String content;

    @JsonProperty("location")
    private String location;

    @JsonProperty("valid")
    private Boolean valid;

}
