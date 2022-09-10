package org.example.service;

import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.example.repository.PostRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostServiceImpl implements PostService {
  private final PostRepositoryImpl repository;

  @Autowired
  public PostServiceImpl(PostRepositoryImpl repository) {
    this.repository = repository;
  }

  @Override
  public List<Post> all() {
    return repository.all();
  }

  @Override
  public Post getById(long id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }

  @Override
  public Post save(Post post) throws NotFoundException {
    return repository.save(post);
  }

  @Override
  public void removeById(long id) {
    repository.removeById(id).orElseThrow(NotFoundException::new);
  }
}

