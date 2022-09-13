package org.example.repository;

import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Repository
public class PostRepositoryImpl implements PostRepository {

  private final AtomicLong lastId;
  private final Map<Long, Post> posts;

  public PostRepositoryImpl() {
    this.lastId = new AtomicLong();
    this.posts = new ConcurrentHashMap<>();
  }

  @Override
  public List<Post> all() {
    return posts.values().stream().filter(Post::isActive).sorted(Comparator.comparingLong(Post::getId)).collect(Collectors.toList());
  }

  @Override
  public Optional<Post> getById(long id) {
    Post result = null;
    if (posts.get(id).isActive()) result = posts.get(id);
    return Optional.ofNullable(result);
  }

  @Override
  public Post save(Post post) throws NotFoundException {
    if (post.getId() == 0) post.setId(lastId.incrementAndGet());
    else if (!posts.containsKey(post.getId()) || posts.get(post.getId()).isRemoved()) throw new NotFoundException();
    posts.put(post.getId(), post);
    return post;
  }

  @Override
  public Optional<Post> removeById(long id) {
    Post result = null;
    if (posts.get(id).isActive()) result = posts.get(id).remove();
    return Optional.ofNullable(result);
  }
}
