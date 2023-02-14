package ua.com.drabchak.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class StartingController {

    @GetMapping("/")
    public String welcomePage() {
        return "starting-page";
    }
}
