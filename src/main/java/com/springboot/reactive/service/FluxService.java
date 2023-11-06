package com.springboot.reactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

@Service
public class FluxService {

    public void fluxTestingService() {
        System.out.println("Flux testing service");
    }

    public Flux<String> getFlux() {
        return Flux.just("Ram", "Ankit", "Imran", "Chaitali", "Utkarsh");
    }

    public Flux<String> fruitsFlux() {
        List<String> fruitsNames = List.of("mango", "Apple", "Banana", "Gowava");
        return Flux.fromIterable(fruitsNames).log();
    }

    public Flux<Void> getBlankFlux() {
        return Flux.empty();
    }

    public Flux<String> mapExampleFlux() {
        Flux<String> capFlux = getFlux().map(name -> name.toUpperCase()).log();
        return capFlux;
    }

    public Flux<String> filterLengthFlux() {
        return getFlux().filter(name -> name.length() > 4).log();
    }

    public Flux<String> flatMap() {
        return getFlux().flatMap(name -> Flux.just(name.split("")))
                .delayElements(Duration.ofSeconds(2));
        // return getFlux().flatMap(name->Flux.just("Test Flux")).log();
    }

    public Flux transformExample() {
        Function<Flux<String>, Flux<String>> funInterface = (name) -> name.map(String::toUpperCase);
        return getFlux().transform(funInterface).log();
    }

    //defalutIfEmpty
    //switchIfEmpty()
    public Flux<String> ifExample(int length) {
        return getFlux()
                .filter(name -> name.length() > length)
                // .defaultIfEmpty("Learn code with Ramjeet")
                .switchIfEmpty(fruitsFlux())
                .log();
    }

    // concat(static) concat(instance)
    public Flux<String> concatExample() {
        //return Flux.concat(getFlux(),fruitsFlux());
        return getFlux()
                .delayElements(Duration.ofSeconds(1))
                .concatWith(fruitsFlux()
                        .delayElements(Duration.ofSeconds(3)));
    }

    public Flux<String> mergeWithExample() {
        return Flux.merge(getFlux()
                .delayElements(Duration.ofSeconds(1)), fruitsFlux()
                .delayElements(Duration.ofSeconds(2)));

    }
}
