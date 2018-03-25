package com.training.controller;

import com.training.model.entity.Comment;
import com.training.model.entity.News;
import com.training.service.NewsService;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
@RequestMapping("/news")
public class NewsController {

  private final NewsService service;

  @Autowired
  public NewsController(NewsService service) {
    this.service = service;
  }

  @GetMapping
  public String getNews(Model model) {
    List<News> newsList = service.findAll();
    if (newsList.isEmpty()) {
      model.addAttribute("newsNotFound", true);
    } else {
      model.addAttribute("newsList", newsList);
    }

    return "redirect:/news/pages/1";
  }

  @GetMapping("/pages/{pageNumber}")
  public String getNewsPage(@PathVariable Integer pageNumber, Model model) {
    Page<News> newsPage = service.getNewsPage(pageNumber);
    int current = newsPage.getNumber() + 1;
    int begin = Math.max(1, current - 5);
    int end = Math.min(begin + 10, newsPage.getTotalPages());

    populateModelAttributePagingList(model, newsPage, current, begin, end);
    return "news/paginglist-page";
  }

  @GetMapping("/search")
  public String searchNews(Model model, @RequestParam("searchText") String searchText) {
    Page<News> newsPage = service.findByTitleContaining(searchText);
    int current = newsPage.getNumber() + 1;
    int begin = Math.max(1, current - 5);
    int end = Math.min(begin + 10, newsPage.getTotalPages());

    populateModelAttributePagingList(model, newsPage, current, begin, end);
    return "news/paginglist-page";
  }

  @GetMapping(value = "/{newsId}")
  public String viewNews(@PathVariable Long newsId, Model model) {
    News news = service.findById(newsId);
    Set<Comment> comments = news.getComments();
    Comment comment = new Comment();
    comment.setNews(news);
    comment.setPostedAt(new Date());
    model.addAttribute("news", news);
    model.addAttribute("comments", comments);
    model.addAttribute("comment", comment);
    return "news/details-page";
  }

  @GetMapping(value = "/add")
  public String addNewsForm(Model model) {
    model.addAttribute("newsForm", new News());
    return "news/form-page";
  }

  @PostMapping(value = "/add")
  public String saveNews(@Valid @ModelAttribute("newsForm") News news, BindingResult result,
      Model model) {
    boolean success = !result.hasErrors() && service.save(news);
    model.addAttribute("newsCreated", success);
    return success ? "redirect:/news?newsCreated" : "news/form-page";
  }

  @GetMapping(value = "/update/{newsId}")
  public String updateNewsForm(@PathVariable Long newsId, Model model) {
    News news = service.findById(newsId);
    if (news != null) {
      model.addAttribute("newsForm", news);
      return "news/form-page";
    } else {
      return "redirect:/news/";
    }
  }

  @PostMapping(value = "/update")
  public String updateNews(@Valid @ModelAttribute("newsForm") News news, BindingResult result,
      Model model) {
    boolean success = !result.hasErrors() && service.save(news);
    model.addAttribute("newsUpdated", success);
    return success ? "redirect:/news?newsUpdated" : "news/form-page";
  }

  @PostMapping(value = "/delete")
  public String deleteNews(@ModelAttribute("news") News news, Model model) {
    boolean success = service.deleteById(news.getId());
    model.addAttribute("newsDeleted", success);
    return "redirect:/news?newsDeleted";
  }

  private void populateModelAttributePagingList(Model model, Page<News> newsPage, int current,
      int begin, int end) {
    model.addAttribute("newsList", newsPage.getContent());
    model.addAttribute("newsPage", newsPage);
    model.addAttribute("currentIndex", current);
    model.addAttribute("beginIndex", begin);
    model.addAttribute("endIndex", end);
  }
}
