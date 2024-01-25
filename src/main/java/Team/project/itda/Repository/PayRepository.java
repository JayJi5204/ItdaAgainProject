package Team.project.itda.Repository;

import Team.project.itda.Entity.PayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<PayEntity,Long> {
        PayEntity findFirstByOrderByPayIdDesc();
}
