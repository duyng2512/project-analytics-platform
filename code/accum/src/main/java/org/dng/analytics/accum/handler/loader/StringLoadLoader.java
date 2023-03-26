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
		long from = request.getRange().getFrom();
		long to = request.getRange().getTo();
		return Flux.just(request.getSource().getDataSource().split("[|]"))
			       .skip(from)
			       .take(to - from);
	}
}
