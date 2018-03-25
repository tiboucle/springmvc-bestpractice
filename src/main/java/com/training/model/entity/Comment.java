package com.training.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "COMMENTS")
public class Comment extends CrudEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "COMMENT_SEQ")
  @SequenceGenerator(name = "COMMENT_SEQ", sequenceName = "COMMENT_SEQUENCE")
  private Long id;

  @NotBlank(message = "Please fill comment")
  @Column(nullable = false)
  private String text;

  @NotBlank(message = "Please fill your name")
  @Column(nullable = false)
  private String postedBy;

  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date postedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "newsId")
  private News news;

  public Comment() {
  }

  public Comment(String text, String postedBy, Date postedAt) {
    this.text = text;
    this.postedBy = postedBy;
    this.postedAt = postedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getPostedBy() {
    return postedBy;
  }

  public void setPostedBy(String postedBy) {
    this.postedBy = postedBy;
  }

  public Date getPostedAt() {
    return postedAt;
  }

  public void setPostedAt(Date postedAt) {
    this.postedAt = postedAt;
  }

  public News getNews() {
    return news;
  }

  public void setNews(News news) {
    this.news = news;
  }
}
