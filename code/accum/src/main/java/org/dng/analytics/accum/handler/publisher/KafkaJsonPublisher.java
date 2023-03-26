package org.dng.analytics.accum.handler.publisher;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.dng.analytics.accum.constant.kafka.Producer;
import org.dng.analytics.accum.constant.type.PublishType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@Getter
public class KafkaJsonPublisher implements AccumPublisher<JsonObject, RecordMetadata> {
	
	private final Map<String, KafkaSender<String, String>> accumProducers;
	private final Map<String, String> accumTopics;
	
	public KafkaJsonPublisher(@Qualifier("accumProducers") Map<String, KafkaSender<String, String>> accumProducers,
	                          @Qualifier("accumTopics") Map<String, String> accumTopics) {
		this.accumProducers = accumProducers;
		this.accumTopics = accumTopics;
	}
	
	@Override
	public Flux<RecordMetadata> publish(Flux<JsonObject> flux) {
		String topic = accumTopics.get(Producer.Topic.PLATFORM_ACCUM);
		return accumProducers.get(Producer.Cluster.GCP_ACCUM)
			       .send(flux.map(json -> buildSenderRecord(json, topic)))
			       .map(SenderResult::recordMetadata)
			       .log()
			       .doOnError(e -> log.error("Send failed", e));
	}
	
	@Override
	public PublishType source() {
		return PublishType.KAFKA_JSON;
	}
	
	private SenderRecord<String, String, String> buildSenderRecord(JsonObject json, String topic) {
		String correlationMetadata = String.valueOf(System.currentTimeMillis());
		ProducerRecord<String, String> rec = new ProducerRecord<>(topic, UUID.randomUUID().toString(), json.toString());
		return SenderRecord.create(rec, correlationMetadata);
	}
	
}