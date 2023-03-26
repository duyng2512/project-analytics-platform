package org.dng.analytics.accum.manager;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.dng.analytics.accum.handler.loader.StringLoadLoader;
import org.dng.analytics.accum.handler.mapper.StringToJsonMapper;
import org.dng.analytics.accum.handler.publisher.KafkaJsonPublisher;
import org.dng.analytics.accum.manager.base.AccumManager;
import org.dng.analytics.accum.constant.type.ManagerType;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringToJsonManager extends AccumManager<String, JsonObject, RecordMetadata> {
	
	public StringToJsonManager(StringLoadLoader loader,
	                           StringToJsonMapper mapper,
	                           KafkaJsonPublisher publisher) {
		this.loader = loader;
		this.mapper = mapper;
		this.publisher = publisher;
	}
	
	@Override
	public ManagerType type() {
		return ManagerType.STR_TO_JSON;
	}
}
