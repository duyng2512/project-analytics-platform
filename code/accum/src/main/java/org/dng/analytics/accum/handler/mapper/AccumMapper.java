package org.dng.analytics.accum.handler.mapper;

import org.dng.analytics.accum.constant.type.MapType;
import reactor.core.publisher.Flux;

public interface AccumMapper<I, O> {
	MapType source();
	Flux<O> process(Flux<I> fluxRequest, String[] schema);
}
