package com.clone.daangnmarketserver.user.presentation.dto;

import com.clone.daangnmarketserver.user.domain.User;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class UserResponse {

  private final String profile_image;

  private final String name;

  private final Long id;

  private final BigDecimal temperature;

  private final int dealCount;

  private UserResponse(String profile_image, String name, Long id, BigDecimal temperature,
    int dealCount) {
    this.profile_image = profile_image;
    this.name = name;
    this.id = id;
    this.temperature = temperature;
    this.dealCount = dealCount;
  }

  public static UserResponse fromEntity(User user) {
    return new UserResponse(
      user.getProfileImageUrl(),
      user.getName(),
      user.getId().value(),
      user.getTemperature(),
      user.getDealCount()
    );
  }
}
