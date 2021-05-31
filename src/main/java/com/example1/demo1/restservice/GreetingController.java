package com.example1.demo1.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    @GetMapping("/Greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world")
                             String name){
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }

}
