package Team.project.itda.DTO;


import Team.project.itda.Entity.FormEntity;
import lombok.Getter;

@Getter
public class FormDTO {

    private final Long id;


    public FormDTO(Long id) {
        this.id = id;

    }

    public static FormDTO toFormDTO(FormEntity formEntity) {
        return new FormDTO(formEntity.getId());
    }
}
