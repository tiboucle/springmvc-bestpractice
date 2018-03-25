package com.training.service;

import com.training.model.entity.CrudEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CrudService<E extends CrudEntity> {

  List<E> findAll();

  E findById(Long id);

  boolean save(E news);

  boolean deleteById(Long id);
}
