package org.dng.analytics.accum.config.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.sql.DataSource;
import java.util.concurrent.Executors;

@Configuration
public class DatabaseConfig {
	
	@Bean
	public DataSource mysqlDataSource(DataSourceProperties mySQLDataSourceProperties) {
		return mySQLDataSourceProperties.initializeDataSourceBuilder().build();
	}
	
	@Bean
	public DataSource postgresDataSource(DataSourceProperties postgresDataSourceProperties) {
		return postgresDataSourceProperties.initializeDataSourceBuilder().build();
	}
	
	@Bean
	public NamedParameterJdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	public NamedParameterJdbcTemplate postgresJdbcTemplate(@Qualifier("postgresDataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	public Scheduler jdbcScheduler() {
		return Schedulers.fromExecutor(Executors.newFixedThreadPool(20));
	}
	
}
