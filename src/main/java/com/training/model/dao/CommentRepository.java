package com.training.model.dao;

import com.training.model.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByOrderByPostedAtDesc();

  Comment findByPostedBy(String title);
}
