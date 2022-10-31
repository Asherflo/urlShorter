package com.asher.urlshorter.service;

import com.asher.urlshorter.dto.requests.UrlRequest;
import com.asher.urlshorter.dto.responses.UrlDetailsResponse;
import com.asher.urlshorter.exception.UrlNotFoundException;
import com.asher.urlshorter.exception.UrlShortenerException;

public interface UrlDetailsService {
    UrlDetailsResponse generateShortUrl(UrlRequest urlRequest)throws UrlShortenerException;
    String updateShortenedUrl(String url,String customUrl)throws UrlShortenerException;
    String getEncodedUrl(String originalUrl) throws UrlNotFoundException;
    String getDecodedUrl(String shortLink) throws UrlNotFoundException;
    void deleteUrlUsingShortenedUrl(String shortenedUrl) throws UrlNotFoundException;
    void deleteUrlUsingOriginalUrl(String originalUrl) throws UrlNotFoundException;
    int size();

}
