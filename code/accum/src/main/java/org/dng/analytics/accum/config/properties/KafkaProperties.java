package org.dng.analytics.accum.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.dng.analytics.accum.config.properties.base.BaseKafkaConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(value = "kafka")
public class KafkaProperties extends BaseKafkaConfigurationProperties {
	public KafkaProperties(Map<String, ProducerConfig> producers) {
		super(producers);
	}
}
