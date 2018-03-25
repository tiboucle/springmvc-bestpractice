package com.training.model.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "NEWS")
public class News extends CrudEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "NEWS_SEQ")
  @SequenceGenerator(name = "NEWS_SEQ", sequenceName = "NEWS_SEQUENCE")
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "Please insert title")
  private String title;

  @Lob
  @NotBlank(message = "Please enter content")
  private String content;

  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Past(message = "published date not valid")
  @NotNull(message = "Please choose date")
  @Column(nullable = false)
  private Date publishedDate;

  @Column(nullable = false)
  @NotBlank(message = "Please insert author")
  private String author;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "news")
  private Set<Comment> comments;

  public News() {
  }

  public News(String title, String content, Date publishedDate, String author) {
    this.title = title;
    this.content = content;
    this.publishedDate = publishedDate;
    this.author = author;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(Date publishedDate) {
    this.publishedDate = publishedDate;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }
}