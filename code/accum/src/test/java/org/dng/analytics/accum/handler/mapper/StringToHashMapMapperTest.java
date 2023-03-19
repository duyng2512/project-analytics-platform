package org.dng.analytics.accum.handler.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Slf4j
class StringToHashMapMapperTest {
	
	@Test
	void mapFromStringFluxToMapObject() {
		List<String> listString = List.of("1,dave,100_000", "2,mike,200_000", "3,ted,300_000");
		Flux<String> stringFlux = Flux.just(listString.toArray(new String[]{}));
		
		StringToHashMapMapper handler = new StringToHashMapMapper();
		Flux<Map<String, String>> flux = handler.process(stringFlux, new String[] {"id", "name", "salary"});
		flux.subscribe();
	}
}