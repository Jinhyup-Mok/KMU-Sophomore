package mobileProgramming.hmhBackend.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class PostingDto {

    private Long id;
    private Long member;
    private Long place;
    private String content;
    private Long rating;

}
