package com.djh.sampleit.cpu.collector;

import com.djh.sampleit.configuration.AgentConfiguration;
import com.djh.sampleit.configuration.CPUConfiguration;
import com.djh.sampleit.cpu.controller.model.CPUCore;
import com.djh.sampleit.cpu.controller.model.CPUMetric;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author David Hancock
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AgentConfiguration.class, CPUConfiguration.class})
@TestPropertySource(properties = {"sampleit.server.uri=http://localhost:3337"})
public class CPUCollectorIT {

    @Autowired
    private CPUMetricCollector cpuMetricCollector;

    @Test
    public void testCollectCPUMetric() {
        CPUMetric cpuMetric = cpuMetricCollector.collectCPUMetric();

        List<CPUCore> cpuCores = cpuMetric.getCpuCores();

        for (CPUCore cpuCore : cpuCores) {
            Assert.assertTrue(cpuCore.getCpuClockSpeed() > 0);
        }

        int numberOfCores = cpuCores.size();
        Assert.assertTrue(numberOfCores > 0);
    }

}
