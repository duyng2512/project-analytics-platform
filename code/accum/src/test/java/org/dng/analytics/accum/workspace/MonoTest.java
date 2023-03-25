package org.dng.analytics.accum.workspace;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class MonoTest {
	
	@Test
	void testMono() {
		Mono.just("Ok")
			.map(String::toUpperCase)
			.then(Mono.just("Test"))
			.subscribe(System.out::println);
	}
	
	@Test
	void testFlatMap() {
		Flux<String> inFlux = Flux.just("baeldung", ".", "com");
		Function<String, Publisher<String>> mapper = s -> Flux.just(s.toUpperCase().split(""));
		Flux<String> outFlux = inFlux.flatMap(mapper);
		List<String> output = new ArrayList<>();
		outFlux.subscribe(output::add);
		System.out.println(output);
	}
	
	@Test
	void testHandle() {
		StepVerifier.create(Flux.just(1)
			                    .handle((v, sink) -> sink.next(v + 1)))
			.expectNext(2)
			.expectComplete()
			.verify();
	}
	
	@Test
	void testComplete() {
		Mono<String> doSomething = Mono.create(s -> s.success("Complete"));
		
		StepVerifier.create(doSomething)
			.expectNext("Complete")
			.expectComplete()
			.verify();
		
	}
	
	@Test
	void testFluxProcessThenReturn() {
		
		Flux<Object> doSomething = Flux.create(s -> {
			s.next("STEP_1");
			s.next("STEP_2");
			s.next("DONE");
		});
		
		doSomething.subscribe(Mono::just);
		
		StepVerifier.create(doSomething)
			.expectNext("STEP_1")
			.expectNext("STEP_2")
			.expectNext("DONE")
			.expectComplete()
			.verify();
	}
}