package com.example.soft_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // 🔥 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔥 HTTP Security 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 일단 CSRF 꺼두고
                .authorizeHttpRequests(auth -> auth
                        // Swagger 관련 경로는 모두 허용
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()
                        // 로그인/회원가입 API도 일단 다 열어두기
                        .requestMatchers("/api/auth/**").permitAll()
                        // 나머지도 지금은 전부 허용 (개발용)
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.disable())   // 기본 로그인 폼 비활성화
                .httpBasic(basic -> basic.disable()); // 기본 브라우저 팝업 로그인도 비활성화

        return http.build();
    }
}
