package org.dng.analytics.accum;

import com.google.gson.JsonObject;
import org.dng.analytics.accum.handler.loader.FileLoadLoader;
import org.dng.analytics.accum.handler.loader.StringLoadLoader;
import org.dng.analytics.accum.handler.mapper.HashMapToJsonMapper;
import org.dng.analytics.accum.handler.mapper.StringToHashMapMapper;
import org.dng.analytics.accum.handler.publisher.KafkaJsonPublisher;
import org.dng.analytics.accum.model.LoadRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.URL;
import java.util.Map;

@SpringBootTest
class AccumApplicationTests {
	
	@Autowired
	KafkaJsonPublisher kafkaJsonPublisher;
	@Autowired
	FileLoadLoader fileLoadLoader;
	
	@Autowired
	StringLoadLoader stringLoadLoader;
	@Autowired
	HashMapToJsonMapper jsonMapper;
	@Autowired
	StringToHashMapMapper mapMapper;
	
	@Test
	void fileLoad_sendMessageToKafka() {
		
		URL dataFile = getClass().getClassLoader().getResource("data.txt");
		assert dataFile != null;
		
		Flux<String> fluxString = fileLoadLoader.load(LoadRequest.builder()
			                                              .source(LoadRequest.Source.builder().dataSource(dataFile.getPath()).build())
			                                              .build());
		Flux<Map<String, String>> fluxMap = mapMapper.process(fluxString, new String[]{"id", "name", "age"});
		Flux<JsonObject> fluxJson = jsonMapper.process(fluxMap, null);
		
		StepVerifier.create(kafkaJsonPublisher.publish(fluxJson))
			.expectNextCount(3)
			.expectComplete()
			.verify();
		;
	}
	
	@Test
	void stringLoad_sendMessageToKafka() {
		
		URL dataFile = getClass().getClassLoader().getResource("data.txt");
		assert dataFile != null;
		
		Flux<String> fluxString = stringLoadLoader.load(LoadRequest.builder()
			                                                .source(LoadRequest.Source.builder().dataSource("1,abc,15|2,xyz,25").build())
			                                                .build());
		Flux<Map<String, String>> fluxMap = mapMapper.process(fluxString, new String[]{"id", "name", "age"});
		Flux<JsonObject> fluxJson = jsonMapper.process(fluxMap, null);
		
		StepVerifier.create(kafkaJsonPublisher.publish(fluxJson))
			.expectNextCount(2)
			.expectComplete()
			.verify();
		;
	}
	
	@Test
	@SuppressWarnings({"unchecked", "rawtypes"})
	void no_type_check() {
		URL dataFile = getClass().getClassLoader().getResource("data.txt");
		assert dataFile != null;
		
		Flux flux = stringLoadLoader.load(LoadRequest.builder()
			                                  .source(LoadRequest.Source.builder().dataSource("1,abc,15|2,xyz,25").build())
			                                  .build());
		flux = mapMapper.process(flux, new String[]{"id", "name", "age"});
		flux = jsonMapper.process(flux, null);
		StepVerifier.create(kafkaJsonPublisher.publish(flux))
			.expectNextCount(2)
			.expectComplete()
			.verify();
		;
	}
	
	@Test
	@SuppressWarnings({"rawtypes"})
	void chain_of_flux() {
		
		Flux flux = Flux.from(stringLoadLoader.load(LoadRequest.builder()
			                                            .source(LoadRequest.Source.builder().dataSource("1,abc,15|2,xyz,25").build())
			                                            .build()))
			            .transform(s -> mapMapper.process(s, new String[]{"id", "code", "value"}))
			            .transform(s -> jsonMapper.process(s, null))
			            .transform(s -> kafkaJsonPublisher.publish(s))
			            .log();
		
		StepVerifier.create(flux)
			.expectNextCount(2)
			.expectComplete()
			.verify();
	}
}
