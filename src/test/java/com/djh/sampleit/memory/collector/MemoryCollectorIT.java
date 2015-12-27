package com.djh.sampleit.memory.collector;

import com.djh.sampleit.configuration.AgentConfiguration;
import com.djh.sampleit.configuration.CPUConfiguration;
import com.djh.sampleit.configuration.MemoryConfiguration;
import com.djh.sampleit.memory.model.MemoryMetric;
import com.djh.sampleit.metadata.MetricMetadata;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author David Hancock
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AgentConfiguration.class, MemoryConfiguration.class})
@TestPropertySource(properties = {"sampleit.server.uri=http://localhost:3337"})
public class MemoryCollectorIT {

    @Autowired
    private MemoryMetricCollector memoryMetricCollector;

    @Test
    public void testCollectMemoryMetric() {

        MemoryMetric memoryMetric = memoryMetricCollector.collectMemoryMetric();

        MetricMetadata metricMetadata = memoryMetric.getMetricMetadata();
        Assert.assertNotNull(metricMetadata.getDate());
        Assert.assertNotNull(metricMetadata.getHostname());

        Assert.assertTrue(memoryMetric.getTotalMemory() > 1000);
        Assert.assertTrue(memoryMetric.getTotalAvailableMemory() > 1000);
        Assert.assertTrue(memoryMetric.getTotalMemory() > memoryMetric.getTotalAvailableMemory());
    }

}
