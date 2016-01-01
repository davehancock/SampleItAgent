package com.djh.sampleit.metadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author David Hancock
 */
public class DefaultMetricMetadataService implements MetricMetadataService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultMetricMetadataService.class);

    static final String UNKNOWN_VALUE = "UnknownValue";

    @Override
    public MetricMetadata collectMetricMetadata() {

        String hostname = retrieveHostname();
        String macAddress = retrieveMacAddress();
        String operatingSystem = retrieveOperatingSystem();

        return new MetricMetadata(new Date(), hostname, macAddress, operatingSystem);
    }

    private String retrieveOperatingSystem() {
        return System.getProperty("os.name");
    }

    private String retrieveMacAddress() {

        String macAddress = UNKNOWN_VALUE;

        try {
            InetAddress ipAddr = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ipAddr);
            macAddress = formatMacByteArrayToString(networkInterface.getHardwareAddress());

            LOG.info("Network Adapter: " + networkInterface.getDisplayName());
            LOG.info("Mac Address: " + macAddress);

        } catch (SocketException | UnknownHostException e) {
            LOG.info("MAC Address can not be resolved");
        }

        return macAddress;
    }

    // TODO This is abit nasty
    private String formatMacByteArrayToString(byte[] macAddr) throws UnknownHostException {

        try {
            StringBuilder sb = new StringBuilder(18);
            for (byte b : macAddr) {
                if (sb.length() > 0)
                    sb.append(':');
                sb.append(String.format("%02x", b));
            }

            return sb.toString().toUpperCase();

        } catch (NullPointerException e) {
            throw new UnknownHostException("Could not determine a valid MAC address from derived IP Address");
        }
    }

    private String retrieveHostname() {

        String hostname = UNKNOWN_VALUE;

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostname = inetAddress.getHostName();
        } catch (UnknownHostException ex) {
            LOG.info("Hostname can not be resolved");
        }

        return hostname;
    }

}
