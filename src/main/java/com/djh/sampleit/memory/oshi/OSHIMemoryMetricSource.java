package com.djh.sampleit.memory.oshi;

import org.springframework.beans.factory.annotation.Autowired;
import oshi.SystemInfo;
import oshi.hardware.Memory;

/**
 * @author David Hancock
 */
public class OSHIMemoryMetricSource {

    @Autowired
    private SystemInfo systemInfo;

    public OSHIMemoryMetric retrieveMemoryMetric() {

        Memory memory = systemInfo.getHardware().getMemory();

        long total = memory.getTotal();
        long available = memory.getAvailable();

        return new OSHIMemoryMetric(total, available);
    }

}
