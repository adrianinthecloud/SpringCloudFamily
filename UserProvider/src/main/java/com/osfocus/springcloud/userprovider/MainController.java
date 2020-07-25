package com.osfocus.springcloud.userprovider;

import com.osfocus.userapi.UserApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController implements UserApi {
    @GetMapping("/alive")
    public String alive() {
        return "ok test";
    }
}
