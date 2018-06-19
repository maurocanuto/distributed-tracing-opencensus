package com.hello.service;

import com.hello.utils.SpanUtils;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    private static final Logger LOG = LoggerFactory.getLogger(HelloService.class);
    private static final Tracer tracer = Tracing.getTracer();

    public String printHello() {
        Span span = SpanUtils.buildSpan(tracer,"HelloService printHello").startSpan();
        String helloStr = "Hello from Service";
        LOG.info("Printing hello");
        span.end();
        return helloStr;
    }
}
