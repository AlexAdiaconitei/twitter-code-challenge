package com.adiaconitei.codechallenge.tweet;

import com.adiaconitei.codechallenge.tweet.web.TweetOutResource;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TweetMapper {

    public TweetOutResource toResource(Tweet entity) {
        TweetOutResource resource = new TweetOutResource();
        resource.setId(entity.getId());
        resource.setUser(entity.getUser());
        resource.setContent(entity.getContent());
        resource.setLocation(entity.getLocation());
        resource.setValid(entity.getValid());
        return resource;
    }

    public Tweet toEntity(Status resource) {
        Tweet entity = new Tweet();
        entity.setUser(resource.getUser().getName());
        entity.setContent(resource.getText());
        entity.setLocation(resource.getUser().getLocation());
        entity.setValid(false);
        return entity;
    }

    public List<TweetOutResource> toResource(List<Tweet> entities) {
        return entities.stream().map(this::toResource).collect(Collectors.toList());
    }

}
