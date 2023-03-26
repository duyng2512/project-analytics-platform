package org.dng.analytics.accum.config.kafka;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.dng.analytics.accum.config.properties.KafkaProperties;
import org.dng.analytics.accum.config.properties.base.BaseKafkaConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class KafkaProducerConfig {
	
	private final KafkaProperties properties;
	
	@Bean
	public Map<String, KafkaSender<String, String>> accumProducers() {
		Map<String, KafkaSender<String, String>> map = new HashMap<>();
		properties.getProducers()
			.forEach((key, producerConfig) -> {
				
				Map<String, Object> props = new HashMap<>();
				props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerConfig.getBrokers());
				props.put(ProducerConfig.CLIENT_ID_CONFIG, String.format("Producer_%s", key));
				props.put(ProducerConfig.ACKS_CONFIG, "all"); // Wait for full sync to all replicas
				props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
				props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
				SenderOptions<String, String> senderOptions = SenderOptions.create(props);
				map.put(key, KafkaSender.create(senderOptions));
			});
		
		return map;
	}
	
	@Bean
	public Map<String, String> accumTopics() {
		Map<String, String> map = new HashMap<>();
		
		properties.getProducers().values()
			.stream()
			.filter(BaseKafkaConfigurationProperties.ProducerConfig::isEnabled)
			.map(BaseKafkaConfigurationProperties.ProducerConfig::getTopics)
			.forEach(s -> s.forEach((key, value) -> map.put(key, value.getTopic())));
		
		return map;
	}
}
