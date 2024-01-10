package Team.project.itda.Repository;

import Team.project.itda.Entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {
    AccountEntity findFirstByOrderByIdDesc();
}
