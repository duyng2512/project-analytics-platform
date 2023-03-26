package org.dng.analytics.accum.handler.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.dng.analytics.accum.model.LoadRequest;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

@SpringBootTest
class DBLoadLoaderTest {
	@Autowired
	DBLoadLoader dbLoadLoader;
	
	@Test
	void testDbLoader() {
		LoadRequest request = LoadRequest.builder()
			                      .source(LoadRequest.Source.builder()
				                              .dataSource("MY_SQL")
				                              .query("select * from stock_data")
				                              .build())
			                      .range(LoadRequest.Range
				                             .builder()
				                             .from(0)
				                             .to(5)
				                             .build())
			                      .build();
		
		StepVerifier.create(dbLoadLoader.load(request))
			.expectNextCount(5)
			.verifyComplete();
	}
}