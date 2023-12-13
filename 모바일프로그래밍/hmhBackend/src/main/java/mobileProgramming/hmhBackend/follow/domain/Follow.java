package mobileProgramming.hmhBackend.follow.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mobileProgramming.hmhBackend.join.entity.Member;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(FollowKey.class)
public class Follow {

    @Id
    @JsonIgnore // Getmapping("/follow/{id}") 오류 해결 -> 연관관계 문제
    @ManyToOne
    @JoinColumn(name = "id")
    private Member member;

    @Id
    private Long followingId;


}
