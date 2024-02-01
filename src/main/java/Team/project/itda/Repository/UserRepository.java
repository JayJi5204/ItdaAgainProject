package Team.project.itda.Repository;

import Team.project.itda.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String userName);
    Optional<UserEntity> findById(Long id);
}
