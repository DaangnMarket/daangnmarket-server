package com.clone.daangnmarketserver.user.domain.repository;

import com.clone.daangnmarketserver.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmailAndProviderId(String email, String providerId);
}
