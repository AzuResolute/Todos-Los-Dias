package org.example.config.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.config.service.DemoService;
import org.example.config.service.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DemoController {

    private final DemoService demoService;

    @Autowired
    public DemoController() {
        this.demoService = new DemoServiceImpl();
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    // todos-list-dias/welcome?user=Cookie$age=37
    @GetMapping("welcome")
    public String welcome(@RequestParam String user, @RequestParam int age , Model model) {
        // whatever is returned is what is displayed
        model.addAttribute("helloMessage", demoService.getHelloMessage(user));
        model.addAttribute("age", age);
        log.info("model = {}", model);
        return "welcome";
    }

    @ModelAttribute("welcomeMessage")
    public String welcomeMessage() {
        log.info("welcome message() called");
        return demoService.getWelcomeMessage();
    }


}
