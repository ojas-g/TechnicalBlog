package blog.repository;


import blog.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;


import javax.persistence.Cacheable;
import java.util.Date;
import java.util.List;

@Repository

public interface PostRepository extends CrudRepository<Post, Integer>{

  @Transactional
  @Modifying
  @Query(nativeQuery = true,value="DELETE FROM POST WHERE ID=?1 ")
  void deletePostById(int id);

  @Transactional
  @Modifying
  @Query(nativeQuery = true,value="INSERT INTO POST (BODY,date,TITLE,USER_user_NAME) VALUES (?1,NOW(),?2,?3)")
  void addPostValues(String body,String title,String username);

  @Transactional
  @Modifying
  @Query(nativeQuery = true,value="update post set body=?1,title=?2,date=now() where id=?3")
  void editPostValues(String body,String title,int id);

  @Transactional
  @Modifying
  @Query(nativeQuery = true,value="insert into likes (post_id,user_name) values (?1,?2)")
  void addLikes(int postid,String uname);

  @Transactional
  @Modifying
  @Query(nativeQuery = true,value="insert into comments (post_id,user_name,comment) values (?1,?2,?3)")
  void addComment(int postid,String uname,String comment);

  @Query(nativeQuery = true,value="select user_user_name from post where id=?1")
  String findUserByPostId(int id);

  @Query(nativeQuery = true,value="select * from comments where post_id=?1")
  Iterable<String> getCommentbyPost(int id);

  @Query(nativeQuery = true,value="select * from likes where post_id=?1")
  Iterable<String> getLikesbyPost(int id);
}
