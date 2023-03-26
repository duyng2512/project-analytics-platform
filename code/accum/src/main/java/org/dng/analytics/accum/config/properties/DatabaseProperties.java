package org.dng.analytics.accum.config.properties;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseProperties {
	
	@Bean
	@ConfigurationProperties("spring.datasource.mysql")
	public DataSourceProperties mySQLDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource.postgres")
	public DataSourceProperties postgresDataSourceProperties() {
		return new DataSourceProperties();
	}
	
}
