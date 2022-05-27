package com.javatechie.spring.client.api;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class EndpointInvokerClient {

    public <T> Mono<ClientResponse> post(String url, T request, Map<String,String> headers,long timeoutInSeconds) {
        Mono<ClientResponse> response =  WebClient.builder().build()
                .post()
                .uri(url)
                .headers(httpHeaders -> httpHeaders.addAll(generateHeaders(headers)))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange();
       //         .timeout(Duration.ofSeconds(timeoutInSeconds));
        return response;
    }

    private MultiValueMap<String,String> generateHeaders(Map<String,String> headers){
        MultiValueMap<String,String> headerMap = new LinkedMultiValueMap<>();
        headers.entrySet().stream()
                .forEach(e -> headerMap.add(e.getKey(),e.getValue()));
        return headerMap;
    }

}