package Team.project.itda.Repository;

import Team.project.itda.Entity.PayEntity;
import Team.project.itda.Entity.UserEntity;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PayRepository extends JpaRepository<PayEntity, Long> {
    PayEntity findFirstByUserEntityOrderByPayIdDesc(UserEntity userEntity);
    List<PayEntity> findByUserEntityOrderByPayIdDesc(UserEntity userEntity);

}
