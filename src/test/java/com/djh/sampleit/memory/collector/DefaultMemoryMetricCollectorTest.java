package com.djh.sampleit.memory.collector;

import com.djh.sampleit.memory.model.MemoryMetric;

import com.djh.sampleit.memory.oshi.OSHIMemoryMetric;
import com.djh.sampleit.memory.oshi.OSHIMemoryMetricSource;
import com.djh.sampleit.metadata.MetricMetadata;
import com.djh.sampleit.metadata.MetricMetadataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author David Hancock
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultMemoryMetricCollectorTest {

    private static final long TOTAL_MEMORY = 1000;

    private static final long AVAILABLE_MEMORY = 200;

    @Mock
    private MetricMetadataService metricMetadataService;

    @Mock
    private OSHIMemoryMetricSource oshiMemoryMetricSource;

    @InjectMocks
    private MemoryMetricCollector memoryMetricCollector = new DefaultMemoryMetricCollector();

    private OSHIMemoryMetric memoryMetric = new OSHIMemoryMetric(TOTAL_MEMORY, AVAILABLE_MEMORY);

    @Test
    public void testCollectMemoryMetric() {

        Mockito.when(oshiMemoryMetricSource.retrieveMemoryMetric()).thenReturn(memoryMetric);

        MemoryMetric memoryMetric = memoryMetricCollector.collectMemoryMetric();

        Mockito.verify(metricMetadataService).collectMetricMetadata();
        Assert.assertEquals(TOTAL_MEMORY, memoryMetric.getTotalMemory());
        Assert.assertEquals(AVAILABLE_MEMORY, memoryMetric.getTotalAvailableMemory());
    }

}