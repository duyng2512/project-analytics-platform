package org.dng.analytics.accum.handler.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Slf4j
class HashMapToJsonMapperTest {
	@Test
	void fromMapFluxToJson() {
		List<String> listString = List.of("1,dave,100_000", "2,mike,200_000", "3,ted,300_000");
		Flux<String> stringFlux = Flux.just(listString.toArray(new String[]{}));
		
		StringToHashMapMapper schemaMapper = new StringToHashMapMapper();
		HashMapToJsonMapper jsonMapper = new HashMapToJsonMapper();
		Flux<Map<String, String>> sFlux = schemaMapper.process(stringFlux, new String[]{"id", "name", "salary"});
		jsonMapper.process(sFlux, null)
			.map(c -> {
				log.info(c.toString());
				return c;
			}).subscribe();
	}
}