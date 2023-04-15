package com.clone.daangnmarketserver.user.domain;

import com.google.common.base.Preconditions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String profileImageUrl;

  @Column(length = 20, unique = true, nullable = false)
  private String providerId;

  @Enumerated(EnumType.STRING)
  @Column(length = 8, nullable = false)
  private OAuth2Provider provider;

  @Column(length = 40, nullable = false)
  private String email;

  @Column(length = 15, nullable = false)
  private String name;

  @Column(precision = 3, scale = 1, nullable = false)
  private BigDecimal temperature;

  @Enumerated(EnumType.STRING)
  @Column(length = 15, nullable = false)
  private Role role;

  protected User() {
  }

  public User(String providerId, OAuth2Provider provider, String email) {
    this(null, providerId, provider, email, "당근", BigDecimal.valueOf(36.5), Role.ROLE_USER);
  }

  private User(String profileImageUrl, String providerId, OAuth2Provider provider, String email,
    String name, BigDecimal temperature, Role role) {
    Preconditions.checkArgument(providerId != null, "providerId must be provided.");
    Preconditions.checkArgument(provider != null, "provider must be provided.");
    Preconditions.checkArgument(email != null, "email must be provided.");
    Preconditions.checkArgument(name != null, "name must be provided.");
    Preconditions.checkArgument(temperature != null, "temperature must be provided.");
    Preconditions.checkArgument(role != null, "role must be provided.");

    this.profileImageUrl = profileImageUrl;
    this.providerId = providerId;
    this.provider = provider;
    this.email = email;
    this.name = name;
    this.temperature = temperature;
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(providerId,
      user.providerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, providerId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
      .append("id", id)
      .append("profileImageUrl", profileImageUrl)
      .append("providerId", providerId)
      .append("provider", provider)
      .append("email", email)
      .append("name", name)
      .append("temperature", temperature)
      .append("role", role)
      .toString();
  }
}
