package org.dng.analytics.accum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AccumApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AccumApplication.class, args);
    }

}
