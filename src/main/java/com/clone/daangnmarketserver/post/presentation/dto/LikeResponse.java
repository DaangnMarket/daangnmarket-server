package com.clone.daangnmarketserver.post.presentation.dto;

import com.querydsl.core.annotations.QueryProjection;

public class LikeResponse {

  private final Long numberOfLikes;

  @QueryProjection
  public LikeResponse(Long numberOfLikes) {
    this.numberOfLikes = numberOfLikes;
  }

  public Long getNumberOfLikes() {
    return numberOfLikes;
  }
}
