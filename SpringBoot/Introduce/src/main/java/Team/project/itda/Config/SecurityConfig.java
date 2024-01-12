package Team.project.itda.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/resources/**", "/static/**").permitAll() // 정적자원 허용
                        .requestMatchers("/CSS/**").permitAll() // css 허용
                        .requestMatchers("/", "/login", "/loginProcess", "/join", "/joinProcess").permitAll() // 모두 허용
                        .requestMatchers("/my/**").hasAnyRole("USER") // 관리자, 회원
                        .anyRequest().authenticated()
                )
                .formLogin((auth) -> auth.loginPage("/login") // 로그인 페이지
                        .loginProcessingUrl("/loginProcess")   // 프론트단에서 보낸 정보를 security에서 처리함
                        .permitAll()
                );

        return http.build();
    }

}