package org.dng.analytics.accum.handler.publisher;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class KafkaPublisher implements AccumPublisher<JsonObject, Map<String, Object>, Object> {
