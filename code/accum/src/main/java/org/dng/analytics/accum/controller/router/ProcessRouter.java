package org.dng.analytics.accum.controller.router;

import org.dng.analytics.accum.controller.handler.ProcessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * @Configuration = Allow multiple bean init
 */
@Configuration(proxyBeanMethods = false)
// proxyBeanMethods = false no enforce bean life cycle
public class ProcessRouter {
	
	@Bean
	public RouterFunction<ServerResponse> route(ProcessHandler handler) {
		return RouterFunctions
			       .route(GET("/ping").and(accept(MediaType.APPLICATION_JSON)), handler::pong);
	}
	
}
