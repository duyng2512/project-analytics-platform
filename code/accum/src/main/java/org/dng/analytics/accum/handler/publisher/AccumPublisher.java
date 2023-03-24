package org.dng.analytics.accum.handler.publisher;

import org.dng.analytics.accum.constant.type.PublishType;
import reactor.core.publisher.Flux;

public interface AccumPublisher<I, O> {
	Flux<O> publish(Flux<I> flux);
	
	PublishType source();
}
