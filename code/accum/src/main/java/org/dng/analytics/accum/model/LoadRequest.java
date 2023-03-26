package org.dng.analytics.accum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dng.analytics.accum.constant.type.ManagerType;
import org.dng.analytics.accum.constant.type.SourceType;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoadRequest {
	
	private String[] schema;
	private Source source; // Can be queried, raw string, file path, ...
	private ManagerType strategy;
	private Range range;
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Range {
		private int to;
		private int from;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Source {
		private SourceType type;
		private String dataSource;
		private String query;
		private boolean cache;
	}
}
