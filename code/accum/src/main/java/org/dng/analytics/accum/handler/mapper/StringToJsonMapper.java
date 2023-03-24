package org.dng.analytics.accum.handler.mapper;


import com.google.gson.JsonObject;
import org.dng.analytics.accum.constant.type.MapType;
import org.dng.analytics.accum.utils.MappingUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StringToJsonMapper implements AccumMapper<String, JsonObject> {
	
	@Override
	public MapType source() {
		return MapType.STR_T0_JSON;
	}
	
	@Override
	public Flux<JsonObject> process(Flux<String> fluxRequest, String[] schema) {
		return fluxRequest.map(s -> MappingUtils.mapFromSchema(schema, s))
			       .map(MappingUtils::jsonFromMap)
			       .log()
			       .onErrorComplete();
	}
}
