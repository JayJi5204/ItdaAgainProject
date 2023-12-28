package Team.project.itda.DTO;


import Team.project.itda.Entity.FormEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FormDTO {

    private Long id;


    public static FormDTO toFormDTO(FormEntity formEntity) {
        return new FormDTO(formEntity.getId());
    }
}
