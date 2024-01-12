package Team.project.itda.Repository;

import Team.project.itda.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String userName);
    boolean existsByName(String Name);

    UserEntity findByUsername(String username);
}
