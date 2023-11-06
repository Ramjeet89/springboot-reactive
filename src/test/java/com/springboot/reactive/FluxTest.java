package com.springboot.reactive;

import com.springboot.reactive.service.FluxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class FluxTest {

    @Autowired
    private FluxService fluxService;


    @Test
    public void testing() {
        this.fluxService.fluxTestingService();
    }

    @Test
    public void getFluxTest() {
        this.fluxService.getFlux().subscribe(data -> {
            System.out.println(data);
            System.out.println("Done with flux data");
        });
    }

    @Test
    public void fruitsFluxTest() {
        this.fluxService.fruitsFlux().subscribe(System.out::println);
    }

    @Test
    public void getBlankFluxTest() {
        this.fluxService.getBlankFlux().subscribe(System.out::println);
    }

    @Test
    public void mapExampleFluxTest() {
        Flux<String> capFlux = this.fluxService.mapExampleFlux();
        StepVerifier.create(capFlux).expectNextCount(5)
                .verifyComplete();
    }

    @Test
    public void mapExampleFluxTest1() {
        Flux<String> capFlux = this.fluxService.mapExampleFlux();
        StepVerifier.create(capFlux)
                .expectNext("Ram".toUpperCase(), "Ankit".toUpperCase(), "Imran".toUpperCase(), "Chaitali".toUpperCase(), "Utkarsh".toUpperCase())
                .verifyComplete();
    }

    @Test
    public void filterLengthFluxTest() {
        Flux<String> filterFlux = fluxService.filterLengthFlux();
        StepVerifier.create(filterFlux).expectNextCount(4).verifyComplete();
    }

    @Test
    public void flatMapTest() {
        Flux<String> stringFlux = this.fluxService.flatMap();
        StepVerifier.create(stringFlux)
                .expectNextCount(28)
                .verifyComplete();
    }

    @Test
    public void transformExampleTest() {
        Flux flux = this.fluxService.transformExample();
        StepVerifier.create(flux)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    public void ifExampleTest() {
        Flux<String> stringFlux = this.fluxService.ifExample(8);
        StepVerifier.create(stringFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    public void concatExampleTest() {
        Flux<String> stringFlux = this.fluxService.concatExample();
        StepVerifier.create(stringFlux)
                .expectNextCount(9)
                .verifyComplete();
    }
    @Test
    public void mergeWithExampleTest(){
        Flux<String> stringFlux = this.fluxService.mergeWithExample().log();
        StepVerifier.create(stringFlux)
                .expectNextCount(9)
                .verifyComplete();
    }


}
