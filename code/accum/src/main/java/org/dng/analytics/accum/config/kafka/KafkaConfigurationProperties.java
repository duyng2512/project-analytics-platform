package org.dng.analytics.accum.config.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(value = "kafka")
public class KafkaConfigurationProperties extends BaseKafkaConfigurationProperties {
	
	public KafkaConfigurationProperties(Map<String, ProducerConfig> producers) {
		super(producers);
	}
}
