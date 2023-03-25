package org.dng.analytics.accum.workspace;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.BiFunction;


public class FluxTest {
	
	@Test
	void testReduce() {
		
		Flux<String> inFlux = Flux.just("1", "2", "3");
		inFlux.reduce("OK", String::concat).map(String::length).subscribe(System.out::println);
		StepVerifier.create(inFlux)
			.expectNextCount(3)
			.verifyComplete();
	}
	
}