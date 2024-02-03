package Team.project.itda.Config;


import Team.project.itda.Service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    final private CustomUserDetailService customUserDetailService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // csrf 비활성화, Thymeleaf는 csrf 토큰을 자동으로 주입한다.
//                .csrf((csrf) -> csrf
//                        .ignoringRequestMatchers("/api/users") // 특정 경로 csrf 비활성화, 회원가입 API
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests((auth) -> auth   // 인증, 인가 설정
                        .requestMatchers("/", "/login", "/join", "/joinProcess", "/board/**", "/introduce", "/goal", "/denied", "/api/**").permitAll() // 모두 허용
                        .requestMatchers("/update", "/user/**", "/account", "/pay/**").hasRole("USER")
                        .anyRequest().authenticated()

                )
                .formLogin((auth) -> auth   // 폼 기반 로그인 설정
                        .loginPage("/login") // 로그인 페이지
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout  // 로그아웃 설정
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID", "remember-me")
                        .invalidateHttpSession(true)
                )
                .rememberMe((remember) -> remember
                        .rememberMeParameter("remember-me") // default: remember-me
                        .tokenValiditySeconds(3600) // 쿠키의 만료시간 설정(초), default: 14일
                        .alwaysRemember(false) // 사용자가 체크박스를 활성화하지 않아도 항상 실행, default: false
                        .userDetailsService(customUserDetailService)
                )
                .sessionManagement((auth) -> auth   // 다중 로그인 설정
                        .maximumSessions(1) // 하나의 아이디에 대한 다중 로그인 허용 개수
                        .maxSessionsPreventsLogin(false)  //다중 로그인 개수를 초과하였을 경우 처리 방법
                );                                        // true 새로운 로그인 차단/false 기존 세션 삭제
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId() //세션 고정 보호 - 로그인 시 세션 새로 생성
                );
        http
                .exceptionHandling((exceptionHandling) -> exceptionHandling // 인증 거부 처리
                        .accessDeniedHandler(accessDeniedHandler()));

        return http.build();
    }

    @Bean   // 정적 자원 시큐리티 비활성화
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web
                .ignoring()
                .requestMatchers("/CSS/**", "/Image/**", "/JQuery/**");        // 정적 리소스 spring security 대상에서 제외
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()); // 정해진 enum 값으로 폴더명을 변경해야함
    }

    @Bean   // 암호화
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean   //인증 거부 처리 메서드
    public AccessDeniedHandler accessDeniedHandler() {

        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");

        return accessDeniedHandler;
    }
}