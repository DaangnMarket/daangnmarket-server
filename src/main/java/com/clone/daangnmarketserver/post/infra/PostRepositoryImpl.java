package com.clone.daangnmarketserver.post.infra;

import static com.clone.daangnmarketserver.post.domain.QLike.like;
import static com.clone.daangnmarketserver.post.domain.QPost.post;
import static com.clone.daangnmarketserver.user.domain.QUser.user;

import com.clone.daangnmarketserver.common.Id;
import com.clone.daangnmarketserver.post.domain.Post;
import com.clone.daangnmarketserver.post.domain.QLike;
import com.clone.daangnmarketserver.post.domain.repository.PostRepositoryCustom;
import com.clone.daangnmarketserver.post.presentation.dto.LikeResponse;
import com.clone.daangnmarketserver.post.presentation.dto.PostResponse;
import com.clone.daangnmarketserver.post.presentation.dto.PostsResponse;
import com.clone.daangnmarketserver.post.presentation.dto.QLikeResponse;
import com.clone.daangnmarketserver.post.presentation.dto.QPostResponse;
import com.clone.daangnmarketserver.post.presentation.dto.QPostsResponse;
import com.clone.daangnmarketserver.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public PostRepositoryImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public List<PostsResponse> findPosts(Pageable pageable) {
    return queryFactory
        .select(new QPostsResponse(
            post.id,
            post.imageUrl,
            post.title,
            post.price,
            like.count()
        ))
        .from(post)
        .leftJoin(like)
        .on(like.post.eq(post))
        .groupBy(post)
        .fetch();
  }

  @Override
  public List<PostsResponse> findMe(Id<User> id) {
    Long userId = id.value();
    return queryFactory
        .select(new QPostsResponse(
            post.id,
            post.imageUrl,
            post.title,
            post.price,
            like.count()
        ))
        .from(post)
        .leftJoin(like)
        .on(like.post.eq(post))
        .where(post.user.id.eq(userId))
        .groupBy(post)
        .fetch();
  }

  @Override
  public Optional<PostResponse> findPost(Id<Post> postId, Id<User> userId) {
    QLike like1 = new QLike("like1");
    QLike like2 = new QLike("like2");
    return Optional.ofNullable(
        queryFactory
            .select(new QPostResponse(
                post.id,
                post.imageUrl,
                post.title,
                post.body,
                post.createdAt,
                like1.count(),
                post.price,
                like2.id.isNotNull(),
                user.profileImageUrl,
                user.name,
                user.id,
                user.temperature
            ))
            .from(post)
            .where(post.id.eq(postId.value()))
            .leftJoin(post.user, user)
            .leftJoin(like1)
            .on(like1.post.eq(post))
            .leftJoin(like2)
            .on(like2.post.eq(post).and(like2.user.id.eq(userId.value())))
            .groupBy(like2)
            .fetchOne()
    );
  }

  @Override
  public LikeResponse numberOfPostLikes(Id<Post> id) {
    return queryFactory
        .select(new QLikeResponse(
            like.count()
        ))
        .from(like)
        .where(like.post.id.eq(id.value()))
        .fetchOne();
  }
}
