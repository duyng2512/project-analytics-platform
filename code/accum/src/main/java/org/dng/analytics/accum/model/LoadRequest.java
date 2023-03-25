package org.dng.analytics.accum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dng.analytics.accum.manager.constant.ManagerType;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoadRequest {
	
	private String[] schema;
	private String source; // Can be queried, raw string, file path, ...
	private ManagerType strategy;
}
