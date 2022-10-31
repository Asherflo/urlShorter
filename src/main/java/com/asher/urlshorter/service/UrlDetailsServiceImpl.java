package com.asher.urlshorter.service;

import com.asher.urlshorter.dto.requests.UrlRequest;
import com.asher.urlshorter.dto.responses.UrlDetailsResponse;
import com.asher.urlshorter.exception.UrlNotFoundException;
import com.asher.urlshorter.exception.UrlShortenerException;
import com.asher.urlshorter.model.UrlDetails;
import com.asher.urlshorter.repository.UrlDetailsRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class UrlDetailsServiceImpl implements UrlDetailsService{

    @Autowired
    private UrlDetailsRepository urlDetailsRepository;
    @Override
    public UrlDetailsResponse generateShortUrl(UrlRequest urlRequest) throws UrlShortenerException {
       if(!isValid(urlRequest.getUrl())){
           throw  new UrlShortenerException("inValid url!");
       }
       String encodedUrl = encodedUrl(urlRequest.getUrl());
        UrlDetails urlDetails = new UrlDetails();
        urlDetails.setOriginalUrl(urlRequest.getUrl());
        urlDetails.setShortenedUrl(encodedUrl);
        urlDetails.setCreationDate(LocalDateTime.now());
        urlDetailsRepository.save(urlDetails);
        UrlDetailsResponse response = new UrlDetailsResponse();
        response.setOriginalUrl(urlDetails.getOriginalUrl());
        response.setShortenedUrl(urlDetails.getShortenedUrl());
        return response;
    }

    @Override
    public String updateShortenedUrl(String url, String customUrl) throws UrlShortenerException {
        UrlDetails urlDetails = urlDetailsRepository.findUrlDetailsByShortenedUrl(url).orElseThrow(()-> new UrlNotFoundException("Url  not found"));
        if(!isValid(customUrl)){
            throw  new UrlShortenerException("Invalid url");
        }
        urlDetails.setShortenedUrl(customUrl);
        urlDetailsRepository.save(urlDetails);
        return urlDetails.getShortenedUrl();
    }

    @Override
    public String getEncodedUrl(String originalUrl) throws UrlNotFoundException {
        return null;
    }

    @Override
    public String getDecodedUrl(String shortLink) throws UrlNotFoundException {
        return null;
    }

    @Override
    public void deleteUrlUsingShortenedUrl(String shortenedUrl) throws UrlNotFoundException {

    }

    @Override
    public void deleteUrlUsingOriginalUrl(String originalUrl) throws UrlNotFoundException {

    }

    @Override
    public int size() {
        return 0;
    }
    private String encodedUrl(String url){
        LocalDateTime time = LocalDateTime.now();
        return Hashing.murmur3_32().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString();
    }
    private boolean isValid(String url){
        String regex =  "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();

    }
}
