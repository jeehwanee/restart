package com.school.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.springboot.model.User;


//Data Access Object라 치면 됨. JpaRepository를
// 자동으로 Bean 등록이 된다. 그래서 @Repository 어노테이션이 필요 없음.
//@Repository : 생략 가능
public interface UserRepository extends JpaRepository<User, Integer > { 
	//JpaRepository 얘는 user테이블이 관리하는 Repository. 이 user테이블의 프라이머리키는 integer다. 라는 의미
	//JpaRepository는 대표젹으로 User테이블이 들고있는 모든 행을 다 리턴해라라는 findAll()이라는 함수를 들고 있음.
	
	
/* 전통 로그인 방식 연관 UserApiController, userservice
	//로그인을 위한 함수
	//JPA Naming 쿼리 전략
	User findByUsernameAndPassword(String username, String password);
	//Select * FROM user WHERE username = ?1 AND password = ?2; 이런 쿼리가 동작
	//-> Select * FROM user WHERE username = username AND password = password; 
	
//똑같음	@Query(value="Select * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);
 * 
 */
}
