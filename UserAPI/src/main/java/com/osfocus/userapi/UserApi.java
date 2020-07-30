package com.osfocus.userapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/User")
public interface UserApi {
    @GetMapping("/alive")
    public String alive();

    @PostMapping("/postPerson")
    public Person postPerson(@RequestBody Person person);
}
