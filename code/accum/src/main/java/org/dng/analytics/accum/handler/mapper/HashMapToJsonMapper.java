package org.dng.analytics.accum.handler.mapper;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.dng.analytics.accum.constant.MapType;
import org.dng.analytics.accum.utils.MappingUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
@Slf4j
public class HashMapToJsonMapper implements AccumMapper<Map<String, String>, Object, JsonObject> {
	
	@Override
	public MapType source() {
		return MapType.MAP_TO_JSON;
	}
	
	@Override
	public Flux<JsonObject> process(Flux<Map<String, String>> fluxRequest, Object context) {
		return fluxRequest.map(MappingUtils::jsonFromMap)
			       .map(jsonObject -> {
				       log.info("Log JSON {}", jsonObject);
				       return jsonObject;
			       });
	}
}
