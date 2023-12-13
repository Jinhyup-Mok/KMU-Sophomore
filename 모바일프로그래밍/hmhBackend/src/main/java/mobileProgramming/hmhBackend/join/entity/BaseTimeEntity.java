package mobileProgramming.hmhBackend.join.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA에서 공통적으로 매핑 정보를 상속받아 사용할 수 있도록하는 어노테이션
@EntityListeners(AuditingEntityListener.class) // 생성일과 수정일을 자동으로 관리하기 위해
public class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createDate; // 생성일 저장

    @LastModifiedDate
    private LocalDateTime modifiedDate; // 수정일 저장
}
