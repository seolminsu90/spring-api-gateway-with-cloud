package com.api.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

    @GetMapping("/greeting")
    public Mono<String> greeting(){
        return Mono.just("Hello GateWay! Iam Api Server~");
    }
}
