package org.dng.analytics.accum.manager;

import lombok.extern.slf4j.Slf4j;
import org.dng.analytics.accum.constant.SourceType;
import org.dng.analytics.accum.handler.loader.AccumLoader;
import org.dng.analytics.accum.model.LoadRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.EnumMap;

@Component
@Slf4j
public class AccumManager {
	
	private final EnumMap<SourceType, AccumLoader> accumLoadHandlerMap;
	
	
	public AccumManager(EnumMap<SourceType, AccumLoader> accumLoadHandlerMap) {
		this.accumLoadHandlerMap = accumLoadHandlerMap;
	}
	
	public Flux<Object> doHandleLoadRequest(LoadRequest request) {
		AccumLoader handler = accumLoadHandlerMap.get(request.getSourceType());
		return handler.load(request);
	}
	
}
