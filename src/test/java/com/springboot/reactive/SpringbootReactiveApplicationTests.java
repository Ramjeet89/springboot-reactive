package com.springboot.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Consumer;

@SpringBootTest
class SpringbootReactiveApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    public void workingWithMono1() {
        //Mono -----> publisher that have 0 ....1 items
        Mono<String> errorMono = Mono.error(new RuntimeException("Error !!"));
        Mono<String> m1 = Mono.just("Learn code with Ram")
                .log()
                .then(errorMono);

        //consume the mono by subscribing
        m1.subscribe(System.out::print);
        errorMono.subscribe(System.out::println);
    }

    @Test
    public void workingWithMono2() {

        Mono<String> m1 = Mono.just("Learn code with Ram");
        Mono<String> m2 = Mono.just("Subscribe to this channel");

        Mono<Tuple2<String, String>> combinedMono = Mono.zip(m1, m2);
        combinedMono.subscribe(data -> {
            System.out.println(data);
            System.out.println(data.getT1());
            System.out.println(data.getT2());
        });
    }

    @Test
    public void workingWithMono3() {

        Mono<String> m1 = Mono.just("Learn code with Ram");
        Mono<String> m2 = Mono.just("Subscribe to this channel");
        Mono<Integer> m3 = Mono.just(54321);

        Mono<Tuple3<String, String, Integer>> combinedMono = Mono.zip(m1, m2, m3);
        combinedMono.subscribe(data -> {
            System.out.println(data);
            System.out.println(data.getT1());
            System.out.println(data.getT2());
            System.out.println(data.getT3());
        });
    }

    @Test
    public void workingWithMono4() {
        Mono<String> m1 = Mono.just("Learn code with Ram");
        Mono<String> m2 = Mono.just("Subscribe to this channel");

        Mono<Tuple2<String, String>> zipWithMono = m1.zipWith(m2);
        zipWithMono.subscribe(data -> {
            System.out.println(data);
            System.out.println(data.getT1());
            System.out.println(data.getT2());
        });
    }

    @Test
    public void workingWithMono5() {
        Mono<String> m1 = Mono.just("Learn code with Ram");
        Mono<String> m2 = Mono.just("Subscribe to this channel");
        Mono<Integer> m3 = Mono.just(54321);

        Mono<String> resultMapMono = m1.map(String::toUpperCase);
        resultMapMono.subscribe(System.out::println);

    }

    @Test
    public void workingWithMono6() {
        Mono<String> m1 = Mono.just("Learn code with Ram");
        Mono<String> m2 = Mono.just("Subscribe to this channel");
        Mono<Integer> m3 = Mono.just(54321);

        Mono<String[]> resultFlatExample = m1.flatMap(valueM1 -> Mono.just(valueM1.split(" ")));
        resultFlatExample.subscribe(data -> {
            for (String s : data) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void workingWithFlux1() {
        Mono<String> m1 = Mono.just("Learn code with Ram");
        Flux<String> stringFlux = m1.flatMapMany(valueM1 -> Flux.just(valueM1.split(" "))).log();
        stringFlux.subscribe(System.out::println);
    }

    @Test
    public void workingWithMono7() {
        Mono<String> m1 = Mono.just("Learn code with Ram");
        Mono<String> m2 = Mono.just("Subscribe to this channel");
        Mono<Integer> m3 = Mono.just(54321);

        Flux<String> stringFlux = m1.concatWith(m2).log();
        stringFlux.subscribe(System.out::println);
    }

    @Test
    public void workingWithMono8() throws InterruptedException {
        Mono<String> m1 = Mono.just("Learn code with Ram");
        Mono<String> m2 = Mono.just("Subscribe to this channel");
        Mono<Integer> m3 = Mono.just(54321);

        System.out.println(Thread.currentThread().getName());
        Flux<String> stringFlux = m1.concatWith(m2)
                .log()
                .delayElements(Duration.ofMillis(2000));
        Thread.sleep(4000);

        stringFlux.subscribe(data -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(data);
        });

        stringFlux.subscribe(System.out::println);
        System.out.println("Terminated main Thread!!");

        m1.delayElement(Duration.ofSeconds(2));
        m1.subscribe(data -> {
            System.out.println(data);
            System.out.println(Thread.currentThread().getName());
        });
    }

    @Test
    public void workingWithMono9() throws InterruptedException {
        Mono<String> m1 = Mono.just("Learn code with Ram").delayElement(Duration.ofSeconds(2));
        m1.subscribe(data -> {
            System.out.println(data);
            System.out.println(Thread.currentThread().getName());
        });
    }

}
