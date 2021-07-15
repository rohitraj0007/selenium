package com.example.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	public static void main(String[] args)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		String stringToParse = "{\r\n" + "		\"name\":\"Siddharth\",\r\n" + "		\"age\": 28,\r\n"
				+ "		\"isPermanentEmployee\": true,\r\n" + "		\"address\": [{\r\n"
				+ "			\"line1\": \"AECS Layout\",\r\n" + "			\"line2\":\"Kundalhahalli\",\r\n"
				+ "			\"city\":\"Bangalore\",\r\n" + "			\"pincode\":560037,\r\n"
				+ "			\"type\":\"present\"\r\n" + "			},\r\n" + "			{\r\n"
				+ "			\"line1\": \"Konia\",\r\n" + "			\"line2\":\"Kajjakpura\",\r\n"
				+ "			\"city\":\"Varanasi\",\r\n" + "			\"pincode\":221007,\r\n"
				+ "			\"type\":\"permanent\"\r\n" + "			}\r\n" + "		],\r\n" + "		\"passport\":{\r\n"
				+ "			\"number\":\"M3713552\",\r\n" + "			\"validFrom\": \"19/11/2010\",\r\n"
				+ "			\"validTill\": \"18/11/2020\"\r\n" + "		},\r\n" + "		\"access\":[\r\n"
				+ "			\"libraray\",\r\n" + "			\"odc\"\r\n" + "		]\r\n" + "	}";
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(stringToParse);

		System.out.println(json);

		Map<String, Object> result = new ObjectMapper().readValue(stringToParse, HashMap.class);
		List<String> keyList = new ArrayList<>();
		System.out.println(getKeys(result, "", keyList));

	}

//address[0].line1
	public static List<String> getKeys(Map<String, Object> map, String listName, List<String> keyList) {
		Iterator<Map.Entry<String, Object>> itr = map.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Object> ent = itr.next();
			if (ent.getValue() instanceof Map<?, ?>) {
				keyList = getKeys((Map<String, Object>) ent.getValue(), ent.getKey() + ".", keyList);
			} else if (ent.getValue() instanceof ArrayList) {
				int count = 0;
				for (Object val : (ArrayList) ent.getValue()) {
					if (val instanceof HashMap) {
						keyList = getKeys((Map<String, Object>) val, ent.getKey() + "[" + count + "].", keyList);
						count++;
					} else {
						keyList.add(ent.getKey());
						break;
					}
				}
			} else {
				keyList.add(listName + ent.getKey());
			}
		}
		return keyList;

	}
}
