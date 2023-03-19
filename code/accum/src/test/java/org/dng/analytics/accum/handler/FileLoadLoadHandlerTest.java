package org.dng.analytics.accum.handler;

import org.dng.analytics.accum.TestConstant;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

class FileLoadLoadHandlerTest {
	
	
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
}