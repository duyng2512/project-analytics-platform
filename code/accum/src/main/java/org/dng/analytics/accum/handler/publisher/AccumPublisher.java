package org.dng.analytics.accum.handler.publisher;

import reactor.core.publisher.Flux;

public interface AccumPublisher<I, C, O> {
	O publish(Flux<I> flux, C context);
}
