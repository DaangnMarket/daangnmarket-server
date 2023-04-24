package com.clone.daangnmarketserver.user.presentation;

import com.clone.daangnmarketserver.common.Id;
import com.clone.daangnmarketserver.security.CustomOAuth2User;
import com.clone.daangnmarketserver.user.application.UserService;
import com.clone.daangnmarketserver.user.domain.User;
import com.clone.daangnmarketserver.user.presentation.dto.UpdateRequest;
import com.clone.daangnmarketserver.user.presentation.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/me")
  public ResponseEntity<UserResponse> getMe(@AuthenticationPrincipal CustomOAuth2User user) {
    Id<User, Long> id = user.getId();
    UserResponse me = userService.getMe(id);
    return ResponseEntity.ok(me);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
    Id<User, Long> findId = Id.of(User.class, id);
    UserResponse findUser = userService.findById(findId);
    return ResponseEntity.ok(findUser);
  }

  @PatchMapping
  public ResponseEntity<Void> update(@AuthenticationPrincipal CustomOAuth2User user, @RequestBody
  UpdateRequest dto) {
    Id<User, Long> id = user.getId();
    userService.update(id, dto);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  public ResponseEntity<Void> delete(@AuthenticationPrincipal CustomOAuth2User user) {
    Id<User, Long> id = user.getId();
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
