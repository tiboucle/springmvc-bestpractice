package com.training.service;

import com.training.model.dao.RoleRepository;
import com.training.model.entity.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements CrudService<Role> {

  private final RoleRepository roleRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  @Override
  public Role findById(Long id) {
    return roleRepository.findById(id).orElse(null);
  }

  @Override
  public boolean save(Role role) {
    try {
      roleRepository.save(role);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean deleteById(Long id) {
    try {
      roleRepository.deleteById(id);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public Role findCode(String code) {
    return roleRepository.findByCode(code);
  }
}
