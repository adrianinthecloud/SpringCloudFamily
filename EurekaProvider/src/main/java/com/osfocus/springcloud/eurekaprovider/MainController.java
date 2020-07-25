package com.osfocus.springcloud.eurekaprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class MainController {
    @Autowired
    HealthStatusService hsrv;

    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    public Object getHi() {
        return "hi, my port is " + port;
    }

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {
        hsrv.setStatus(status);
        return String.valueOf(hsrv.getStatus());
    }

    @GetMapping("/getMap")
    public Map<String, String> getMap() {
        return Collections.singletonMap("id", "100");
    }

    @GetMapping("/person")
    public Person getPerson() {
        return new Person("Adrian", 18);
    }

    @GetMapping("/getObj")
    public Person getObj(@RequestParam String name) {
        return new Person("Adrian", 20);
    }

    @PostMapping("/person")
    public Person newPerson(@RequestBody Person person) {
        System.out.println("person = " + person);

        return person;
    }
}
