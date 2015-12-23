package com.djh.sampleit.cpu.oshi;

/**
 * @author David Hancock
 */
public class OSHICPUMetric {

    private int numberOfCores;

    private double[] cpuClockSpeed;

    public OSHICPUMetric(int numberOfCores, double[] cpuClockSpeed) {
        this.numberOfCores = numberOfCores;
        this.cpuClockSpeed = cpuClockSpeed;
    }

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public void setNumberOfCores(int numberOfCores) {
        this.numberOfCores = numberOfCores;
    }

    public double[] getCpuClockSpeed() {
        return cpuClockSpeed;
    }

    public void setCpuClockSpeed(double[] cpuClockSpeed) {
        this.cpuClockSpeed = cpuClockSpeed;
    }

}
