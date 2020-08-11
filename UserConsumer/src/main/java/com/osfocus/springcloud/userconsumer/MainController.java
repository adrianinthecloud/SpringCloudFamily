package com.osfocus.springcloud.userconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.osfocus.userapi.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class MainController {
    @Autowired
    ConsumerApi api;

    @Autowired
    RestService restService;

    @Value("${server.port}")
    String port;

    @GetMapping("/alive")
    public Object getAlive() {
        return restService.alive();
    }

    @GetMapping("/map")
    public Map<Integer, String> map(Integer id) {
        System.out.println(id);
        return api.getMap(id);
    }

    @GetMapping("/map2")
    public Map<Integer, String> map2(Integer id,String name) {
        System.out.println(id);
        return api.getMap2(id,name);
    }


    @GetMapping("/map3")
    public Map<Integer, String> map3(@RequestParam Map<String, Object> map) {

        System.out.println(map);
        return api.getMap3(map);
    }


    @GetMapping("/map4")
    public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {

        System.out.println(map);
        return api.postMap(map);
    }

    @GetMapping("/postMap")
    public Person postPerson(@RequestParam Map<String, Object> map) {
        Person person = new Person();
        person.setId(Integer.valueOf(map.get("id").toString()));
        person.setName(map.get("name").toString());

        return api.postPerson(person);
    }

    @GetMapping("/alive2")
    @HystrixCommand(defaultFallback = "back")
    public String alive2() {
        return "Consumer: " + port + "->>>>" + restService.alive();
    }

    public String back() {
        return "fallback";
    }
}
