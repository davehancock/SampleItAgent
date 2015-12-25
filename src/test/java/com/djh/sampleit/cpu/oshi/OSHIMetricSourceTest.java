package com.djh.sampleit.cpu.oshi;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Processor;

/**
 * @author David Hancock
 */
@RunWith(MockitoJUnitRunner.class)
public class OSHIMetricSourceTest {

    private static final double CPU_TICK = 0.123412122121;

    @InjectMocks
    private OSHIMetricSource oshiMetricSource = new OSHIMetricSource();

    @Mock
    private SystemInfo systemInfo;

    @Test
    public void testReadOSHICPUMetric() {

        // Mock Processor
        Processor processorMock = Mockito.mock(Processor.class);
        Mockito.when(processorMock.getProcessorCpuLoadBetweenTicks()).thenReturn(CPU_TICK);
        Processor[] processors = new Processor[]{processorMock};

        // Mock Hardware
        HardwareAbstractionLayer hardwareAbstractionLayerMock = Mockito.mock(HardwareAbstractionLayer.class);
        Mockito.when(hardwareAbstractionLayerMock.getProcessors()).thenReturn(processors);

        // Mock System Info Call
        Mockito.when(systemInfo.getHardware()).thenReturn(hardwareAbstractionLayerMock);

        OSHICPUMetric oshicpuMetric = oshiMetricSource.readOSHICPUMetric();
        Assert.assertEquals(1, oshicpuMetric.getCpuClockSpeed().length);
        Assert.assertEquals(12.34, oshicpuMetric.getCpuClockSpeed()[0], 0.1);
    }

}