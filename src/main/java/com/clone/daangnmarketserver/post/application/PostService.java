package com.clone.daangnmarketserver.post.application;

import com.clone.daangnmarketserver.common.Id;
import com.clone.daangnmarketserver.post.domain.Like;
import com.clone.daangnmarketserver.post.domain.Post;
import com.clone.daangnmarketserver.post.domain.repository.LikeRepository;
import com.clone.daangnmarketserver.post.domain.repository.PostRepository;
import com.clone.daangnmarketserver.post.presentation.dto.CreateRequest;
import com.clone.daangnmarketserver.post.presentation.dto.LikeResponse;
import com.clone.daangnmarketserver.post.presentation.dto.PostResponse;
import com.clone.daangnmarketserver.post.presentation.dto.PostsResponse;
import com.clone.daangnmarketserver.post.presentation.dto.UpdateRequest;
import com.clone.daangnmarketserver.user.domain.User;
import com.clone.daangnmarketserver.user.domain.repository.UserRepository;
import com.google.common.base.Preconditions;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final UserRepository userRepository;

  private final PostRepository postRepository;

  private final LikeRepository likeRepository;

  @Transactional
  public URI create(Id<User> id, CreateRequest dto) {
    Preconditions.checkArgument(id != null, "id must be provided.");
    Preconditions.checkArgument(dto != null, "dto must be provided.");

    User user = userRepository.findById(id.value())
        .orElseThrow(RuntimeException::new);

    Post post = dto.toEntity(user);
    postRepository.save(post);

    return URI.create("/posts/" + post.getId().value());
  }

  @Transactional(readOnly = true)
  public List<PostsResponse> findMe(Id<User> id) {
    Preconditions.checkArgument(id != null, "id must be provided.");

    return postRepository.findMe(id);
  }

  @Transactional(readOnly = true)
  public List<PostsResponse> findAll() {
    return postRepository.findPosts(null);
  }

  @Transactional
  public void deleteById(Id<User> userId, Id<Post> postId) {
    Preconditions.checkArgument(userId != null, "userId must be provided.");
    Preconditions.checkArgument(postId != null, "postId must be provided.");

    postRepository.deleteByIdAndUserId(postId.value(), userId.value());
  }

  @Transactional
  public void update(Id<User> userId, Id<Post> postId, UpdateRequest dto) {
    Preconditions.checkArgument(userId != null, "userId must be provided.");
    Preconditions.checkArgument(postId != null, "postId must be provided.");
    Preconditions.checkArgument(dto != null, "dto must be provided.");

    postRepository.findByIdAndUserId(postId.value(), userId.value())
        .ifPresent(post -> {
          String title = dto.getTitle();
          String body = dto.getBody();
          String imageUrl = dto.getImageUrl();
          Integer price = dto.getPrice();
          post.update(title, body, imageUrl, price);
        });
  }

  @Transactional(readOnly = true)
  public PostResponse findOne(Id<Post> postId, Id<User> userId) {
    Preconditions.checkArgument(postId != null, "postId must be provided.");
    Preconditions.checkArgument(userId != null, "userId must be provided.");

    return postRepository.findPost(postId, userId)
        .orElseThrow(RuntimeException::new);
  }

  @Transactional
  public LikeResponse like(Id<User> userId, Id<Post> postId) {
    Preconditions.checkArgument(userId != null, "userId must be provided.");
    Preconditions.checkArgument(postId != null, "postId must be provided.");

    likeRepository.findByUserIdAndPostId(userId.value(), postId.value())
        .ifPresentOrElse(
            likeRepository::delete,
            () -> {
              Like like = new Like(userId.value(), postId.value());
              likeRepository.save(like);
            }
        );
    return postRepository.numberOfPostLikes(postId);
  }
}
