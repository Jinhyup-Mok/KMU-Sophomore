package mobileProgramming.hmhBackend.posting.service;

import lombok.RequiredArgsConstructor;
import mobileProgramming.hmhBackend.join.entity.Member;
import mobileProgramming.hmhBackend.join.entity.MemberRepository;
import mobileProgramming.hmhBackend.place.domain.Place;
import mobileProgramming.hmhBackend.place.domain.PlaceRepository;
import mobileProgramming.hmhBackend.posting.domain.Posting;
import mobileProgramming.hmhBackend.posting.domain.PostingRepository;
import mobileProgramming.hmhBackend.posting.dto.PostingDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostingService {

    private final PostingRepository postingRepository;
    private final MemberRepository memberRepository;
    private final PlaceRepository placeRepository;

    public List<Posting> findAll() {
        return postingRepository.findAll();
    }

    public List<Posting> findByMember(Long member) {
        Optional<Member> optionalMember = memberRepository.findById(member);
        if (optionalMember.isPresent()) {
            return optionalMember.get().getPostings();
        } else {
            throw new RuntimeException();
        }
    }

    public void savePosting(PostingDto postingDto) {
        Optional<Member> optionalMember = memberRepository.findById(postingDto.getMember());
        Optional<Place> optionalPlace = placeRepository.findById(postingDto.getPlace());

        Posting posting = Posting.builder()
                    .id(postingDto.getId())
                    .member(optionalMember.get())
                    .place(optionalPlace.get())
                    .content(postingDto.getContent())
                    .rating(postingDto.getRating())
                    .build();
        postingRepository.save(posting);

        optionalMember.ifPresent(member -> member.addPosting(posting));
        optionalPlace.ifPresent(place -> place.addPosting(posting));
    }

    public void deletePosting(PostingDto postingDto) {
        Optional<Posting> deletingPosting = postingRepository.findById(postingDto.getId());
        if (deletingPosting.isPresent()) {
            Posting posting = deletingPosting.get();
            posting.getMember().delPosting(posting);
            postingRepository.delete(posting);
        } else {
            throw new RuntimeException();
        }
    }
}
