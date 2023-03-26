package org.dng.analytics.accum.handler.loader;

import org.dng.analytics.accum.constant.type.DatabaseType;
import org.dng.analytics.accum.constant.type.SourceType;
import org.dng.analytics.accum.model.LoadRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.sql.ResultSetMetaData;
import java.util.*;

@Service
public class DBLoadLoader implements AccumLoader<Map<String, String>> {
	private final Scheduler jdbcScheduler;
	private final EnumMap<DatabaseType, NamedParameterJdbcTemplate> jdbcTemplates;
	
	public DBLoadLoader(@Qualifier("jdbcScheduler") Scheduler jdbcScheduler,
	                    EnumMap<DatabaseType, NamedParameterJdbcTemplate> jdbcTemplates) {
		this.jdbcScheduler = jdbcScheduler;
		this.jdbcTemplates = jdbcTemplates;
	}
	
	@Override
	public SourceType source() {
		return SourceType.DATABASE;
	}
	
	@Override
	public Flux<Map<String, String>> load(LoadRequest request) {
		String dataSource = request.getSource().getDataSource();
		NamedParameterJdbcTemplate template = jdbcTemplates.get(DatabaseType.valueOf(dataSource));
		
		if (template == null)
			return Flux.error(new IllegalArgumentException(dataSource + " not found"));
		
		return Flux.defer(() -> Flux.fromIterable(query(template, request))).
			       log()
			       .subscribeOn(jdbcScheduler);
	}
	
	private List<Map<String, String>> query(NamedParameterJdbcTemplate template, LoadRequest request) {
		int from = request.getRange().getFrom();
		int to = request.getRange().getTo();
		String query = request.getSource().getQuery();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("offset", from);
		params.addValue("limit", to - from);
		
		return template.query(query + " limit :limit offset :offset ", params, rs -> {
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			List<Map<String, String>> results = new ArrayList<>();
			while (rs.next()) {
				Map<String, String> row = new HashMap<>();
				for (int i = 1; i <= columns; i++)
					row.put(md.getColumnLabel(i).toUpperCase(), rs.getString(i));
				results.add(row);
			}
			return results;
		});
	}
	
}
