package Team.project.itda.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class FormEntity {

    @Id
    @GeneratedValue
    private Long id;
}
