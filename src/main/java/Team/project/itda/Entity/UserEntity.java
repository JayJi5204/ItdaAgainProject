package Team.project.itda.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long id;    // pk

    @Column(unique = true, nullable = false)
    private String username;     // 로그인 ID

    @Column(nullable = false)
    private String password;    // 비밀번호

    @Column(nullable = false)
    private String name;    // 이름

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<PayEntity> payEntities = new ArrayList<>();

    @Builder
    public UserEntity (String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public void passwordEncode(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.password = bCryptPasswordEncoder.encode(password);
    }


    public void addUserAuthority() {
        this.role = UserRole.ROLE_USER;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void updateUserPassword(String password){
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new SimpleGrantedAuthority(role.name()));
        return collect;
    }

    @Override
    public String getPassword() {
        return password;
    }



    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true; // true -> 잠금되지 않음
    }

    // 패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // true -> 만료되지 않음
    }

    // 계정 사용 가능 여부 변환
    @Override
    public boolean isEnabled() {
        return true; // true -> 사용 가능
    }
}