package com.clone.daangnmarketserver.user.application;

import com.clone.daangnmarketserver.common.Id;
import com.clone.daangnmarketserver.user.domain.User;
import com.clone.daangnmarketserver.user.domain.repository.UserRepository;
import com.clone.daangnmarketserver.user.presentation.dto.UpdateRequest;
import com.clone.daangnmarketserver.user.presentation.dto.UserResponse;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public UserResponse getMe(Id<User> id) {
    Preconditions.checkArgument(id != null, "id must be provided.");

    return find(id);
  }

  @Transactional
  public void update(Id<User> id, UpdateRequest dto) {
    Preconditions.checkArgument(id != null, "id must be provided.");
    Preconditions.checkArgument(dto != null, "dto must be provided.");

    userRepository.findById(id.value())
      .ifPresent(user -> {
        String name = dto.getName();
        user.update(name);
      });
  }

  @Transactional
  public void delete(Id<User> id) {
    Preconditions.checkArgument(id != null, "id must be provided.");

    userRepository.deleteById(id.value());
  }

  public UserResponse findById(Id<User> id) {
    Preconditions.checkArgument(id != null, "id must be provided.");

    return find(id);
  }

  private UserResponse find(Id<User> id) {
    User user = userRepository.findById(id.value())
      .orElseThrow(RuntimeException::new);
    return UserResponse.fromEntity(user);
  }
}
