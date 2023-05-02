package com.clone.daangnmarketserver.post.domain.repository;

import com.clone.daangnmarketserver.common.Id;
import com.clone.daangnmarketserver.post.domain.Post;
import com.clone.daangnmarketserver.post.presentation.dto.LikeResponse;
import com.clone.daangnmarketserver.post.presentation.dto.PostResponse;
import com.clone.daangnmarketserver.post.presentation.dto.PostsResponse;
import com.clone.daangnmarketserver.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

  List<PostsResponse> findPosts(Pageable pageable);

  List<PostsResponse> findMe(Id<User> id);

  Optional<PostResponse> findPost(Id<Post> postId, Id<User> userId);

  LikeResponse numberOfPostLikes(Id<Post> id);
}
