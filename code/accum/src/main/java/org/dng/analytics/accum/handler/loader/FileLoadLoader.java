package org.dng.analytics.accum.handler.loader;

import org.dng.analytics.accum.constant.type.SourceType;
import org.dng.analytics.accum.model.LoadRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class FileLoadLoader implements AccumLoader<String> {
	
	@Override
	public SourceType source() {
		return SourceType.FILE;
	}
	
	@Override
	public Flux<String> load(LoadRequest request) {
		
		long from = request.getRange().getFrom();
		long to = request.getRange().getTo();
		
		// For file query is file path
		String filePath = request.getSource().getDataSource();
		Path path = Path.of(filePath);
		
		if (!Files.exists(path)) {
			return Flux.error(new FileNotFoundException(filePath));
		} else {
			return Flux.using(
					() -> Files.lines(path),
					Flux::fromStream,
					Stream::close
				).skip(from)
				.take(to - from);
		}
	}
}
