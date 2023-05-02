package com.clone.daangnmarketserver.post.presentation.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UpdateRequest {

  private String title;

  private String imageUrl;

  private Integer price;

  private String body;

  protected UpdateRequest() {
  }

  public UpdateRequest(String title, String imageUrl, Integer price, String body) {
    this.title = title;
    this.imageUrl = imageUrl;
    this.price = price;
    this.body = body;
  }

  public String getTitle() {
    return title;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public Integer getPrice() {
    return price;
  }

  public String getBody() {
    return body;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("title", title)
        .append("imageUrl", imageUrl)
        .append("price", price)
        .append("body", body)
        .toString();
  }
}
