package com.djh.sampleit.memory.collector;

import com.djh.sampleit.memory.model.MemoryMetric;
import com.djh.sampleit.memory.oshi.OSHIMemoryMetric;
import com.djh.sampleit.memory.oshi.OSHIMemoryMetricSource;
import com.djh.sampleit.metadata.MetricMetadata;
import com.djh.sampleit.metadata.MetricMetadataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author David Hancock
 */
public class DefaultMemoryMetricCollector implements MemoryMetricCollector {

    @Autowired
    private OSHIMemoryMetricSource oshiMemoryMetricSource;

    @Autowired
    private MetricMetadataService metricMetadataService;

    @Override
    public MemoryMetric collectMemoryMetric() {

        MetricMetadata metricMetadata = metricMetadataService.collectMetricMetadata();

        OSHIMemoryMetric oshiMemoryMetric = oshiMemoryMetricSource.retrieveMemoryMetric();

        return new MemoryMetric(metricMetadata,
                oshiMemoryMetric.getTotalMemory(), oshiMemoryMetric.getTotalAvailableMemory());
    }

}
