package com.greetings.config;

import io.opencensus.exporter.trace.jaeger.JaegerTraceExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaegerConfig {

    public JaegerConfig(@Value("${tracing.jaegerUrl}") String jaegerThriftEndpoint) {
        JaegerTraceExporter.createAndRegister(jaegerThriftEndpoint, "greetings-service");
    }
}
