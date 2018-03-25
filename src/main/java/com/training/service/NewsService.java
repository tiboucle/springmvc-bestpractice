package com.training.service;

import static com.training.util.Constant.PAGE_SIZE;

import com.training.model.dao.NewsRepository;
import com.training.model.entity.News;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsService implements CrudService<News> {

  private final NewsRepository newsRepository;

  @Autowired
  public NewsService(NewsRepository newsRepository) {
    this.newsRepository = newsRepository;
  }

  @Override
  public List<News> findAll() {
    return newsRepository.findAllByOrderByPublishedDateDesc();
  }

  @Override
  public News findById(Long id) {
    return newsRepository.findById(id).orElse(null);
  }

  @Override
  public boolean save(News news) {
    try {
      newsRepository.save(news);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean deleteById(Long id) {
    try {
      newsRepository.deleteById(id);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public Page<News> getNewsPage(Integer pageNumber) {
    PageRequest request = PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.by("id"));
    return newsRepository.findAll(request);
  }

  public Page<News> findByTitleContaining(String title) {
    return newsRepository.findByTitleContaining(title, PageRequest.of(0, PAGE_SIZE));
  }
}