package com.example.tasktodo.controller;

import com.example.tasktodo.config.AppConfiguration;
import com.example.tasktodo.config.IAppConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    final private IAppConfiguration configuration;

    public HomeController(IAppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("pageSize", configuration.getPageSize());
        model.addAttribute("pageStart", configuration.getPageStart());

        return "index";
    }


}
