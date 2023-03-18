package org.dng.analytics.accum.handler.mapper;

import lombok.extern.slf4j.Slf4j;
import org.dng.analytics.accum.constant.MapType;
import org.dng.analytics.accum.utils.MappingUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
@Slf4j
public class StringToHashMapMapper implements AccumMapper<String, String[], Map<String, String>> {
	
	@Override
	public MapType source() {
		return MapType.STR_T0_MAP;
	}
	
	@Override
	public Flux<Map<String, String>> process(Flux<String> fluxRequest, String[] schema) {
		return fluxRequest.map(s -> MappingUtils.mapFromSchema(schema, s)).log();
	}
}
