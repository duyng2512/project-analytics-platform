package org.dng.analytics.accum;

import com.google.gson.JsonObject;
import org.dng.analytics.accum.handler.loader.FileLoadLoader;
import org.dng.analytics.accum.handler.mapper.HashMapToJsonMapper;
import org.dng.analytics.accum.handler.mapper.StringToHashMapMapper;
import org.dng.analytics.accum.handler.publisher.KafkaPublisher;
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
	KafkaPublisher kafkaPublisher;
	
	@Autowired
	FileLoadLoader fileLoadLoader;
	
	@Autowired
	HashMapToJsonMapper jsonMapper;
	
	@Autowired
	StringToHashMapMapper mapMapper;
	
	@Test
	void fileLoad_sendMessageToKafka() {
		
		URL dataFile = getClass().getClassLoader().getResource("data.txt");
		assert dataFile != null;
		
		Flux<String> fluxString = fileLoadLoader.load(LoadRequest.builder()
			                                              .query(dataFile.getPath())
			                                              .build());
		Flux<Map<String, String>> fluxMap = mapMapper.process(fluxString, new String[]{"id", "name", "age"});
		Flux<JsonObject> fluxJson = jsonMapper.process(fluxMap, null);
		
		StepVerifier.create(kafkaPublisher.publish(fluxJson, null))
			.expectNextCount(3)
			.expectComplete()
			.verify();;
	}

}
