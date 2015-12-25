package com.djh.sampleit.cpu.oshi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import oshi.SystemInfo;
import oshi.hardware.Processor;

/**
 * @author David Hancock
 */
public class OSHIMetricSource {

    @Autowired
    private SystemInfo systemInfo;

    public OSHICPUMetric readOSHICPUMetric() {

        Processor[] processors = systemInfo.getHardware().getProcessors();

        double[] cpuLoads = new double[processors.length];
        for (Processor processor : processors) {

            double processorTickSpeed = processor.getProcessorCpuLoadBetweenTicks() * 100;
            double formattedProcessorTickSpeed = Math.round(processorTickSpeed * 100.0) / 100.0;

            cpuLoads[processor.getProcessorNumber()] = formattedProcessorTickSpeed;
        }

        return new OSHICPUMetric(processors.length, cpuLoads);
    }

}
