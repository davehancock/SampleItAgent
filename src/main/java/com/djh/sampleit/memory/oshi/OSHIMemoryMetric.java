package com.djh.sampleit.memory.oshi;

/**
 * @author David Hancock
 */
public class OSHIMemoryMetric {

    private long totalMemory;

    private long totalAvailableMemory;

    public OSHIMemoryMetric(long totalMemory, long totalAvailableMemory) {
        this.totalMemory = totalMemory;
        this.totalAvailableMemory = totalAvailableMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public long getTotalAvailableMemory() {
        return totalAvailableMemory;
    }

}
