package com.osfocus.springcloud.userconsumer;

import com.osfocus.userapi.Person;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserProviderFallBack implements ConsumerApi {
    @Override
    public String alive() {
        return "Degrade";
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

    @Override
    public Person postPerson(Person person) {
        return null;
    }
}
