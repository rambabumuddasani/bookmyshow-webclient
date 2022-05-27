package com.javatechie.spring.client.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

@Getter
@Setter
@Builder
public class RequestDetails {
    private String url;
    private HttpMethod requestType;
}
