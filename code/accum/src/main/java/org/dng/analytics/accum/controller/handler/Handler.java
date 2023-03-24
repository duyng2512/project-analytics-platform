package org.dng.analytics.accum.controller.handler;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public abstract class Handler {
	
	public Mono<ServerResponse> pong(ServerRequest request) {
		return ServerResponse.ok()
			       .contentType(MediaType.APPLICATION_JSON)
			       .body(BodyInserters.fromValue("pong"));
	}
	
	public Mono<ServerResponse> process(ServerRequest request) {
		return ServerResponse.ok()
			       .contentType(MediaType.APPLICATION_JSON)
			       .body(BodyInserters.fromValue("NOT_IMPLEMENT"));
	}
	
}
