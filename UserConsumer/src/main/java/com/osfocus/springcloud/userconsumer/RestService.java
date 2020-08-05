package com.osfocus.springcloud.userconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.osfocus.userapi.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RestService implements ConsumerApi {
    @Autowired
    RestTemplate template;

    @Override
    public Map<Integer, String> getMap(Integer id) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap2(Integer id, String name) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap3(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<Integer, String> postMap(Map<String, Object> map) {
        return null;
    }

    @Override
    @HystrixCommand(defaultFallback = "back")
    public String alive() {
        String url = "http://user-provider/alive";

        String str = template.getForObject(url, String.class);

        return str;
    }

    public String back() {
        return "Fallback";
    }

    @Override
    public Person postPerson(Person person) {
        return null;
    }
}
