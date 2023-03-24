package org.dng.analytics.accum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoadRequest {
	
	private String[] schema;
	private String source; // Can be queried, raw string, file path, ...
	private String strategy;
	
}
