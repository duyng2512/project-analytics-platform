package org.dng.analytics.accum.utils;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class MappingUtils {
	
	static public Map<String, String> mapFromSchema(String[] schema, String data) {
		String[] dataArr = data.split(",");
		Map<String, String> object = new HashMap<>();
		Assert.isTrue(dataArr.length == schema.length, "Invalid data length ");
		for (int i = 0; i < dataArr.length; i++) {
			object.put(schema[i], dataArr[i]);
		}
		return object;
	}
	
	static public JsonObject jsonFromMap(Map<String, String> data) {
		return new Gson().toJsonTree(data).getAsJsonObject();
	}
}
