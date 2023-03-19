package org.dng.analytics.accum.handler.loader;

import org.dng.analytics.accum.constant.SourceType;
import org.dng.analytics.accum.model.LoadRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DBLoadLoader implements AccumLoader<Object> {
	
	@Override
	public SourceType source() {
		return SourceType.DATABASE;
	}
	
	@Override
	public Flux<Object> load(LoadRequest request) {
		return null;
	}
}
