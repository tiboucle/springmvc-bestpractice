package com.training.service;

import static com.training.util.Constant.PAGE_SIZE;

import com.training.model.dao.UserRepository;
import com.training.model.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CrudService<User> {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User findById(Long id) {
    return userRepository.findById(id).orElse(null);
  }


  @Override
  public boolean save(User user) {
    try {
      userRepository.save(user);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean deleteById(Long id) {
    try {
      userRepository.deleteById(id);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public User findUsername(String username) {
    return userRepository.findByUsername(username);
  }


  public Page<User> getUserPage(Integer pageNumber) {
    PageRequest request = PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.by("id"));
    return userRepository.findAll(request);
  }


  public Page<User> findByTitleContaining(String title) {
    return userRepository.findByUsernameContaining(title, PageRequest.of(0, PAGE_SIZE));
  }
}
