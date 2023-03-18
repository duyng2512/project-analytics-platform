package org.dng.analytics.accum.handler.mapper;

import org.dng.analytics.accum.constant.MapType;
import reactor.core.publisher.Flux;

public interface AccumMapper<I, C, O> {
	MapType source();
	Flux<O> process(Flux<I> fluxRequest, C context);
}
