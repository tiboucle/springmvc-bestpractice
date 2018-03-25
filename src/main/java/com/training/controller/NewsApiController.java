package com.training.controller;

import com.training.model.entity.News;
import com.training.service.NewsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsApiController {

  private final NewsService service;

  @Autowired
  public NewsApiController(NewsService service) {
    this.service = service;
  }

  @GetMapping(value = {"", "/", "/list"})
  public List<News> listNews() {
    return service.findAll();
  }

  @GetMapping(value = "/{newsId}")
  public News getNews(@PathVariable Long newsId) {
    return service.findById(newsId);
  }

  @PostMapping(value = {"", "/"})
  public boolean createNews(@RequestBody News news) {
    System.out.println(news.getPublishedDate());
    return service.save(news);
  }
}