package com.djh.sampleit.metadata;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author David Hancock
 */
public class DefaultMetricMetadataServiceTest {

    private DefaultMetricMetadataService defaultMetricMetadataService = new DefaultMetricMetadataService();

    @Test
    public void testCollectMetricMetadata() {

        // TODO Mock out system props etc
        MetricMetadata metricMetadata = defaultMetricMetadataService.collectMetricMetadata();
        Assert.assertNotNull(metricMetadata.getDate());
        Assert.assertNotNull(metricMetadata.getHostname());
        Assert.assertNotNull(metricMetadata.getMacAddress());
        Assert.assertNotNull(metricMetadata.getOperatingSystem());

        Assert.assertNotEquals(DefaultMetricMetadataService.UNKNOWN_VALUE, metricMetadata.getHostname());
        Assert.assertNotEquals(DefaultMetricMetadataService.UNKNOWN_VALUE, metricMetadata.getMacAddress());
        Assert.assertNotEquals(DefaultMetricMetadataService.UNKNOWN_VALUE, metricMetadata.getOperatingSystem());
    }

}