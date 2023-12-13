package mobileProgramming.hmhBackend.todolist.controller;

import lombok.RequiredArgsConstructor;
import mobileProgramming.hmhBackend.todolist.domain.TodoList;
import mobileProgramming.hmhBackend.todolist.dto.TodoListDto;
import mobileProgramming.hmhBackend.todolist.service.TodoListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class TodoListController {

    private final TodoListService todoListService;

    @PutMapping("/todoList/{id}")
    public void editDone(@PathVariable Long id) { todoListService.editDone(id);}

    @GetMapping("/todoList/{member}")
    public List<TodoList> todos(@PathVariable Long member) {
        return todoListService.getTodos(member);
    }

    @PostMapping("/todoList")
    @PutMapping("/todoList")
    public void saveTodo(@RequestBody TodoListDto todoListDto) {
        todoListService.saveTodo(todoListDto);
    }

    @DeleteMapping("/todoList")
    public void deleteTodo(@RequestBody TodoListDto todoListDto) {
        todoListService.deleteTodo(todoListDto);
    }

}
