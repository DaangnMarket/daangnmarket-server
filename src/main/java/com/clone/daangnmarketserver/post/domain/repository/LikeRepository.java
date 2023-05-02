package com.clone.daangnmarketserver.post.domain.repository;

import com.clone.daangnmarketserver.post.domain.Like;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

  Optional<Like> findByUserIdAndPostId(Long userId, Long postId);
}
