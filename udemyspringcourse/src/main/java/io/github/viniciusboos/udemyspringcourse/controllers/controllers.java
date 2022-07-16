package io.github.viniciusboos.udemyspringcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controllers {

    @Autowired
    @Qualifier("applicationName")
    private String applicationName;

    @GetMapping("/hello")
    public String helloworld(){
        return applicationName;
    }
}
