package com.clone.daangnmarketserver.security;

import com.clone.daangnmarketserver.user.domain.OAuth2Provider;
import com.clone.daangnmarketserver.user.domain.User;
import com.clone.daangnmarketserver.user.domain.repository.UserRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    OAuth2Provider oAuth2Provider = OAuth2Provider.getProvider(userRequest);
    User user = processUser(oAuth2Provider, oAuth2User.getAttributes());
    return new CustomOAuth2User(oAuth2User, user.getId(), user.getRole());
  }

  private User processUser(OAuth2Provider provider, Map<String, Object> attributes) {
    CustomOAuth2UserInfo userInfo = CustomOAuth2UserInfoFactory.create(provider, attributes);
    String email = userInfo.getEmail();
    String providerId = userInfo.getProviderId();
    return userRepository.findByEmailAndProviderId(email, providerId).orElseGet(() -> {
      User user = new User(providerId, provider, email);
      return userRepository.save(user);
    });
  }
}
