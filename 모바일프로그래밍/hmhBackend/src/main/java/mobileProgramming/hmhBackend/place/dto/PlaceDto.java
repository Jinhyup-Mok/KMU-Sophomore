package mobileProgramming.hmhBackend.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class PlaceDto {

    private Long id;
    private Long member;
    private String name;
    private String address;

}
