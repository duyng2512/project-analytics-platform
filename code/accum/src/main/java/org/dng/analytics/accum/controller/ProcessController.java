package org.dng.analytics.accum.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dng.analytics.accum.manager.base.AccumManager;
import org.dng.analytics.accum.constant.type.ManagerType;
import org.dng.analytics.accum.model.LoadRequest;
import org.dng.analytics.accum.model.LoadResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/process")
@AllArgsConstructor
public class ProcessController {
	private final Map<ManagerType, AccumManager<?, ?, ?>> accumManagers;
	
	@PostMapping
	public Mono<LoadResponse> process(@RequestBody LoadRequest loadRequest) {
		AccumManager<?, ?, ?> accumManager = accumManagers.get(loadRequest.getStrategy());
		if (accumManager == null) {
			return Mono.error(new IllegalArgumentException("Invalid Strategy " + loadRequest.getStrategy()));
		} else {
			return accumManager.handle(loadRequest);
		}
	}
}
