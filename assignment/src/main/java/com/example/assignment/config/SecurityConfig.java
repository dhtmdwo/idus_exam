package com.example.assignment.config;

import com.example.assignment.config.filter.JwtFilter;
import com.example.assignment.config.filter.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationConfiguration configuration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable); // CSRF 공격 방어 비활성화(JWT 사용)
        http.httpBasic(AbstractHttpConfigurer::disable); // 기본 HTTP Basic 인증 비활성화 (JWT 사용)
        http.formLogin(AbstractHttpConfigurer::disable); // 기본 로그인 폼 비활성화(커스텀 로그인 로직)
        http.sessionManagement(AbstractHttpConfigurer::disable); // 세션 사용 안함(JWT 사용)


        http.authorizeHttpRequests(
                (auth) -> auth
                        .requestMatchers("/user", "/user/signup", "/login").permitAll()
                        .anyRequest().permitAll()
        );

        // URL별 접근 권한 설정


        http.addFilterAt(new LoginFilter(configuration.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class);
        // UsernamePasswordAuthenticationFilter 대신에 LoginFilter 사용한다.
        // 사용자가 로그인하면 JWT 생성하여 응답
        http.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
        // UsernamePasswordAuthenticationFilter 실행 전에 JwtFilter 넣는다.
        return http.build();


    }

}
