package org.dng.analytics.accum.handler.loader;

import org.dng.analytics.accum.constant.type.SourceType;
import org.dng.analytics.accum.model.LoadRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StringLoadLoader implements AccumLoader<String> {
	
	@Override
	public SourceType source() {
		return SourceType.STRING;
	}
	
	@Override
	public Flux<String> load(LoadRequest request) {
		return Flux.just(request.getSource().split("[|]")); // Special character
	}
}
