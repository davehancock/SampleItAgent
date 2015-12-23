package com.djh.sampleit.cpu.collector;


import com.djh.sampleit.cpu.controller.model.CPUMetric;
import com.djh.sampleit.cpu.oshi.OSHICPUMetric;
import com.djh.sampleit.cpu.oshi.OSHIMetricSource;
import com.djh.sampleit.metadata.MetricMetadata;
import com.djh.sampleit.metadata.MetricMetadataService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

/**
 * @author David Hancock
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultCPUMetricCollectorTest {

    private static final int NUMBER_OF_CORES = 4;

    private static final double[] CPU_CLOCK_SPEEDS = {2.33, 1.11, 2.22, 4.00};

    private OSHICPUMetric oshicpuMetric = new OSHICPUMetric(NUMBER_OF_CORES, CPU_CLOCK_SPEEDS);

    private MetricMetadata metricMetadata = new MetricMetadata("Foo", new Date());

    @InjectMocks
    private CPUMetricCollector cpuMetricCollector = new DefaultCPUMetricCollector();

    @Mock
    private OSHIMetricSource oshiMetricSource;

    @Mock
    private MetricMetadataService metricMetadataService;

    @Test
    public void testCollectCPUMetric() {

        Mockito.when(oshiMetricSource.readOSHICPUMetric()).thenReturn(oshicpuMetric);
        Mockito.when(metricMetadataService.collectMetricMetadata()).thenReturn(metricMetadata);

        CPUMetric cpuMetric = cpuMetricCollector.collectCPUMetric();

        Assert.assertEquals(CPU_CLOCK_SPEEDS[0], cpuMetric.getCpuCores().get(0).getCpuClockSpeed(), 0.001);
        Assert.assertEquals(CPU_CLOCK_SPEEDS[1], cpuMetric.getCpuCores().get(1).getCpuClockSpeed(), 0.001);
        Assert.assertEquals(CPU_CLOCK_SPEEDS[2], cpuMetric.getCpuCores().get(2).getCpuClockSpeed(), 0.001);
        Assert.assertEquals(CPU_CLOCK_SPEEDS[3], cpuMetric.getCpuCores().get(3).getCpuClockSpeed(), 0.001);
    }

}
