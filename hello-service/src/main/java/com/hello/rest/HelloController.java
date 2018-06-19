package com.hello.rest;

import com.hello.service.HelloService;
import com.hello.utils.SpanUtils;
import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloController {
    private static final Tracer tracer = Tracing.getTracer();

    private final HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    public String getHello() {
        Span span = SpanUtils.buildSpan(tracer,"HelloController getHello").startSpan();
        String result;
        try (Scope ws = tracer.withSpan(span)) {
            result = helloService.printHello();
        }
        span.end();
        return result;
    }
}
