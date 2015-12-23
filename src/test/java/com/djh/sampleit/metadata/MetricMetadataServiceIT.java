package com.djh.sampleit.metadata;

import com.djh.sampleit.configuration.AgentConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author David Hancock
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AgentConfiguration.class)
public class MetricMetadataServiceIT {

    @Autowired
    private MetricMetadataService metricMetadataService;

    @Test
    public void testCollectMetricMetadata() {

        MetricMetadata metricMetadata = metricMetadataService.collectMetricMetadata();

        Assert.assertNotNull(metricMetadata.getHostname());
        Assert.assertNotNull(metricMetadata.getDate());
    }

}
