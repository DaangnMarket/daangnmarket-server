package com.clone.daangnmarketserver.post.domain;

import com.clone.daangnmarketserver.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "likes")
public class Like {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  @Column(name = "user_id")
  private Long userId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", insertable = false, updatable = false)
  private Post post;

  @Column(name = "post_id")
  private Long postId;

  protected Like() {
  }

  public Like(Long userId, Long postId) {
    this.userId = userId;
    this.postId = postId;
  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public Post getPost() {
    return post;
  }
}
