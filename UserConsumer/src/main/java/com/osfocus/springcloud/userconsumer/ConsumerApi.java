package com.osfocus.springcloud.userconsumer;

import com.osfocus.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "user-provider", fallbackFactory = UserProviderFallBackFactory.class)
public interface ConsumerApi extends UserApi {
    // this GetMapping is for Feign to construct API with provider mentioned above, i.e. user-provider/getMap here
    @GetMapping("/getMap")
    Map<Integer, String> getMap(@RequestParam("id") Integer id);
    @GetMapping("/getMap2")
    Map<Integer, String> getMap2(@RequestParam("id") Integer id,@RequestParam("name") String name);

    @GetMapping("/getMap3")
    Map<Integer, String> getMap3(@RequestParam Map<String, Object> map);

    @PostMapping("/postMap")
    Map<Integer, String> postMap(Map<String, Object> map);

}
