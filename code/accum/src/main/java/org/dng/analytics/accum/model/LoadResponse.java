package org.dng.analytics.accum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoadResponse {
	
	private Integer errCode;
	private String errMessage;
	private AtomicInteger recordAffect;
	
	public LoadResponse incRec() {
		this.recordAffect.incrementAndGet();
		return this;
	}
}
