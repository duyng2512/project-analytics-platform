package org.dng.analytics.accum.manager;

import org.dng.analytics.accum.handler.loader.AccumLoader;
import org.dng.analytics.accum.handler.mapper.AccumMapper;
import org.dng.analytics.accum.handler.publisher.AccumPublisher;
import org.dng.analytics.accum.model.LoadRequest;
import org.dng.analytics.accum.model.LoadResponse;
import reactor.core.publisher.Mono;

public class AccumManager<I, M, O> {
	
	protected AccumLoader<I> loader;
	protected AccumMapper<I, M> mapper;
	protected AccumPublisher<M, O> publisher;
	
	public Mono<LoadResponse> handle(LoadRequest request) {
		return loader.load(request)
			       .transform(s -> mapper.process(s, request.getSchema()))
			       .transform(s -> publisher.publish(s))
			       .then(Mono.just(LoadResponse.builder()
				                       .errCode(1)
				                       .errMessage("Success")
				                       .build()))
			       .onErrorReturn(LoadResponse.builder()
				                      .errCode(-1)
				                      .errMessage("Error")
				                      .build());
	}
}
