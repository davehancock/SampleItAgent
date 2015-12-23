package com.djh.sampleit.metadata;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author David Hancock
 */
public class DefaultMetricMetadataService implements MetricMetadataService {

    @Override
    public MetricMetadata collectMetricMetadata() {

        String hostname = retrieveHostname();
        Date date = new Date();
        return new MetricMetadata(hostname, date);
    }

    private String retrieveHostname() {

        String hostname = "Unknown";

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostname = inetAddress.getHostName();
        } catch (UnknownHostException ex) {
            System.out.println("Hostname can not be resolved");
        }

        return hostname;
    }

}
