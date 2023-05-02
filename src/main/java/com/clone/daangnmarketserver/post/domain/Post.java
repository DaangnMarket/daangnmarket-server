package com.clone.daangnmarketserver.post.domain;

import com.clone.daangnmarketserver.user.domain.User;
import com.google.common.base.Preconditions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String imageUrl;

  @Column(nullable = false)
  private String title;

  @Lob
  @Column(nullable = false)
  private String body;

  private int price;

  @Enumerated(EnumType.STRING)
  private Status status;

  private boolean haggle;

  private boolean share;

  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  protected Post() {
  }

  public Post(String imageUrl, String title, String body, int price, boolean haggle,
      boolean share, User user) {
    this(imageUrl, title, body, price, Status.ON_SALE, haggle, share, LocalDateTime.now(),
        user);
  }

  private Post(String imageUrl, String title, String body, int price, Status status,
      boolean haggle, boolean share, LocalDateTime createdAt, User user) {
    Preconditions.checkArgument(title != null, "title must be provided.");
    Preconditions.checkArgument(body != null, "body must be provided.");
    Preconditions.checkArgument(status != null, "status must be provided.");
    Preconditions.checkArgument(createdAt != null, "createdAt must be provided.");
    Preconditions.checkArgument(user != null, "user must be provided.");
    Preconditions.checkArgument(price >= 0, "price must be greater than or equal to 0.");

    this.imageUrl = imageUrl;
    this.title = title;
    this.body = body;
    this.price = price;
    this.status = status;
    this.haggle = haggle;
    this.share = share;
    this.createdAt = createdAt;
    this.user = user;
  }

  public com.clone.daangnmarketserver.common.Id<Post> getId() {
    return com.clone.daangnmarketserver.common.Id.of(Post.class, id);
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }

  public int getPrice() {
    return price;
  }

  public Status getStatus() {
    return status;
  }

  public boolean isHaggle() {
    return haggle;
  }

  public boolean isShare() {
    return share;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public User getUser() {
    return user;
  }

  public void update(String title, String body, String imageUrl, Integer price) {
    if (title != null) {
      this.title = title;
    }

    if (body != null) {
      this.body = body;
    }

    if (price != null) {
      this.price = price;
    }

    this.imageUrl = imageUrl;
  }
}
