package mobileProgramming.hmhBackend.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class FollowDto {

    private Long id;
    private Long followingId;

}
