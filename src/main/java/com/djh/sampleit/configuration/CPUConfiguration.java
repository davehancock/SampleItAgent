package com.djh.sampleit.configuration;

import com.djh.sampleit.cpu.collector.CPUMetricCollector;
import com.djh.sampleit.cpu.collector.DefaultCPUMetricCollector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.MessageHandler;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author David Hancock
 */
@Configuration
public class CPUConfiguration {

    @Bean
    public CPUMetricCollector cpuMetricCollector() {
        return new DefaultCPUMetricCollector();
    }

    // TODO There may be an annotation supported method of achieving this, service activator-esque?
    // Define the CPUMetricCollector as a message source
    @Bean
    public MessageSource cpuMetricMessageSource(CPUMetricCollector cpuMetricCollector) {
        MethodInvokingMessageSource methodInvokingMessageSource = new MethodInvokingMessageSource();
        methodInvokingMessageSource.setObject(cpuMetricCollector);
        methodInvokingMessageSource.setMethodName("collectCPUMetric");
        return methodInvokingMessageSource;
    }

    // Spring Integration DSL doesn't support HTTP namespace yet so we have to declare the endpoint explicitly like below.
    @Bean
    public MessageHandler httpGateway(@Value("${sampleit.server.uri}/cpu") URI uri) {
        HttpRequestExecutingMessageHandler httpHandler = new HttpRequestExecutingMessageHandler(uri);
        httpHandler.setExpectReply(false);
        return httpHandler;
    }

    // The definition of this application's integration flow, essentially the stitching together of components.
    @Bean
    public IntegrationFlow cpuMetricFlow(MessageSource cpuMetricMessageSource, MessageHandler httpGateway) {

        Map<String, Object> httpHeaders = new HashMap<>();
        httpHeaders.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        // TODO Integrate MetricMetadata service into this flow instead of injecting it into the specific collector implementation
        return IntegrationFlows
                .from(cpuMetricMessageSource, p -> p.poller(Pollers.fixedRate(500)))
                .enrichHeaders(httpHeaders)
                .handle(httpGateway)
                .get();
    }

}
