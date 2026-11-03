package com.example.demo.service;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class Gatewayservice {

    DiscoveryClient DC;
    int current = 0;

    public Gatewayservice(DiscoveryClient DC) {
        this.DC = DC;
    }

    // Invoke the service
    public Object invokeService(String service, String endpoint) {

        List<ServiceInstance> services = DC.getInstances(service);

        if (services == null || services.isEmpty()) {
            return "Service not available: " + service;
        }

        if (current >= services.size()) {
            current = 0;
        }

        ServiceInstance instance = services.get(current);

        current = (current + 1) % services.size();

        String url = String.format("%s/%s", instance.getUri(), endpoint);

        RestClient client = RestClient.create();

        return "From Port: " + instance.getPort()
                + "\n"
                + client.get()
                        .uri(url)
                        .retrieve()
                        .body(String.class);
    }
}