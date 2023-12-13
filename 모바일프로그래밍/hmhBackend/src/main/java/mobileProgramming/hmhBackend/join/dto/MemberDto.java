package mobileProgramming.hmhBackend.join.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mobileProgramming.hmhBackend.join.entity.Role;

@AllArgsConstructor
@Builder
@Getter
public class MemberDto {
    private Long id;
    private String email;
    private String name;
    private String birth;
    private String password;
    private String image;
    private Role role;
}
