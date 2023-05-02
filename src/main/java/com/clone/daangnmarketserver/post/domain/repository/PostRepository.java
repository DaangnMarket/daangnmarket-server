package com.clone.daangnmarketserver.post.domain.repository;

import com.clone.daangnmarketserver.post.domain.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

  List<Post> findByUserId(Long userId);

  void deleteByIdAndUserId(Long id, Long userId);

  Optional<Post> findByIdAndUserId(Long id, Long userId);
}
