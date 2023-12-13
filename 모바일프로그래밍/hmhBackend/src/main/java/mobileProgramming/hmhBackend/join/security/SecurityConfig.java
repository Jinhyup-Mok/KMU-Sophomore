package mobileProgramming.hmhBackend.join.security;

import lombok.RequiredArgsConstructor;
import mobileProgramming.hmhBackend.join.jwt.JwtAuthenticationFilter;
import mobileProgramming.hmhBackend.join.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Spring Security를 사용하여 보안 구성을 정의
// URL 패턴들에 대한 접근을 설정하고, 기본 인증 방식, 세션 관리 및 암호화 방식을 정의하여 Spring Security 구성을 제공.
// 위 설정은 주로 API 문서 접근과 회원가입, 로그인 등의 엔드포인트에 대한 보안 규칙을 정의하고 있다.
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/","/v2/api-docs", "/v3/api-docs/**", "/configuration/ui", "/swagger-resources/**",
                                "/configuration/security", "/swagger-ui.html", "/webjars/**", "/file/**", "/image/**",
                                "/swagger/**", "/swagger-ui/**", "/h2/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/member/email", "/member/{id}", "/member/login", "/member/join", "/member/insertImage/**").permitAll()
                .antMatchers("/member/follow/**").permitAll() // follow DB 접근 권한 설정, 여기에 CRUD mapping 권한 추가 -> .permitAll()
                .antMatchers("/member/place/**").permitAll()
                .antMatchers("/member/posting/**").permitAll()
                .antMatchers("/member/todoList/**").permitAll()
                .antMatchers("/member").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .addFilter(corsConfig.corsFilter());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
