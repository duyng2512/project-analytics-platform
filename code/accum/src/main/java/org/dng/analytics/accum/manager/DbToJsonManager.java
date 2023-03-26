package org.dng.analytics.accum.manager;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.dng.analytics.accum.constant.type.ManagerType;
import org.dng.analytics.accum.handler.loader.DBLoadLoader;
import org.dng.analytics.accum.handler.mapper.HashMapToJsonMapper;
import org.dng.analytics.accum.handler.publisher.KafkaJsonPublisher;
import org.dng.analytics.accum.manager.base.AccumManager;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class DbToJsonManager extends AccumManager<Map<String, String>, JsonObject, RecordMetadata> {
	
	public DbToJsonManager(DBLoadLoader loader,
	                       HashMapToJsonMapper mapper,
	                       KafkaJsonPublisher publisher) {
		this.loader = loader;
		this.mapper = mapper;
		this.publisher = publisher;
	}
	
	@Override
	public ManagerType type() {
		return ManagerType.DB_TO_JSON;
	}
}
