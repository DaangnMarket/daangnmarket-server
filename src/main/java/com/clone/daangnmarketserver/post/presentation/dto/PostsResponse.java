package com.clone.daangnmarketserver.post.presentation.dto;

import com.querydsl.core.annotations.QueryProjection;

public class PostsResponse {

  private final Long id;

  private final String imageUrl;

  private final String title;

  private final int price;

  private final long numberOfLikes;


  @QueryProjection
  public PostsResponse(Long id, String imageUrl, String title, int price, long numberOfLikes) {
    this.id = id;
    this.imageUrl = imageUrl;
    this.title = title;
    this.price = price;
    this.numberOfLikes = numberOfLikes;
  }

  public Long
  getId() {
    return id;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getTitle() {
    return title;
  }

  public int getPrice() {
    return price;
  }

  public long getNumberOfLikes() {
    return numberOfLikes;
  }
}
