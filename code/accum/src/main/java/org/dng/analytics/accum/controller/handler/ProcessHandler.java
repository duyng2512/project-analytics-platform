package org.dng.analytics.accum.controller.handler;

import org.dng.analytics.accum.model.LoadRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProcessHandler extends Handler {
	
	@Override
	public Mono<ServerResponse> process(ServerRequest request) {
		return super.process(request);
	}
}
