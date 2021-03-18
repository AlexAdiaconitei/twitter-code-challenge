package com.adiaconitei.codechallenge.trend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twitter4j.Trend;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrendService {

    private final Twitter twitter;

    public TrendService() {
        this.twitter = TwitterFactory.getSingleton();
    }

    public List<String> getTrends(int placeId, int limit) throws TwitterException {
        return Arrays.stream(twitter.trends().getPlaceTrends(placeId).getTrends())
                .limit(limit).map(Trend::getName)
                .collect(Collectors.toList());
    }

}
