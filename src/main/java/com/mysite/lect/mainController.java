package com.mysite.lect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mainController {

    @GetMapping("/main")
    @ResponseBody
    public String main() {
        return "index";
    }


    @GetMapping("/")
    public String root(){
        return "redirect:/question/list";
    }
}
