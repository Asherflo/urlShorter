package com.asher.urlshorter.repository;

import com.asher.urlshorter.model.UrlDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface  UrlDetailsRepository extends MongoRepository<UrlDetails,String> {

   Optional<UrlDetails>  findUrlDetailsByShortenedUrl(String shortenedUrl);
}


