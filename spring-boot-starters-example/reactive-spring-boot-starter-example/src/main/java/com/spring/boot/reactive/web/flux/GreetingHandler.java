package com.spring.boot.reactive.web.flux;

import com.spring.boot.reactive.web.flux.entity.Greeting;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * Create a WebFlux Handler
 * WebFlux处理器
 *
 * @author guang.yi
 * @since 2023/8/13
 */
@Component("greetingHandler")
public class GreetingHandler {

    /**
     * <pre>
     * <a href="http://localhost:8080/hello">http://localhost:8080/hello</a>
     *
     * {"message":"Hello, Spring!"}
     * </pre>
     *
     * @param request 请求信息
     * @return 响应信息
     */
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hello, Spring!")))
                ;
    }
}
