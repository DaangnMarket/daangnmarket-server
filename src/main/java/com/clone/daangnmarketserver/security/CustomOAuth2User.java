package com.clone.daangnmarketserver.security;

import com.clone.daangnmarketserver.user.domain.Role;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User, Serializable {

  private final OAuth2User oAuth2User;

  private final Long id;

  private final Role role;

  public CustomOAuth2User(OAuth2User oAuth2User, Long id, Role role) {
    Preconditions.checkArgument(oAuth2User != null, "oAuth2User must be provided.");
    Preconditions.checkArgument(id != null, "id must be provided.");
    Preconditions.checkArgument(role != null, "role must be provided.");

    this.oAuth2User = oAuth2User;
    this.id = id;
    this.role = role;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return oAuth2User.getAttributes();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return oAuth2User.getAuthorities();
  }

  @Override
  public String getName() {
    return oAuth2User.getName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomOAuth2User that = (CustomOAuth2User) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
      .append("[oAuth2User]")
      .append("id", id)
      .append("role", role)
      .toString();
  }
}
