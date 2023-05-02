package com.clone.daangnmarketserver.post.presentation.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PostResponse {

  private final Long id;

  private final String imageUrl;

  private final String title;

  private final String body;

  private final LocalDateTime createdAt;

  private final long numberOfLikes;

  private final int price;

  private final boolean isLike;

  private final String profileImageUrl;

  private final String name;

  private final Long userId;

  private final BigDecimal temperature;

  @QueryProjection
  public PostResponse(Long id, String imageUrl, String title, String body, LocalDateTime createdAt,
      long numberOfLikes, int price, boolean isLike, String profileImageUrl, String name,
      Long userId,
      BigDecimal temperature) {
    this.id = id;
    this.imageUrl = imageUrl;
    this.title = title;
    this.body = body;
    this.createdAt = createdAt;
    this.numberOfLikes = numberOfLikes;
    this.price = price;
    this.isLike = isLike;
    this.profileImageUrl = profileImageUrl;
    this.name = name;
    this.userId = userId;
    this.temperature = temperature;
  }

  public Long getId() {
    return id;
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public long getNumberOfLikes() {
    return numberOfLikes;
  }

  public int getPrice() {
    return price;
  }

  public boolean getIsLike() {
    return isLike;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public String getName() {
    return name;
  }

  public Long getUserId() {
    return userId;
  }

  public BigDecimal getTemperature() {
    return temperature;
  }
}
