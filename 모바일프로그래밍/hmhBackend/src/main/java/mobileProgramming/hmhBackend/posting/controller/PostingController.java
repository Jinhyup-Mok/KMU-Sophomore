package mobileProgramming.hmhBackend.posting.controller;

import lombok.RequiredArgsConstructor;
import mobileProgramming.hmhBackend.posting.domain.Posting;
import mobileProgramming.hmhBackend.posting.dto.PostingDto;
import mobileProgramming.hmhBackend.posting.service.PostingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("member")
@RequiredArgsConstructor
@RestController
public class PostingController {

    private final PostingService postingService;

    @GetMapping("/posting")
    public List<Posting> allPostings() {
        return postingService.findAll();
    }

    @GetMapping("/posting/{member}")
    public List<Posting> memberPostings(@PathVariable Long member) {
        return postingService.findByMember(member);
    }

    @PostMapping("/posting")
    @PutMapping("/posting")
    public void savePosting(@RequestBody PostingDto postingDto) {
        postingService.savePosting(postingDto);
    }

    @DeleteMapping("/posting")
    public void deletePosting(@RequestBody PostingDto postingDto) {
        postingService.deletePosting(postingDto);
    }

}
