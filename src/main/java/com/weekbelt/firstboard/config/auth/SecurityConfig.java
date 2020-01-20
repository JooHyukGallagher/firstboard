package com.weekbelt.firstboard.config.auth;

import com.weekbelt.firstboard.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()     // h2-console 화면을 사용하기 위해 해당 옵션을 disable
                .and()
                    .authorizeRequests()                // URL별 권한 관리를 설정하는 옵션의 시작점입니다.
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/api/board/list").permitAll()  // 전체 열람 권한
                    .antMatchers("/api/**").hasRole(Role.USER.name())                                   // POST 메소드이면서 "/api/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 설정
                    .anyRequest().authenticated()                                                                        // 설정되 값들 이외 나머지 URL들을 나타냅니다. authenticated()를 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용하도록(로그인한 사용자들)
                .and()
                    .logout()
                        .logoutSuccessUrl("/")      // 로그아웃 기능에 대한 여러 설정의 진입점입니다. 로그아웃 성공 시 / 주소로 이동합니다.
                .and()
                    .oauth2Login()  // OAuth2 로그인 기능에 대한 여러 설정의 진입점 입니다.
                        .userInfoEndpoint() // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당합니다.
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스와 구현체를 등록 합니다.
    }
}
