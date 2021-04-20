//db에 회원정보 등록을 위한 클래스

package com.school.springboot.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.springboot.model.RoleType;
import com.school.springboot.model.User;
import com.school.springboot.repository.UserRepository;

import ch.qos.logback.core.encoder.Encoder;

@Service //스프링이 component 스캔을 통해서 Bean에 등록을 해줌. -> IoC를 해줌 (메모리에 띄워줌)
public class UserService {
	
	@Autowired //DI 객체불러오기(?)
	private UserRepository userRepository;
	
	@Autowired //encoder를 DI해서 주입
	private BCryptPasswordEncoder encoder;
	
	@Transactional//commit을 위해
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //패스워드 원문
		String encPassword = encoder.encode(rawPassword); //해시된 패스워드
		user.setPassword(encPassword); //패스워드 db에 저장
		user.setRole(RoleType.ROLE_NOT_PERMITTED); //역할 db에 저장
		userRepository.save(user);
	}
}
