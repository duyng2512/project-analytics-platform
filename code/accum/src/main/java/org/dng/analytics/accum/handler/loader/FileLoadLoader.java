package org.dng.analytics.accum.handler.loader;

import org.dng.analytics.accum.constant.type.SourceType;
import org.dng.analytics.accum.model.LoadRequest;
import org.dng.analytics.accum.model.LoadResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;
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
        URL fileURL = this.getClass().getResource(filePath);
        if (fileURL == null) return Flux.error(new NullPointerException("URL is Null"));
        Path path = Path.of(fileURL.getPath());

        if (!Files.exists(path)) {
            return Flux.error(new FileNotFoundException(filePath));
        } else {
            return Flux.using(() -> Files.lines(path), Flux::fromStream, Stream::close).skip(from).take(to - from);
        }
    }
}
