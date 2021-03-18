package com.adiaconitei.codechallenge.trend.web;

import com.adiaconitei.codechallenge.trend.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

import java.util.List;

@RestController
@RequestMapping("/trends")
public class TrendController {

    @Autowired
    private TrendService service;

    @GetMapping
    public ResponseEntity<List<String>> getTrends (
            @RequestParam(name="place_id", required=false, defaultValue="1") int placeId,
            @RequestParam(name="limit", required=false, defaultValue="10") int limit
    ) throws TwitterException {
        List<String> response = service.getTrends(placeId, limit);
        return ResponseEntity.ok(response);
    }

}
