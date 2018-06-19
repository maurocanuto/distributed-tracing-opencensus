package com.greetings.rest;

import com.greetings.service.GreetingsService;
import com.greetings.utils.SpanUtils;
import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    private static final Tracer tracer = Tracing.getTracer();

    private final GreetingsService greetingsService;

    @Autowired
    public GreetingsController(GreetingsService greetingsService) {
        this.greetingsService = greetingsService;
    }

    @GetMapping("/hello")
    public String getHello() {
        Span span = SpanUtils.buildSpan(tracer, "GET /greetings/hello").startSpan();
        span.addAnnotation("GreetingsController getHello");
        String result;

        try (Scope ws = tracer.withSpan(span)) {
            result = greetingsService.printHello();
        }

        span.end();
        return result;
    }

}
