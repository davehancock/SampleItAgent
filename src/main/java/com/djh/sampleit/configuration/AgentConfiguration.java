package com.djh.sampleit.configuration;

import com.djh.sampleit.cpu.oshi.OSHIMetricSource;
import com.djh.sampleit.metadata.DefaultMetricMetadataService;
import com.djh.sampleit.metadata.MetricMetadataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import oshi.SystemInfo;

/**
 * @author David Hancock
 */
@Configuration
public class AgentConfiguration {

    @Bean
    public SystemInfo systemInfo(){
        return new SystemInfo();
    }

    @Bean
    public OSHIMetricSource oshiMetricSource() {
        return new OSHIMetricSource();
    }

    @Bean
    public MetricMetadataService metricMetadataService(){
        return new DefaultMetricMetadataService();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
