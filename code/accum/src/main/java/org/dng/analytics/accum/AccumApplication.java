package org.dng.analytics.accum;

import org.dng.analytics.accum.handler.publisher.KafkaPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AccumApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccumApplication.class, args);
	}

}
