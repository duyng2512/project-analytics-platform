package org.dng.analytics.accum.config;

import org.dng.analytics.accum.constant.SourceType;
import org.dng.analytics.accum.handler.loader.AccumLoader;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;

@Configuration
@SuppressWarnings("rawtypes")
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class AccumConfiguration {
	
	@Bean
	EnumMap<SourceType, AccumLoader> accumLoadHandlerMap(List<AccumLoader> accumLoaders) {
		EnumMap<SourceType, AccumLoader> map = new EnumMap<>(SourceType.class);
		accumLoaders.forEach(s -> map.put(s.source(), s));
		return map;
	}
	

}
