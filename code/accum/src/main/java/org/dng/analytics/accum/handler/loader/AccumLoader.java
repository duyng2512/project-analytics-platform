package org.dng.analytics.accum.handler.loader;

import org.dng.analytics.accum.constant.type.SourceType;
import org.dng.analytics.accum.model.LoadRequest;
import reactor.core.publisher.Flux;

public interface AccumLoader<O> {
	SourceType source();
	Flux<O> load(LoadRequest request);
}