package Team.project.itda.Config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화, Thymeleaf는 csrf 토큰을 자동으로 주입하기 때문에 주석처리함
                .authorizeHttpRequests((auth) -> auth   // 인증, 인가 설정
                        .requestMatchers("/", "/login", "/join", "/joinProcess", "/board/**","/introduce","/goal").permitAll() // 모두 허용
                        .requestMatchers("/my/**", "/account").hasAnyRole("USER") // 회원
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
                        .invalidateHttpSession(true)
                )
                .sessionManagement((auth) -> auth   // 다중 로그인 설정
                        .maximumSessions(1) // 하나의 아이디에 대한 다중 로그인 허용 개수
                        .maxSessionsPreventsLogin(true)  //다중 로그인 개수를 초과하였을 경우 처리 방법
                );                                        // true 새로운 로그인 차단/false 기존 세션 삭제
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId()); //세션 고정 보호 - 로그인 시 세션 새로 생성

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
}