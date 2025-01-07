package com.mysite.lect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mainController {

    @GetMapping("/")
    @ResponseBody
    public void home() {
        System.out.println("home");
    }

    @GetMapping("/main")
    @ResponseBody
    public String main() {
        return "index";
    }
}
