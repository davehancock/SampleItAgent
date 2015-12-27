package com.djh.sampleit.cpu.collector;

import com.djh.sampleit.cpu.controller.model.CPUCore;
import com.djh.sampleit.cpu.controller.model.CPUMetric;
import com.djh.sampleit.cpu.oshi.OSHICPUMetric;
import com.djh.sampleit.cpu.oshi.OSHICPUMetricSource;
import com.djh.sampleit.metadata.MetricMetadata;
import com.djh.sampleit.metadata.MetricMetadataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Hancock
 */
public class DefaultCPUMetricCollector implements CPUMetricCollector {

    @Autowired
    private OSHICPUMetricSource OSHICPUMetricSource;

    @Autowired
    private MetricMetadataService metricMetadataService;

    @Override
    public CPUMetric collectCPUMetric() {

        MetricMetadata metricMetadata = metricMetadataService.collectMetricMetadata();

        OSHICPUMetric oshicpuMetric = OSHICPUMetricSource.readOSHICPUMetric();
        return new CPUMetric(metricMetadata, processCPUCores(oshicpuMetric));
    }

    private List<CPUCore> processCPUCores(OSHICPUMetric oshicpuMetric) {

        List<CPUCore> cpuCores = new ArrayList<>();
        for (int i = 0; i < oshicpuMetric.getNumberOfCores(); i++) {
            CPUCore cpuCore = new CPUCore();
            cpuCore.setCpuClockSpeed(oshicpuMetric.getCpuClockSpeed()[i]);
            cpuCores.add(cpuCore);
        }

        return cpuCores;
    }

}
