package org.dng.analytics.accum.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
class MappingUtilsTest {
	
	@Test
	void stringToMap() {
		
		List<String> schema = new ArrayList<>(List.of("id", "name", "data"));
		String line = "1,test,object_value";
		String[] data = line.split(",");
		Map<String, String> object = new HashMap<>();
		
		for (int i = 0; i < data.length; i++) {
			object.put(schema.get(i), data[i]);
		}
		log.info(object.toString());
		Assertions.assertNotNull(object);
	}
}