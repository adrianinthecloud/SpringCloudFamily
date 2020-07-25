package com.osfocus.springcloud.userconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    UserApi api;

    @GetMapping("/alive")
    public Object getAlive() {
        return api.alive();
    }
}
