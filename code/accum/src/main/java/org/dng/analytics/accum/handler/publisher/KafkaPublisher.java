package org.dng.analytics.accum.handler.publisher;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.dng.analytics.accum.constant.Producer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.util.Map;

@Service
@Slf4j
public class KafkaPublisher implements AccumPublisher<JsonObject, Map<String, Object>, Object> {
	
	
	private final  Map<String, KafkaSender<String, String>> accumProducers;
	private final Map<String, String> accumTopics;
	
	public KafkaPublisher(@Qualifier("accumProducers") Map<String, KafkaSender<String, String>> accumProducers,
	                      @Qualifier("accumTopics") Map<String, String> accumTopics) {
		this.accumProducers = accumProducers;
		this.accumTopics = accumTopics;
	}
	
	@Override
	public Object publish(Flux<JsonObject> flux, Map<String, Object> context) {
		String topic = accumTopics.get(Producer.Topic.PLATFORM_ACCUM);
		accumProducers.get(Producer.GCP_ACCUM)
			//.send(Flux.just(1, 100).map(i -> buildSenderRecord(i, topic)))
			.send(Flux.range(1, 100)
				      .map(i -> SenderRecord.create(new ProducerRecord<>(topic, String.valueOf(i), "Message_" + i), i)))
			.doOnError(e -> log.error("Send failed", e))
			.subscribe(r -> log.info("Record Data {}", r));
		return null;
	}
	
	private SenderRecord<String, String, String> buildSenderRecord(Integer i, String topic) {
		String correlationMetadata = String.valueOf(i);
		ProducerRecord<String, String> rec = new ProducerRecord<>(topic, String.valueOf(i), "Message_" + i);
		return SenderRecord.create(rec, correlationMetadata);
	}
	
}