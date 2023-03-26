package org.dng.analytics.accum.config;

import org.dng.analytics.accum.constant.type.MapType;
import org.dng.analytics.accum.constant.type.PublishType;
import org.dng.analytics.accum.constant.type.SourceType;
import org.dng.analytics.accum.handler.loader.AccumLoader;
import org.dng.analytics.accum.handler.mapper.AccumMapper;
import org.dng.analytics.accum.handler.publisher.AccumPublisher;
import org.dng.analytics.accum.manager.base.AccumManager;
import org.dng.analytics.accum.constant.type.ManagerType;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class AccumConfiguration {
	
	@Bean
	EnumMap<SourceType, AccumLoader<?>> accumLoadHandlerMap(List<AccumLoader<?>> loaders) {
		EnumMap<SourceType, AccumLoader<?>> map = new EnumMap<>(SourceType.class);
		loaders.forEach(s -> map.put(s.source(), s));
		return map;
	}
	
	@Bean
	EnumMap<MapType, AccumMapper<?, ?>> accumMapHandlerMap(List<AccumMapper<?, ?>> mapper) {
		EnumMap<MapType, AccumMapper<?, ?>> map = new EnumMap<>(MapType.class);
		mapper.forEach(s -> map.put(s.source(), s));
		return map;
	}
	
	@Bean
	EnumMap<PublishType, AccumPublisher<?, ?>> accumPublishHandlerMap(List<AccumPublisher<?, ?>> mapper) {
		EnumMap<PublishType, AccumPublisher<?, ?>> map = new EnumMap<>(PublishType.class);
		mapper.forEach(s -> map.put(s.source(), s));
		return map;
	}
	
	@Bean
	EnumMap<ManagerType, AccumManager<?, ?, ?>> accumManagers(List<AccumManager<?, ?, ?>> manager) {
		EnumMap<ManagerType, AccumManager<?, ?, ?>> map = new EnumMap<>(ManagerType.class);
		manager.forEach(s -> map.put(s.type(), s));
		return map;
	}
}
