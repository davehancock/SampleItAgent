package com.djh.sampleit.memory.oshi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Memory;

/**
 * @author David Hancock
 */
@RunWith(MockitoJUnitRunner.class)
public class OSHIMemoryMetricSourceTest {

    private static final long TOTAL_MEMORY = 1000;

    private static final long AVAILABLE_MEMORY = 2000;

    @Mock
    private SystemInfo systemInfo;

    @InjectMocks
    private OSHIMemoryMetricSource oshiMemoryMetricSource = new OSHIMemoryMetricSource();

    @Test
    public void testRetrieveMemoryMetric() {

        // Mock Memory
        Memory memory = Mockito.mock(Memory.class);
        Mockito.when(memory.getTotal()).thenReturn(TOTAL_MEMORY);
        Mockito.when(memory.getAvailable()).thenReturn(AVAILABLE_MEMORY);

        // Mock Hardware
        HardwareAbstractionLayer hardwareAbstractionLayerMock = Mockito.mock(HardwareAbstractionLayer.class);
        Mockito.when(hardwareAbstractionLayerMock.getMemory()).thenReturn(memory);

        // Mock System Info Call
        Mockito.when(systemInfo.getHardware()).thenReturn(hardwareAbstractionLayerMock);

        OSHIMemoryMetric oshiMemoryMetric = oshiMemoryMetricSource.retrieveMemoryMetric();
        Assert.assertEquals(1000, oshiMemoryMetric.getTotalMemory());
        Assert.assertEquals(2000, oshiMemoryMetric.getTotalAvailableMemory());
    }

}