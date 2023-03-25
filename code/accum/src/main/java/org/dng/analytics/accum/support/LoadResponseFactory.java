package org.dng.analytics.accum.support;

import org.dng.analytics.accum.model.LoadResponse;

import java.util.concurrent.atomic.AtomicInteger;

public class LoadResponseFactory {
	
	static public LoadResponse successLoadRes() {
		return LoadResponse.builder()
			       .errCode(0)
			       .recordAffect(new AtomicInteger())
			       .errMessage("OK")
			       .build();
	}
	
	static public LoadResponse errorLoadRes(Throwable throwable) {
		return LoadResponse.builder()
			       .errCode(-1)
			       .errMessage(throwable.getMessage())
			       .build();
	}
}
