package com.adiaconitei.codechallenge.tweet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class TweetService {

    @Value("${tweet.languages}")
    private List<String> languages;

    @Value("${tweet.min-followers}")
    private int minFollowers;

    private final Twitter twitter;
    private final TweetRepository repository;
    private final TweetMapper mapper;


    public TweetService(TweetRepository repository,
                        TweetMapper mapper) {
        this.twitter = TwitterFactory.getSingleton();
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostConstruct
    private void init () throws TwitterException {
        List<Status> rawTweets = twitter.getHomeTimeline();
        List<Tweet> tweets = rawTweets.stream()
                .filter(tweet -> languages.contains(tweet.getLang()))
                .filter(tweet -> tweet.getUser().getFollowersCount() > minFollowers)
                .map(mapper::toEntity)
                .collect(Collectors.toList());
        repository.saveAll(tweets);
    }

    public List<Tweet> getTweets(boolean valid) {
        if (valid)
            return repository.findAllByValid(valid);
        return repository.findAll();
    }

    public Tweet validTweet(String id) {
        Tweet tweet = repository.getOne(id);
        tweet.setValid(true);
        return repository.save(tweet);
    }

}
