package com.djh.sampleit.memory.collector;

import com.djh.sampleit.memory.model.MemoryMetric;

/**
 * @author David Hancock
 */
public interface MemoryMetricCollector {

    MemoryMetric collectMemoryMetric();

}
