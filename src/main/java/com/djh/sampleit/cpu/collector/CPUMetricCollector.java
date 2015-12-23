package com.djh.sampleit.cpu.collector;

import com.djh.sampleit.cpu.controller.model.CPUMetric;

/**
 * @author David Hancock
 */
public interface CPUMetricCollector {

    CPUMetric collectCPUMetric();

}
