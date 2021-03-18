package com.adiaconitei.codechallenge.tweet.web;

import com.adiaconitei.codechallenge.tweet.Tweet;
import com.adiaconitei.codechallenge.tweet.TweetMapper;
import com.adiaconitei.codechallenge.tweet.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    private TweetService service;

    @Autowired
    private TweetMapper mapper;

    @GetMapping
    public ResponseEntity<List<TweetOutResource>> getTweets(
            @RequestParam(name = "valid", required = false) boolean valid
    ) {
        List<Tweet> searchResult = service.getTweets(valid);
        List<TweetOutResource> response = mapper.toResource(searchResult);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/valid")
    public ResponseEntity<TweetOutResource> validTweet(
            @PathVariable String id
    ) {
        Tweet udpatedTweet = service.validTweet(id);
        TweetOutResource response = mapper.toResource(udpatedTweet);
        return ResponseEntity.ok(response);
    }

}
