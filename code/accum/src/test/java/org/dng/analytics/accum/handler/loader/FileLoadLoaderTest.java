package org.dng.analytics.accum.handler.loader;

import org.dng.analytics.accum.constant.TestConstant;
import org.dng.analytics.accum.model.LoadRequest;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

class FileLoadLoaderTest {
	
	@Test
	void experimentFluxReadFile() throws IOException {
		Path filePath = Path.of(TestConstant.prefixFileName + "_file.txt");
		Files.deleteIfExists(filePath);
		
		Files.createFile(filePath);
		Files.write(filePath, "Hello this is sample file \n".getBytes(), StandardOpenOption.APPEND);
		Files.write(filePath, "Hello this is content \n".getBytes(), StandardOpenOption.APPEND);
		Files.write(filePath, "Hello this is end file \n".getBytes(), StandardOpenOption.APPEND);
		
		Flux<Object> stringFlux = Flux.using(
			() -> Files.lines(filePath),
			Flux::fromStream,
			Stream::close
		);
		
		stringFlux.subscribe(System.out::println);
		Files.deleteIfExists(filePath);
	}
	
	@Test
	void givenFilePath_returnLineStream() {
		URL dataFile = getClass().getClassLoader().getResource("data.txt");
		
		FileLoadLoader loadLoader = new FileLoadLoader();
		assert dataFile != null;
		LoadRequest request = LoadRequest.builder()
			                      .source(LoadRequest.Source.builder().dataSource(dataFile.getPath()).build())
			                      .build();
		
		StepVerifier
			.create(loadLoader.load(request))
			.expectNext("1,name_tester_1,10")
			.expectNextCount(2)
			.expectComplete()
			.verify();
	}
}