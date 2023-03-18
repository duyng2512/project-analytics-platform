package org.dng.analytics.accum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dng.analytics.accum.constant.SourceType;

import java.util.Map;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoadRequest {
	
	private SourceType sourceType;
	private String[] schema;
	private String query;
	private Integer stepSize;
	private String filter;
	
}
