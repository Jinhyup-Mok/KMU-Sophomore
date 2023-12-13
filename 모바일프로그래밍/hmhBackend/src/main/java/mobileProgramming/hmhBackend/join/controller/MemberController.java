package mobileProgramming.hmhBackend.join.controller;

import lombok.RequiredArgsConstructor;
import mobileProgramming.hmhBackend.join.dto.MemberDto;
import mobileProgramming.hmhBackend.join.dto.MemberSignInRequestDto;
import mobileProgramming.hmhBackend.join.dto.MemberSignUpRequestDto;
import mobileProgramming.hmhBackend.join.entity.MemberRepository;
import mobileProgramming.hmhBackend.join.service.MemberServiceMain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


// 회원 관련 기능을 처리하는 RESTful API를 제공
@RequiredArgsConstructor
@RequestMapping("/member")
@RestController // RESTful 컨트롤러로 지정, 각 메서드의 리턴값은 HTTP Response Body로 전송됨
public class MemberController {

    private final MemberServiceMain memberServiceMain;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberSignInRequestDto request) throws Exception {
        return ResponseEntity.ok().body(memberServiceMain.signIn(request));
    }
    @PostMapping("/join") // Http POST 요청에 매핑되는 엔드포인트로, '/member/join' 경로에 POST 요청을 처리하는 메서드
    @ResponseStatus(HttpStatus.OK) // 메서드의 Http 응답 상태 코드를 200(ok)로 지정
    public Long join(@Valid @RequestBody MemberSignUpRequestDto request) throws Exception {
        return memberServiceMain.signUp(request);
    }
    // @Valid : 유효성 검사를 수행, @RequestBody : http 요청의 body에서 데이터를 가져옴
    // MemberSignUpRequestDto 형식의 요청 데이터를 처리하여 회원 가입 동작 수행 - request
    // memberService.signUp(request)를 호출하여 회원가입 기능 수행, 반환되는 회원의 ID를 HTTP응답으로 반환

    @PostMapping("/insertImage/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void insertImage(@RequestParam("imageFile") String imageFile, @PathVariable Long id) {
        memberServiceMain.insertImage(imageFile, id);
    }

    /**
     *
     * @param id
     * @return MemberDto
     */
    @GetMapping("/{id}")
    public MemberDto memberInfo(@PathVariable Long id) {
        return memberServiceMain.memberInfo(id);
    }

    @PostMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public Long findId(@RequestBody MemberSignInRequestDto memberSignInRequestDto) { return memberServiceMain.findIdByEmail(memberSignInRequestDto); }

}
