package org.dng.analytics.accum.manager;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.dng.analytics.accum.handler.loader.StringLoadLoader;
import org.dng.analytics.accum.handler.mapper.StringToJsonMapper;
import org.dng.analytics.accum.handler.publisher.KafkaPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccumStringToJsonManager extends AccumManager<String, JsonObject, RecordMetadata> {
	
	public AccumStringToJsonManager(StringLoadLoader loader,
	                                StringToJsonMapper mapper,
	                                KafkaPublisher publisher) {
		this.loader = loader;
		this.mapper = mapper;
		this.publisher = publisher;
	}
}
