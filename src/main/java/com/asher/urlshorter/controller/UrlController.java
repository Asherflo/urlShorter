package com.asher.urlshorter.controller;

import com.asher.urlshorter.dto.requests.UrlRequest;
import com.asher.urlshorter.exception.UrlNotFoundException;
import com.asher.urlshorter.exception.UrlShortenerException;
import com.asher.urlshorter.service.UrlDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/url")
public class UrlController {
    @Autowired
    private UrlDetailsService urlDetailsService;

    @PostMapping("/getLink")
    public ResponseEntity<?> getShortLinkOfUrl(@RequestBody UrlRequest request) {
        try {
            return new ResponseEntity<>(urlDetailsService.generateShortUrl(request), HttpStatus.OK);
        } catch (UrlShortenerException ex) {

            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortUrl){
        try{
            return  new ResponseEntity<>(urlDetailsService.getDecodedUrl(shortUrl), HttpStatus.FOUND);
        } catch (UrlShortenerException e) {
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.NOT_ACCEPTABLE);
        }

    }
}