package mobileProgramming.hmhBackend.join.service;

import lombok.RequiredArgsConstructor;
import mobileProgramming.hmhBackend.join.entity.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// Spring Security에 존재하는 UserDetailsService를 구현한 클래스
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {
    // UserDetailsService : Spring Security에서 인증에 사용되는 인터페이스로, 사용자 세부 정보를 검색하고 인증을 위해 제공

    private final MemberRepository memberRepository;

    // 사용자 이름(이메일)을 기반으로 사용자 정보를 검색
    // 입력된 사용자 이메일에 해당하는 정보를 데이터베이스에서 찾아 UserDetails 인터페이스로 구현한 객체로 반환
    // 사용자를 찾을 수 없는 경우 UsernameNotFoundException을 던져 예외 발생
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
