package com.asher.urlshorter.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UrlDetailsResponse {
    private String originalUrl;
    private String shortenedUrl;
}
