package Team.project.itda.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class BoardEntity {
    @Id @GeneratedValue
    private Long id;

    private String title;

    private String content;


}
