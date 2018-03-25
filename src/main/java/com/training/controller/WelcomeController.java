package com.training.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

  @Value("${spring.application.name}")
  private String applicationName;

  @RequestMapping("/")
  public String index() {
    return "redirect:/login";
  }

  @RequestMapping("/welcome")
  public String welcome() {
    return "welcome";
  }
}
