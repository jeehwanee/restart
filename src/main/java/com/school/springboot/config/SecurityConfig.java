//
package com.school.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration //빈orBean? 등록: 스프링컨테이너에서 객체를 관리할 수 있게 하는 것. IoC로 관리
@EnableWebSecurity //스프링 시큐리티 필터 등록 - SecurityConfig 클래스에 필터 등록
//원래 Request가 될 때 Controller로 가서 함수가 실행되는데, 시큐리티가 모든 request를 가로채서 아래 필터링 수행
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크
//위의 3개의 어노테이션은 스프링시큐리티 로그인에서 세트임
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    //스프링 시큐리티 해시암호화
    @Bean //해시암호 리턴값을 스프링이 관리하는 IoC가 됨.
    public BCryptPasswordEncoder encodePWD() { //encodePWD()함수를 호출하면 BCryptPasswordEncoder()를 리턴받을 수 있음
        return new BCryptPasswordEncoder(); //스프링시큐리티가 들고있는 함수 해시암호화 수행
    }




    //인증 여부에 따른 홈페이지 안내경로
    @Override //httpsecurity 오버라이딩
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf토큰 비활성화 (테스트 시 걸어두는 게 좋음)
                .authorizeRequests()
                .antMatchers("/","/auth/**","/js/**","/css/**","/image/**", "/email", "/searchPw.do", "/check") //인증없이 허용되는 경로들
                .permitAll() //auth request 다 허용
                .anyRequest() //그외 다른 요청은
                .authenticated() //인증이 되어야 한다.
                .and()
                .formLogin() //인증이 필요한 곳으로 요청이 오면 loginForm 페이지로 이동
                .loginPage("/auth/loginForm");
    }

}
