package Team.project.itda.Repository;


import Team.project.itda.Entity.BoardEntity;
import Team.project.itda.Entity.FormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
}
