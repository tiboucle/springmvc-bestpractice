package com.training.controller;

import com.training.model.entity.Comment;
import com.training.model.entity.News;
import com.training.service.CommentService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comment")
public class CommentController {

  private final CommentService service;

  @Autowired
  public CommentController(CommentService service) {
    this.service = service;
  }

  @PostMapping(value = "/add")
  public String saveComment(@Valid @ModelAttribute("comment") Comment comment, BindingResult result,
      Model model, RedirectAttributes redirectAttributes) {
    boolean success = !result.hasErrors() && service.save(comment);
    News news = comment.getNews();
    if (success) {
      model.addAttribute("commentCreated", true);
      return "redirect:/news/" + news.getId() + "?commentCreated";
    } else {
      model.addAttribute("commentNotCreated", true);
      redirectAttributes.addAttribute("comment", comment);
      return "redirect:/news/" + news.getId() + "?commentNotCreated";
    }
  }
}
