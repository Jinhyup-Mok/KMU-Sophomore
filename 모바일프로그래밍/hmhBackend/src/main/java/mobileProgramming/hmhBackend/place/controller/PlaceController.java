package mobileProgramming.hmhBackend.place.controller;

import lombok.RequiredArgsConstructor;
import mobileProgramming.hmhBackend.place.domain.Place;
import mobileProgramming.hmhBackend.place.dto.PlaceDto;
import mobileProgramming.hmhBackend.place.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/place/{member}")
    public List<Place> placeList(@PathVariable Long member) {
        return placeService.findPlaces(member);
    }

    @PostMapping("/place")
    public void savePlace(@RequestBody PlaceDto placeDto) {
        placeService.savePlace(placeDto);
    }

    @DeleteMapping("/place")
    public void deletePlace(@RequestBody PlaceDto placeDto) {
        placeService.deletePlace(placeDto);
    }
}
