package ru.ikv.qa.auto.spring_web.restControllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/hello")
public class Test {
    @GetMapping
    public String printHello() {
        return "hello";
    }
    @PostMapping("/test")
    public int test(@RequestBody String a) {
       return a.length();
    }
}
