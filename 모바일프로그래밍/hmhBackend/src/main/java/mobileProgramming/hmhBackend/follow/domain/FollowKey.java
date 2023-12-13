package mobileProgramming.hmhBackend.follow.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowKey implements Serializable {
    private Long member;
    private Long followingId;

}
