package mobileProgramming.hmhBackend.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TodoListDto {

    private Long id;
    private Long member;
    private Long place;
    private String content;
    private String date;
    private Boolean done;

}
