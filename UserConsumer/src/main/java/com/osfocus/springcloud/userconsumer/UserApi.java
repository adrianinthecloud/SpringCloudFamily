package com.osfocus.springcloud.userconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-provider", url = "http://localhost:8083")
public interface UserApi {
    @GetMapping("/alive")
    public String alive();
}
