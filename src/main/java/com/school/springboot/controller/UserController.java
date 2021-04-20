package com.school.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//auth: 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth 이하 경로만 허용할 것임.
//그냥 주소가 /인 주소는 index.jsp로 가는데 여기도 허용해줄 것임.
//나머지 static 이하에 있는  resource 파일들 js파일이나, css나, image나 여기까지만 허용해준다.
//즉 인증이 필요없는 곳에 /auth가 붙음

@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "login/signup";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "login/signup";
	}
}
