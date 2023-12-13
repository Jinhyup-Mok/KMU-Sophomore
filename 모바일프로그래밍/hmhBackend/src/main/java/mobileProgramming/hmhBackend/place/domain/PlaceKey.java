package mobileProgramming.hmhBackend.place.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class PlaceKey implements Serializable {

    private Long id;
    private Long member;

}
