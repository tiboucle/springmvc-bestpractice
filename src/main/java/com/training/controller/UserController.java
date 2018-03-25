package com.training.controller;

import com.training.model.entity.User;
import com.training.service.RoleService;
import com.training.service.UserService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  private final UserService userService;
  private final RoleService roleService;

  @Autowired
  public UserController(RoleService roleService, UserService userService) {
    this.roleService = roleService;
    this.userService = userService;
  }

  @GetMapping(value = {"/login"})
  public String login(final Principal principal) {
    return principal != null ? "redirect:/welcome" : "user/login";
  }

  @GetMapping(value = "/signup")
  public String signup(Model model) {
    model.addAttribute("user", new User());
    return "user/signup";
  }

  @PostMapping(value = "/signup/add")
  public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
    boolean success = false;
    if (!result.hasErrors()) {
      user.addRole(roleService.findCode("ROLE_USER"));
      success = userService.save(user);
    }
    return success ? "user/login" : "user/signup";
  }


}