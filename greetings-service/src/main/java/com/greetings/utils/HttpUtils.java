package com.greetings.utils;

import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.propagation.TextFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

    private static final Tracer tracer = Tracing.getTracer();
    private static final TextFormat textFormat = Tracing.getPropagationComponent().getB3Format();
    private static final TextFormat.Setter setter = new TextFormat.Setter<HttpURLConnection>() {
        public void put(HttpURLConnection carrier, String key, String value) {
            carrier.setRequestProperty(key, value);
        }
    };

    public static String callEndpoint(String url, HttpMethod method) throws Exception {
        LOG.info("Calling endpoint: {}, method: {}", url, method);
        StringBuilder result = new StringBuilder();
        Span span = SpanUtils.buildSpan(tracer, "Request sent").startSpan();

        try (Scope s = tracer.withSpan(span)) {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            textFormat.inject(span.getContext(), conn, setter);

            conn.setRequestMethod(method.name());

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        }
        span.end();
        LOG.info("Response: {}", result.toString());
        return result.toString();
    }

}
