package com.greetings.service;

import com.greetings.utils.HttpUtils;
import com.greetings.utils.SpanUtils;
import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Status;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class GreetingsService {
    private static final Logger LOG = LoggerFactory.getLogger(GreetingsService.class);
    private static final Tracer tracer = Tracing.getTracer();
    private final String helloServiceHost;

    @Autowired
    public GreetingsService(@Value("${service.helloHost}") String helloServiceHost) {
        this.helloServiceHost = helloServiceHost;
    }

    public String printHello() {
        LOG.info("printHello");
        Span span = SpanUtils.buildSpan(tracer, "GreetingsService printHello").startSpan();
        String result;
        String url = "http://" + helloServiceHost + ":8888/hello";

        try (Scope ws = tracer.withSpan(span)) {
            result = HttpUtils.callEndpoint(url, HttpMethod.GET);
        } catch (Exception e) {
            span.setStatus(Status.ABORTED);
            span.addAnnotation("Error while calling service");
            LOG.error("Error while calling service: {}", e.getMessage());
            result = "";
        }
        span.end();
        return result;
    }
}
