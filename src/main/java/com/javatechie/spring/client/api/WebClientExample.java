package com.javatechie.spring.client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/bookMyShow-client")
public class WebClientExample {
    WebClient webClient;

    @Autowired
    GenericRestClient genericRestClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder().baseUrl("http://localhost:9090/bookMyShow")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    @PostMapping("/bookNow")
    public Mono<String> BookNow(@RequestBody BookRequest request) {
        return webClient.post().uri("/book").syncBody(request).retrieve().bodyToMono(String.class);
    }

    @GetMapping("/trackBookings")
    public Flux<BookRequest> trackAllBooking() {
        return webClient.get().uri("/getAllBooking").retrieve().bodyToFlux(BookRequest.class);
    }

    @GetMapping("/trackBooking/{bookingId}")
    public Mono<BookRequest> getBookingById(@PathVariable int bookingId) {
        return webClient.get().uri("/getBooking/" + bookingId).retrieve().bodyToMono(BookRequest.class);
    }

    @DeleteMapping("/removeBooking/{bookingId}")
    public Mono<String> cancelBooking(@PathVariable int bookingId) {
        return webClient.delete().uri("/cancelBooking/" + bookingId).retrieve().bodyToMono(String.class);
    }

    @PutMapping("/changeBooking/{bookingId}")
    public Mono<BookRequest> updateBooking(@PathVariable int bookingId, @RequestBody BookRequest request) {
        return webClient.put().uri("/updateBooking/" + bookingId).syncBody(request).retrieve()
                .bodyToMono(BookRequest.class);
    }
/*    private String url = "";
    @GetMapping("/getAllBookings")
    public Flux<BookRequest> getAllBooking() {
       // return webClient.get().uri("/getAllBooking").retrieve().bodyToFlux(BookRequest.class);
        WebClient.ResponseSpec responseSpec = WebClient.builder().build()
                .post()
                .uri("http://localhost:9090/bookMyShow/getAllBooking")
                //.headers(httpHeaders -> httpHeaders.addAll(generateHeaders(headers)))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .retrieve();
        responseSpec.

    }*/
    String url = "http://localhost:9090/bookMyShow/book";
    @PostMapping("/bookNow2")
    public BookRequest bookNow3(@RequestBody BookRequest request) {
        ResponseEntity<String> responseEntity = genericRestClient.execute(url, HttpMethod.POST, request,
                String.class, HttpHeaders.EMPTY);
        System.out.println("response "+responseEntity.getBody());
        System.out.println("response "+responseEntity.getStatusCode());
        return request;
    }

}
