package com.training.model.dao;

import com.training.model.entity.News;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

  List<News> findAllByOrderByPublishedDateDesc();

  Page<News> findByTitleContaining(String title, Pageable pageable);
}