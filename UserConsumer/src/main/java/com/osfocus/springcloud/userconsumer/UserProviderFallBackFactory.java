package com.osfocus.springcloud.userconsumer;

import com.osfocus.userapi.Person;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserProviderFallBackFactory implements FallbackFactory<ConsumerApi> {
    @Override
    public ConsumerApi create(Throwable cause) {
        return new ConsumerApi() {

            @Override
            public String alive() {
                if (cause instanceof FeignException.InternalServerError) {
                    return "Remote server 500 error: " + cause.getLocalizedMessage();
                } else if(cause instanceof RuntimeException) {
                    return "Runtime Exception：" + cause;
                } else {
                    return "Refresh";
                }
            }

            @Override
            public Person postPerson(Person person) {
                return null;
            }

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
        };
    }
}
