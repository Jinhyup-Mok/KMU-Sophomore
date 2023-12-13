package mobileProgramming.hmhBackend.join.service;


import lombok.RequiredArgsConstructor;
import mobileProgramming.hmhBackend.join.dto.MemberDto;
import mobileProgramming.hmhBackend.join.dto.MemberSignInRequestDto;
import mobileProgramming.hmhBackend.join.dto.MemberSignUpRequestDto;
import mobileProgramming.hmhBackend.join.entity.Member;
import mobileProgramming.hmhBackend.join.entity.MemberRepository;
import mobileProgramming.hmhBackend.join.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 서비스 레이어
@RequiredArgsConstructor // final 또는 @NonNull로 표시된 필드들을 가지고 생성자를 자동으로 생성
@Transactional
@Service
public class MemberServiceMain implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    // 회원가입
    @Override
    public Long signUp(MemberSignUpRequestDto requestDto) throws Exception {
        // 중복된 이메일 확인
        if(memberRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        // 비밀번호 확인 (비밀번호 vs 확인용 비밀번호) -> 비밀번호가 일치하는지 확인
        if(!requestDto.getPassword().equals(requestDto.getCheckedPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        Member member = memberRepository.save(requestDto.toEntity()); // 회원 정보를 데이터베이스에 저장
        member.encodePassword(passwordEncoder); // 비밀번호를 암호화하고 저장된 회원 정보에 적용

        member.addUserAuthority(); // 사용자 권한 추가
        return member.getId();
    }

    @Override
    public String signIn(MemberSignInRequestDto requestDto) throws Exception {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다."));

        String password = requestDto.getPassword();
        if(!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        List<String> roles = new ArrayList<>();
        roles.add(member.getRole().name());

        return JwtTokenProvider.createToken(member.getName(), roles);
    }

    @Override
    public void insertImage(String imageFile, Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setImage(imageFile);
            memberRepository.save(member);
        }
    }

    @Override
    public MemberDto memberInfo(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return MemberDto.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .name(member.getName())
                    .birth(member.getBirth())
                    .password(member.getPassword())
                    .image(member.getImage())
                    .role(member.getRole())
                    .build();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Long findIdByEmail(MemberSignInRequestDto memberSignInRequestDto) {
        Optional<Member> optionalMember = memberRepository.findByEmail(memberSignInRequestDto.getEmail());
        if (optionalMember.isPresent()) {
            return optionalMember.get().getId();
        } else {
            throw new RuntimeException();
        }
    }

}
