package com.osfocus.springcloud.userconsumer;

import com.osfocus.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-provider")
public interface ConsumerApi extends UserApi {
}
