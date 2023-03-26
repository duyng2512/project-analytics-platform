package org.dng.analytics.accum.config;

import org.dng.analytics.accum.constant.type.*;
import org.dng.analytics.accum.handler.loader.AccumLoader;
import org.dng.analytics.accum.handler.mapper.AccumMapper;
import org.dng.analytics.accum.handler.publisher.AccumPublisher;
import org.dng.analytics.accum.manager.base.AccumManager;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

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
	
	@Bean
	EnumMap<DatabaseType, NamedParameterJdbcTemplate> jdbcTemplates(Map<String, NamedParameterJdbcTemplate> jdbcTemplates) {
		EnumMap<DatabaseType, NamedParameterJdbcTemplate> map = new EnumMap<>(DatabaseType.class);
		map.put(DatabaseType.MY_SQL, jdbcTemplates.get("mysqlJdbcTemplate"));
		map.put(DatabaseType.POSTGRES, jdbcTemplates.get("postgresJdbcTemplate"));
		return map;
	}
}
