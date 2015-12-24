package com.djh.sampleit.metadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author David Hancock
 */
public class DefaultMetricMetadataService implements MetricMetadataService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultMetricMetadataService.class);

    @Override
    public MetricMetadata collectMetricMetadata() {
        String hostname = retrieveHostname();
        return new MetricMetadata(hostname, new Date());
    }

    private String retrieveHostname() {

        String hostname = "Unknown";

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostname = inetAddress.getHostName();
        } catch (UnknownHostException ex) {
            LOG.info("Hostname can not be resolved");
        }

        return hostname;
    }

}
