package com.clone.daangnmarketserver.post.presentation;

import com.clone.daangnmarketserver.common.Id;
import com.clone.daangnmarketserver.post.application.PostService;
import com.clone.daangnmarketserver.post.domain.Post;
import com.clone.daangnmarketserver.post.presentation.dto.CreateRequest;
import com.clone.daangnmarketserver.post.presentation.dto.LikeResponse;
import com.clone.daangnmarketserver.post.presentation.dto.PostResponse;
import com.clone.daangnmarketserver.post.presentation.dto.PostsResponse;
import com.clone.daangnmarketserver.post.presentation.dto.UpdateRequest;
import com.clone.daangnmarketserver.security.CustomOAuth2User;
import com.clone.daangnmarketserver.user.domain.User;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<Void> create(@AuthenticationPrincipal CustomOAuth2User user, @RequestBody
  CreateRequest dto) {
    URI uri = postService.create(user.getId(), dto);
    return ResponseEntity.created(uri).build();
  }

  @GetMapping("/me")
  public ResponseEntity<List<PostsResponse>> getMe(@AuthenticationPrincipal CustomOAuth2User user) {
    List<PostsResponse> userPosts = postService.findMe(user.getId());
    return ResponseEntity.ok(userPosts);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostResponse> findOne(@AuthenticationPrincipal CustomOAuth2User user,
      @PathVariable Long id) {
    PostResponse findPost = postService.findOne(Id.of(Post.class, id), user.getId());
    return ResponseEntity.ok(findPost);
  }

  @GetMapping
  public ResponseEntity<List<PostsResponse>> findAll() {
    List<PostsResponse> findPosts = postService.findAll();
    return ResponseEntity.ok(findPosts);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> update(@AuthenticationPrincipal CustomOAuth2User user,
      @PathVariable Long id, @RequestBody UpdateRequest dto) {
    Id<User> userId = user.getId();
    Id<Post> postId = Id.of(Post.class, id);
    postService.update(userId, postId, dto);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@AuthenticationPrincipal CustomOAuth2User user,
      @PathVariable Long id) {
    Id<User> userId = user.getId();
    Id<Post> postId = Id.of(Post.class, id);
    postService.deleteById(userId, postId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{id}/like")
  public ResponseEntity<LikeResponse> like(@AuthenticationPrincipal CustomOAuth2User user,
      @PathVariable Long id) {
    Id<User> userId = user.getId();
    Id<Post> postId = Id.of(Post.class, id);
    LikeResponse likeResponse = postService.like(userId, postId);
    return ResponseEntity.ok(likeResponse);
  }
}
