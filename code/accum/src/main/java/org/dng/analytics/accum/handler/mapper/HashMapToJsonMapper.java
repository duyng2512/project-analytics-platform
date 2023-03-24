package org.dng.analytics.accum.handler.mapper;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.dng.analytics.accum.constant.type.MapType;
import org.dng.analytics.accum.utils.MappingUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Slf4j
@Service
public class HashMapToJsonMapper implements AccumMapper<Map<String, String>, JsonObject> {
	
	@Override
	public MapType source() {
		return MapType.MAP_TO_JSON;
	}
	
	@Override
	public Flux<JsonObject> process(Flux<Map<String, String>> fluxRequest, String[] schema) {
		return fluxRequest.map(MappingUtils::jsonFromMap)
			       .map(jsonObject -> {
				       log.info("Log JSON {}", jsonObject);
				       return jsonObject;
			       }).onErrorComplete();
	}
}
