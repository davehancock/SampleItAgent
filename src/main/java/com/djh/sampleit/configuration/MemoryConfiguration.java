package com.djh.sampleit.configuration;

import com.djh.sampleit.cpu.collector.CPUMetricCollector;
import com.djh.sampleit.memory.collector.DefaultMemoryMetricCollector;
import com.djh.sampleit.memory.collector.MemoryMetricCollector;
import com.djh.sampleit.memory.oshi.OSHIMemoryMetricSource;
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
public class MemoryConfiguration {

    @Bean
    public MemoryMetricCollector memoryMetricCollector() {
        return new DefaultMemoryMetricCollector();
    }

    @Bean
    public OSHIMemoryMetricSource oshiMemoryMetricSource() {
        return new OSHIMemoryMetricSource();
    }

    @Bean
    public MessageSource memoryMetricMessageSource(MemoryMetricCollector memoryMetricCollector) {
        MethodInvokingMessageSource methodInvokingMessageSource = new MethodInvokingMessageSource();
        methodInvokingMessageSource.setObject(memoryMetricCollector);
        methodInvokingMessageSource.setMethodName("collectMemoryMetric");
        return methodInvokingMessageSource;
    }

    @Bean
    public MessageHandler memoryHttpGateway(@Value("${sampleit.server.uri}/memory") URI uri) {
        HttpRequestExecutingMessageHandler httpHandler = new HttpRequestExecutingMessageHandler(uri);
        httpHandler.setExpectReply(false);
        return httpHandler;
    }

    // The definition of this application's integration flow, essentially the stitching together of components.
    @Bean
    public IntegrationFlow memoryMetricFlow(MessageSource memoryMetricMessageSource, MessageHandler memoryHttpGateway) {

        Map<String, Object> httpHeaders = new HashMap<>();
        httpHeaders.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        return IntegrationFlows
                .from(memoryMetricMessageSource, p -> p.poller(Pollers.fixedRate(1000)))
                .enrichHeaders(httpHeaders)
                .handle(memoryHttpGateway)
                .get();
    }

}
