package com.clone.daangnmarketserver.user.domain;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

public enum OAuth2Provider {
  KAKAO;

  public static OAuth2Provider getProvider(OAuth2UserRequest userRequest) {
    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    return OAuth2Provider.valueOf(registrationId.toUpperCase());
  }
}
