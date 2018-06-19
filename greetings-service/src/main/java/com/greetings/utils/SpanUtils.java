package com.greetings.utils;

import io.opencensus.trace.SpanBuilder;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.samplers.Samplers;

public class SpanUtils {

    public static SpanBuilder buildSpan(Tracer tracer, String name) {
        return tracer.spanBuilder(name)
                .setRecordEvents(true)
                .setSampler(Samplers.alwaysSample());
    }
}
