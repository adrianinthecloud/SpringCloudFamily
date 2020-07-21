package com.osfocus.springcloud.eurekaprovider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/hi")
    public Object getHi() {
        return "hi";
    }
}
