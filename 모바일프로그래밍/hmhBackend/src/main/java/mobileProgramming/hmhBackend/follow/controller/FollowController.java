package mobileProgramming.hmhBackend.follow.controller;

import lombok.RequiredArgsConstructor;
import mobileProgramming.hmhBackend.follow.dto.FollowDto;
import mobileProgramming.hmhBackend.follow.service.FollowService;
import mobileProgramming.hmhBackend.join.entity.Member;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class FollowController {

    private final FollowService followService;

    /**
     * Select following user list
     * @return following user list
     */
    @GetMapping(value = "/follow/{id}")
    public List<Member> followList(@PathVariable Long id) {
        return followService.findFollowingList(id);
    }

    /**
     * follow other user
     */
    @PostMapping("/follow")
    public void followOther(@Valid @RequestBody FollowDto followDto){
        followService.following(followDto);
    }

    /**
     * unfollow other user
     */
    @DeleteMapping(value = "/follow")
    public void unfollowOther(@RequestBody FollowDto followDto) {
        followService.unfollow(followDto);
    }
}
