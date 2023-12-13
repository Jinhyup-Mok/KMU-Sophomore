package mobileProgramming.hmhBackend.posting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mobileProgramming.hmhBackend.join.entity.Member;
import mobileProgramming.hmhBackend.place.domain.Place;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member", referencedColumnName = "id")
    private Member member;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "place", referencedColumnName = "id")
    private Place place;

    @Column
    private String content;

    @Column
    private Long rating;
}
