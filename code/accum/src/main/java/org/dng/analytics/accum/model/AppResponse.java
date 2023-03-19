package org.dng.analytics.accum.model;

import lombok.Builder;

import java.time.Duration;

@Builder
public class AppResponse {
	
	String processName;
	Duration executeTime;
	Boolean result;
	String message;
	
}
