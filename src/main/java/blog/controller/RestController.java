package blog.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import blog.model.Post;
import blog.model.ProfilePhoto;
import blog.model.User;
import blog.repository.UserRepository;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
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
  public String deletePosts(@RequestParam("id") int id,@RequestParam("username") String uname,@RequestParam("password") String password) {
    String postOwner=postRepo.findUserByPostId(id);
    if (!postOwner.equalsIgnoreCase(uname))
      return "You have no right to delete other users post";

    String passwordByUser = String.valueOf(userRepo.findUserPassword(uname));


    String sha256hex = Hashing.sha256()
      .hashString(password, Charsets.US_ASCII)
      .toString();
    if (!(sha256hex.equalsIgnoreCase(passwordByUser))) {
      return "Invalid credentials";
    }
    postRepo.deletePostById(id);
    return "Post deleted successfully";
  }

  @PostMapping("/api/editpost/")
  public String editPosts(@RequestParam("id") int id,@RequestParam("username") String uname,@RequestParam("password") String password,@RequestParam("title") String title,@RequestParam("body") String body) {

    String postOwner=postRepo.findUserByPostId(id);
    if (!postOwner.equalsIgnoreCase(uname))
      return "You have no right to edit other users post";

    String passwordByUser = String.valueOf(userRepo.findUserPassword(uname));


    String sha256hex = Hashing.sha256()
      .hashString(password, Charsets.US_ASCII)
      .toString();
    if (!(sha256hex.equalsIgnoreCase(passwordByUser))) {
      return "Invalid credentials";
    }


    postRepo.editPostValues(body,title,id);
    return "the edits have been updated for the posts with new title" +title;

  }

  @PostMapping("/api/register/")
  public String registerUser(@RequestParam("username") String uname,@RequestParam("password") String password,@RequestParam("fullname") String fullName) {

    String result = String.valueOf(userRepo.findUserExist(uname));
    String sha256hex = Hashing.sha256()
      .hashString(password, Charsets.US_ASCII)
      .toString();

    if ((result.equalsIgnoreCase("null"))) {
      userRepo.addUserCredentials(uname, fullName,sha256hex);
      return uname + ", you successfully registered";
    } else {
      return "user already exists";
    }


  }

  @PostMapping("/api/login/")
  public void loginUser(@RequestParam("id") int id) {

    //postRepo.deletePostById(id);
  }

  @PostMapping("/api/createpost/")
  public String createPost(@RequestParam("username") String uname,@RequestParam("password") String password,@RequestParam("title") String title,@RequestParam("post body") String body) {
    String passwordByUser = String.valueOf(userRepo.findUserPassword(uname));

    String sha256hex = Hashing.sha256()
      .hashString(password, Charsets.US_ASCII)
      .toString();
    if (!(sha256hex.equalsIgnoreCase(passwordByUser))) {
      return "Invalid credentials";
    }

    postRepo.addPostValues(body,title,uname);
    return "Your post with title "+title + "is created";

    //postRepo.deletePostById(id);
  }



}
