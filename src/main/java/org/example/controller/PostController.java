package org.example.controller;

import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.example.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {
  private final PostServiceImpl service;

  @Autowired
  public PostController(PostServiceImpl service) {
    this.service = service;
  }

  @GetMapping
  public List<Post> all() {
    try {
      return service.all();
    } catch (NotFoundException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{id}")
  public Post getById(@PathVariable long id) {
    try {
      return service.getById(id);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public Post save(@RequestBody Post post) {
    try {
      return service.save(post);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public void removeById(@PathVariable long id) {
    try {
      service.removeById(id);
    } catch (NotFoundException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
}
