package mobileProgramming.hmhBackend.join.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mobileProgramming.hmhBackend.join.entity.Member;
import mobileProgramming.hmhBackend.join.entity.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

// 데이터 전송 객체(DTO), 사용자로부터 받은 데이터를 전달하고 해당 데이터를 엔티티로 변환하는 역할
@Data // getters, setters, toString, equals, hashcode 메서드 자동 생성
@Builder
@AllArgsConstructor
public class MemberSignUpRequestDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    private String name;

    private String checkedPassword;

    private Role role;

    // DTO를 실제 엔티티로 변환하는 메서드
    // Member 엔티티를 생성하고, DTO의 필드 값을 엔티티 필드에 설정한 후 엔티티를 반환
    @Builder
    public Member toEntity() {
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .role(Role.USER) // 사용자의 역할 할당
                .build();
    }
}
