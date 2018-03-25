package com.training.service;

import com.training.model.dao.CommentRepository;
import com.training.model.entity.Comment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements CrudService<Comment> {

  private final CommentRepository commentRepository;

  @Autowired
  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  @Override
  public List<Comment> findAll() {
    return commentRepository.findAllByOrderByPostedAtDesc();
  }

  @Override
  public Comment findById(Long id) {
    return commentRepository.findById(id).orElse(null);
  }

  @Override
  public boolean save(Comment comment) {
    try {
      commentRepository.save(comment);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean deleteById(Long id) {
    try {
      commentRepository.deleteById(id);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public Comment findByPostedAt(String title) {
    return commentRepository.findByPostedBy(title);
  }
}
