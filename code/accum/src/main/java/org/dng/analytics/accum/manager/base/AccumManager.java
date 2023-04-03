package org.dng.analytics.accum.manager.base;

import org.dng.analytics.accum.handler.loader.AccumLoader;
import org.dng.analytics.accum.handler.mapper.AccumMapper;
import org.dng.analytics.accum.handler.publisher.AccumPublisher;
import org.dng.analytics.accum.constant.type.ManagerType;
import org.dng.analytics.accum.model.LoadRequest;
import org.dng.analytics.accum.model.LoadResponse;
import org.dng.analytics.accum.support.LoadResponseFactory;
import reactor.core.publisher.Mono;

public abstract class AccumManager<I, M, O> {

    protected AccumLoader<I> loader;
    protected AccumMapper<I, M> mapper;
    protected AccumPublisher<M, O> publisher;

    public abstract ManagerType type();

    public Mono<LoadResponse> handle(LoadRequest request) {

        return loader.load(request)
                .transform(s -> mapper.process(s, request.getSchema()))
                .transform(s -> publisher.publish(s))
                .reduce(LoadResponseFactory.successLoadRes(), (loadResponse, o) -> loadResponse.incRec())
                .onErrorResume(throwable -> Mono.just(LoadResponseFactory.errorLoadRes(throwable)));
    }
}
