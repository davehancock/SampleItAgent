package com.djh.sampleit.cpu.oshi;

import oshi.SystemInfo;
import oshi.hardware.Processor;

/**
 * @author David Hancock
 */
public class OSHIMetricSource {

    // TODO Spring bean it?
    private SystemInfo systemInfo = new SystemInfo();

    public OSHICPUMetric readOSHICPUMetric() {

        Processor[] processors = systemInfo.getHardware().getProcessors();

        double[] cpuLoads = new double[processors.length];
        for (Processor processor : processors) {

            double processorTickSpeed = processor.getProcessorCpuLoadBetweenTicks() * 100;
            double formattedProcessorTickSpeed = Math.round(processorTickSpeed * 100) / 100;

            cpuLoads[processor.getProcessorNumber()] = formattedProcessorTickSpeed;
        }

        OSHICPUMetric oshicpuMetric = new OSHICPUMetric(processors.length, cpuLoads);
        return oshicpuMetric;
    }

}
