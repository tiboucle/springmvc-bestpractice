package com.training.model.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User extends CrudEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
  @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQUENCE")
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "Please insert username")
  private String username;

  @Column(nullable = false, length = 60)
  @NotBlank(message = "Please insert password")
  private String password;

  @NotNull
  private boolean enabled = true;

  @ManyToMany(
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "UserRole",
      joinColumns = @JoinColumn(
          name = "user_id",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "role_id",
          referencedColumnName = "id"))
  @Size(min = 1, message = "Role must minimum one")
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username,
      String password, boolean enabled,
      Set<Role> roles) {
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public void addRole(Role role) {
    roles.add(role);
  }

  public void removeRole(Role role) {
    roles.remove(role);
  }
}
