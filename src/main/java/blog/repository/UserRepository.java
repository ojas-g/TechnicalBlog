package blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import blog.model.Post;
import blog.model.User;
import blog.model.ProfilePhoto;
import blog.model.Category;


import javax.persistence.Cacheable;
import java.util.Date;
import java.util.List;

@Repository

public interface UserRepository extends CrudRepository<User, String> {

  //@Query(nativeQuery = true,value="SELECT  username from users  ")
  //List<User> findAllUsers() ;
}
