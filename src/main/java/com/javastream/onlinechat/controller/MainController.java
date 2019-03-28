package com.javastream.onlinechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @RequestMapping("/")
    public String output() {
        return "test";
    }

    @GetMapping("/print")
    public String print(@RequestParam(name="name", required = false, defaultValue = "Test") String name, Map<String, Object> model) {
        model.put("name", name);
        return "test2";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("some", "Some test code");
        return "main";
    }
}
