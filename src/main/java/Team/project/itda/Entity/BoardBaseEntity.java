package Team.project.itda.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 어노테이션이 적용된 클래스는 테이블로 생성되지 않습니다 실제 테이블은 BaseEntity 클래스를 상속한 엔티티의 클래스로 테이블이 생성됩니다
// 주로 공통의 매핑 정보가 필요할 때 사용된다
@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class})
@Getter
abstract class BoardBaseEntity {

    @CreatedDate
    @Column(name = "createDt", updatable = false)
    private LocalDateTime createDt; //작성일

    @LastModifiedDate
    @Column(name = "updateDt")
    private LocalDateTime updateDt; //수정시간


}