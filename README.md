# OpenCensus - A stats collection and distributed tracing framework

[OpenCensus](https://opencensus.io/) is a toolkit for collecting application performance and behavior data.
Opencensus is a single distribution of libraries that automatically collects traces and metrics from your app, displays them locally, and sends them to any analysis tool.

Although OpenCensus records stats or traces, in this example we will only export traces and propagate context between 2 services.

# Exporter
Opencensus includes exporters for storage and analysis tools. Right now the list includes Zipkin, Prometheus, Jaeger, Stackdriver, and SignalFx.
In this example will use the [Jaeger](https://www.jaegertracing.io/docs/) exporter using the all-in-one docker image.

## THE EXAMPLE
In this example we are running 2 services:
 * hello-service
 * greetings-service

_Hello service_
This service will run on **port 8888** and exposes the enpoint **/hello**.
This endpoint returns the String "Hello from Service"

_Greetings service_
This service will run on **port 8080** and exposes the enpoint **/greetings/hello**.
When calling this endpoint, this service calls the _hello-service_ and returns the String received in the response.

![Example](https://image.ibb.co/b8OEFJ/example.png)

When a request is process, traces are sent to Jaeger tool where you will get a detailed view of the operation. You can access at http://localhost:16686/

![](https://image.ibb.co/fdXYsy/jaeger.png =250x250)
![](https://image.ibb.co/cVzeXy/jaeger_trace_details.png =250x250)

### Build/run the example
Example can be run with docker-compose.
**Build**:
```
docker-compose build
```

**Run**:
```
docker-compose up
```

**Call endpoint**: http://localhost:8080/greetings/hello
