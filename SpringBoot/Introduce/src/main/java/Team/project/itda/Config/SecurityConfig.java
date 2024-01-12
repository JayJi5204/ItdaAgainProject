package Team.project.itda.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { // 암호화

        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/resources/**", "/static/**").permitAll() // 정적자원 허용
                        .requestMatchers("/CSS/**", "/Image/**", "/JQuery/**").permitAll() // css 허용
                        .requestMatchers("/", "/login", "/loginProcess", "/join", "/joinProcess").permitAll() // 모두 허용
                        .requestMatchers("/my/**", "/account").hasAnyRole("USER") // 회원
                        .anyRequest().authenticated()
                )
                .formLogin((auth) -> auth.loginPage("/login") // 로그인 페이지
                        .loginProcessingUrl("/loginProcess")   // 프론트단에서 보낸 정보를 security에서 처리함
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout
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

}