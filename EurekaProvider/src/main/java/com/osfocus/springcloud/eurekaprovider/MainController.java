package com.osfocus.springcloud.eurekaprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    HealthStatusService hsrv;

    @GetMapping("/hi")
    public Object getHi() {
        return "hi";
    }

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {
        hsrv.setStatus(status);
        return String.valueOf(hsrv.getStatus());
    }
}
