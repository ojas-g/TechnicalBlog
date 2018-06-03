package blog.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import blog.model.Post;
import blog.model.User;
import blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import blog.repository.PostRepository;
import blog.repository.PostRepository;
import blog.repository.PostRepository;

@org.springframework.web.bind.annotation.RestController
public class RestController {

  @Autowired
  PostRepository postRepo;

  @Autowired
  UserRepository userRepo;


  @GetMapping("/api/allposts/")
  public Iterable<Post> getAllPosts() {
    return postRepo.findAll();

  }

  @GetMapping("/api/allusers/")
  public Iterable<User> getAllUsers() {
    return  userRepo.findAll();
  }

  @DeleteMapping("/api/deletepost/")
  public void deletePosts(@RequestParam("id") int id) {
    postRepo.deletePostById(id);
  }
}
