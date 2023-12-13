package mobileProgramming.hmhBackend.place.service;

import lombok.RequiredArgsConstructor;
import mobileProgramming.hmhBackend.join.entity.Member;
import mobileProgramming.hmhBackend.join.entity.MemberRepository;
import mobileProgramming.hmhBackend.place.domain.Place;
import mobileProgramming.hmhBackend.place.domain.PlaceRepository;
import mobileProgramming.hmhBackend.place.dto.PlaceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final MemberRepository memberRepository;

    public List<Place> findPlaces(Long member) {
        Optional<Member> optionalMember = memberRepository.findById(member);
        if (optionalMember.isPresent()) {
            return optionalMember.get().getPlaces();
        } else {
            throw new RuntimeException();
        }
    }

    public void savePlace(PlaceDto placeDto) {
        Optional<Member> optionalMember = memberRepository.findById(placeDto.getMember());
        if (optionalMember.isPresent()) {
            Place place = Place.builder()
                    .id(placeDto.getId())
                    .member(optionalMember.get())
                    .name(placeDto.getName())
                    .address(placeDto.getAddress())
                    .build();
            placeRepository.save(place);
            optionalMember.get().addPlace(place);
        } else {
            throw new RuntimeException();
        }
    }

    public void deletePlace(PlaceDto placeDto) {
        Optional<Place> deletingPlace = placeRepository.findById(placeDto.getId());
        if (deletingPlace.isPresent()) {
            Place place = deletingPlace.get();
            place.getMember().delPlace(place);
            placeRepository.delete(place);
        } else {
            throw new RuntimeException();
        }
    }
}
