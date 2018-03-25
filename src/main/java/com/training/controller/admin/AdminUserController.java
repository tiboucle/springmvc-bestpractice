package com.training.controller.admin;

import com.training.model.entity.Role;
import com.training.model.entity.User;
import com.training.service.RoleService;
import com.training.service.UserService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

  private final UserService userService;
  private final RoleService roleService;

  @Autowired
  public AdminUserController(RoleService roleService, UserService userService) {
    this.roleService = roleService;
    this.userService = userService;
  }

  @GetMapping
  public String getUsers(Model model) {
    model.addAttribute("userList", userService.findAll());
    return "redirect:/admin/users/pages/1";
  }

  @GetMapping("/pages/{pageNumber}")
  public String getUserPage(@PathVariable Integer pageNumber, Model model) {
    Page<User> newsPage = userService.getUserPage(pageNumber);
    int current = newsPage.getNumber() + 1;
    int begin = Math.max(1, current - 5);
    int end = Math.min(begin + 10, newsPage.getTotalPages());

    populateModelAttributePagingList(model, newsPage, current, begin, end);
    return "admin/users";
  }

  @GetMapping("/search")
  public String searchUser(Model model, @RequestParam("searchText") String searchText) {
    Page<User> newsPage = userService.findByTitleContaining(searchText);
    int current = newsPage.getNumber() + 1;
    int begin = Math.max(1, current - 5);
    int end = Math.min(begin + 10, newsPage.getTotalPages());

    populateModelAttributePagingList(model, newsPage, current, begin, end);
    return "admin/users";
  }

  @GetMapping(value = "/add")
  public String addUserForm(Model model) {
    User user = new User();
    populateModelAttributeUserForm(model, user);
    return "admin/form-page";
  }

  @PostMapping(value = "/add")
  public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result,
      Model model) {
    boolean success;
    if (!result.hasErrors()) {
      userService.save(user);
      success = true;
      model.addAttribute("userCreated", success);
    } else {
      populateModelAttributeUserForm(model, user);
      success = false;
    }
    return success ? "redirect:/admin/users?newsCreated" : "admin/form-page";
  }

  @GetMapping(value = "/{userId}")
  public String updateUserForm(@PathVariable Long userId, Model model) {
    User user = userService.findById(userId);
    if (user != null) {
      populateModelAttributeUserForm(model, user);
      return "admin/form-page";
    } else {
      return "redirect:/admin/users";
    }
  }

  @PostMapping(value = "/update")
  public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result,
      Model model) {
    boolean success;
    if (!result.hasErrors()) {
      userService.save(user);
      success = true;
      model.addAttribute("userUpdated", success);
    } else {
      populateModelAttributeUserForm(model, user);
      success = false;
    }
    return success ? "redirect:/admin/users?newsUpdated" : "admin/form-page";
  }

  @GetMapping(value = "/delete/{userId}")
  public String deleteUser(@PathVariable("userId") Long userId, Model model) {
    boolean success = userService.deleteById(userId);
    model.addAttribute("userDeleted", success);
    return "redirect:/admin/users?userDeleted";
  }

  private void populateModelAttributePagingList(Model model, Page<User> userPage, int current,
      int begin, int end) {
    model.addAttribute("userList", userPage.getContent());
    model.addAttribute("userPage", userPage);
    model.addAttribute("currentIndex", current);
    model.addAttribute("beginIndex", begin);
    model.addAttribute("endIndex", end);
  }

  private void populateModelAttributeUserForm(Model model, User user) {
    List<Role> roles = roleService.findAll();
    Set<String> userRoles = user.getRoles().stream().map(Role::getCode)
        .collect(Collectors.toSet());
    model.addAttribute("user", user);
    model.addAttribute("roleList", roles);
    model.addAttribute("userRoles", userRoles);
  }
}
