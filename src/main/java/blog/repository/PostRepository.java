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

  @Query(nativeQuery = true,value="select user_user_name from post where id=?1")
  String findUserByPostId(int id);

}
//public interface ShowsRepository extends CrudRepository<Shows, Integer> {
//
//
//  @Query(nativeQuery = true,value="SELECT  * FROM SHOWS WHERE UPPER(CITY) = UPPER (?1) ")
//  List<Shows> findAllShowsByCity(String city1);
//
//  @Query(nativeQuery = true,value="SELECT  * FROM SHOWS WHERE SHOWID=?1 AND AVAILABILITY >= ?2 AND date>=NOW()  ")
//  String findTicketAvailability(int showId,int quantity);
//
//  @Transactional
//  @Modifying
//  @Query(nativeQuery = true,value="UPDATE SHOWS SET AVAILABILITY=(AVAILABILITY-?2) WHERE SHOWID=?1")
//  void findBooking(int showId,int quantity);
//
//  @Transactional
//  @Modifying
//  @Query(nativeQuery = true,value="INSERT INTO SHOWS VALUES (DEFAULT,?1,?2,?3,?4,?5)")
//  void addNewShows(int availability,String city,String Language,Date date1,int movieid);
//}


