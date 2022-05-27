package com.javatechie.spring.client.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class GenericRestClient  {

    private RestTemplate restTemplate;

    public <T, V> ResponseEntity<V> execute(String url, HttpMethod httpMethod, T data,
                     Class<V> genericClass, HttpHeaders headers)  {
        HttpEntity<T> entity = new HttpEntity<T>(data, headers);
        return restTemplate.exchange(url, httpMethod, entity, genericClass);
    }

}
