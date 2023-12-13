package mobileProgramming.hmhBackend.join.service;

import mobileProgramming.hmhBackend.join.dto.MemberDto;
import mobileProgramming.hmhBackend.join.dto.MemberSignInRequestDto;
import mobileProgramming.hmhBackend.join.dto.MemberSignUpRequestDto;

public interface MemberService {

    // 회원가입
    public Long signUp(MemberSignUpRequestDto requestDto) throws Exception;

    public String signIn(MemberSignInRequestDto requestDto) throws Exception;

    //프로필 사진 추가
    public void insertImage(String imageFile, Long id) throws Exception;

    public MemberDto memberInfo(Long id) throws Exception;

    public Long findIdByEmail(MemberSignInRequestDto memberSignInRequestDto) throws Exception;
}
