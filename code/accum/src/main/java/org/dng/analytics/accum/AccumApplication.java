package org.dng.analytics.accum;

import org.dng.analytics.accum.manager.AccumStringToJsonManager;
import org.dng.analytics.accum.model.LoadRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AccumApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AccumApplication.class, args);
		AccumStringToJsonManager manager = context.getBean(AccumStringToJsonManager.class);
		manager.handle(LoadRequest.builder()
			               .schema(new String[]{"id", "name"})
			               .source("12,tester|13,dev")
			               .build()).block();
	}

}
