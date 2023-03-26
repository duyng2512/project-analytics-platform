package org.dng.analytics.accum.config.properties.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class BaseKafkaConfigurationProperties {
	
	protected final Map<String, ProducerConfig> producers;
	
	@Getter
	@Setter
	@NoArgsConstructor
	public static class ProducerConfig {
		private boolean enabled;
		private List<String> brokers;
		private Map<String, ProducerTopicProperties> topics;
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	public static class ProducerTopicProperties {
		private String topic;
		private boolean enabled;
		private String predefinedMsgFormat;
	}
	
}
