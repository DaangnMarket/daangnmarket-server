package com.clone.daangnmarketserver.post.presentation.dto;

import com.clone.daangnmarketserver.post.domain.Post;
import com.clone.daangnmarketserver.user.domain.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CreateRequest {

  private String imageUrl;

  private String title;

  private String body;

  private int price;

  private boolean haggle;

  private boolean share;

  protected CreateRequest() {
  }

  public CreateRequest(String imageUrl, String title, String body, int price, boolean haggle,
      boolean share) {
    this.imageUrl = imageUrl;
    this.title = title;
    this.body = body;
    this.price = price;
    this.haggle = haggle;
    this.share = share;
  }

  public Post toEntity(User user) {
    return new Post(imageUrl, title, body, price, haggle, share, user);
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

  public boolean isHaggle() {
    return haggle;
  }

  public boolean isShare() {
    return share;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("imageUrl", imageUrl)
        .append("title", title)
        .append("body", body)
        .append("price", price)
        .append("isHaggle", haggle)
        .append("isShare", share)
        .toString();
  }
}
