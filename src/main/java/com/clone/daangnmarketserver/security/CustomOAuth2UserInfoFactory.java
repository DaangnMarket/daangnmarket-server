package com.clone.daangnmarketserver.security;

import com.clone.daangnmarketserver.user.domain.OAuth2Provider;
import java.util.Map;

public class CustomOAuth2UserInfoFactory {

  public static CustomOAuth2UserInfo create(OAuth2Provider oAuth2Provider,
    Map<String, Object> attributes) {
    return switch (oAuth2Provider) {
      case KAKAO -> new KakaoOAuth2UserInfo(attributes);
    };
  }
}
