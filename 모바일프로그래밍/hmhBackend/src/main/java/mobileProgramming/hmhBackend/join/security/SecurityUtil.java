package mobileProgramming.hmhBackend.join.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

// Spring Security를 사용하여 현재 인증된 사용자의 이름을 반환
public class SecurityUtil {

    // 현재 인증된 사용자의 이름을 반환
    // SecurityContextHolder를 사용하여 사용자의 UserDetails 객체를 가져온 후, 객체에서 사용자 이름을 추출하여 반환
    public static String getLoginUsername() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // SecurityContextHolder는 Spring Security의 전역 보안 정보를 제공하는 클래스
        // getContext() 현재 실행 중인 스레드에 연관된 'SecurityContext'를 가져옴
        // getAuthentication() 인증된 사용자의 정보를 나타내는 Authentication 객체 반환
        // getPrincipal() 현자 사용자를 나타내는 객체 반환 -> 사용자의 세부 정보를 정의

        return user.getUsername();
    }
}
