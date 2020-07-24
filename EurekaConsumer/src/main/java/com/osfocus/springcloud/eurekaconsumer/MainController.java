package com.osfocus.springcloud.eurekaconsumer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    DiscoveryClient client;

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient client2;

    @Autowired
    LoadBalancerClient lb;

    @Autowired
    RestTemplate restTemplate;

    @Value("${provider}")
    String provider;

    @GetMapping("/getHi")
    public String getHi() {
        return "Hi";
    }

    @GetMapping("/client")
    public String client() {
        List<String> services = client.getServices();
        for (String service : services) {
            System.out.println(service);
        }

        System.out.println(ToStringBuilder.reflectionToString(services));
        return "hi";
    }

    @GetMapping(value = "/client2", produces = "application/json")
    public Object client2() {
        return client.getInstances("eureka-provider");
    }

    @GetMapping(value = "/client3")
    public Object client3() {
        List<ServiceInstance> instances = client.getInstances("eureka-provider");
        for (ServiceInstance instance : instances) {
            System.out.println(ToStringBuilder.reflectionToString(instance));
        }

        ServiceInstance provider = instances.get(0);
        String url = "http://" + provider.getHost() + ":" + provider.getPort() + "/hi";

        RestTemplate restTemplate = new RestTemplate();

        String resStr = "result: " + restTemplate.getForObject(url, String.class);

        return resStr;
    }

    @GetMapping(value = "/client4")
    public Object client4() {
        List<InstanceInfo> instances = client2.getInstancesByVipAddress("eureka-provider", false);

        if (instances.size() > 0) {
            InstanceInfo instanceInfo = instances.get(0);

            if (instanceInfo.getStatus() == InstanceInfo.InstanceStatus.UP) {
                String url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/hi";
                RestTemplate restTemplate = new RestTemplate();

                String resStr = "result: " + restTemplate.getForObject(url, String.class);

                return resStr;
            }
        }

        return null;
    }

    @GetMapping(value = "/client5")
    public Object client5() {
        ServiceInstance provider = lb.choose("eureka-provider");

        String url = "http://" + provider.getHost() + ":" + provider.getPort() + "/hi";

        RestTemplate restTemplate = new RestTemplate();

        String resStr = "result: " + restTemplate.getForObject(url, String.class);

        return resStr;
    }

    @GetMapping(value = "/client6")
    public Object client6() {
        String url = "http://eureka-provider/hi";
        String resStr = "result: " + restTemplate.getForObject(url, String.class);

        return resStr;
    }

    @GetMapping(value = "/client7")
    public ResponseEntity<String> client7() {
        String url = "http://" + provider + "/hi";

        ResponseEntity<String> resEntity = restTemplate.getForEntity(url, String.class);
        System.out.println(resEntity);
        return resEntity;
    }
}
