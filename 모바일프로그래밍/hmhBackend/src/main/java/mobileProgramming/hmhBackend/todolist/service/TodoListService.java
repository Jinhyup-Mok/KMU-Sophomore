package mobileProgramming.hmhBackend.todolist.service;

import lombok.RequiredArgsConstructor;
import mobileProgramming.hmhBackend.join.entity.Member;
import mobileProgramming.hmhBackend.join.entity.MemberRepository;
import mobileProgramming.hmhBackend.place.domain.Place;
import mobileProgramming.hmhBackend.place.domain.PlaceRepository;
import mobileProgramming.hmhBackend.todolist.domain.TodoList;
import mobileProgramming.hmhBackend.todolist.domain.TodoListRepository;
import mobileProgramming.hmhBackend.todolist.dto.TodoListDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final MemberRepository memberRepository;
    private final PlaceRepository placeRepository;

    public List<TodoList> getTodos(Long member) {
        Optional<Member> optionalMember = memberRepository.findById(member);
        if (optionalMember.isPresent()) {
            return optionalMember.get().getTodoLists();
        } else {
            throw new RuntimeException();
        }
    }

    public void saveTodo(TodoListDto todoListDto) {
        Optional<Member> optionalMember = memberRepository.findById(todoListDto.getMember());
        Optional<Place> optionalPlace = placeRepository.findById(todoListDto.getPlace());

        TodoList todoList = TodoList.builder()
                .id(todoListDto.getId())
                .member(optionalMember.get())
                .place(optionalPlace.get())
                .content(todoListDto.getContent())
                .date(todoListDto.getDate())
                .done(todoListDto.getDone())
                .build();
        todoListRepository.save(todoList);

        optionalMember.ifPresent(member -> member.addTodoList(todoList));
        optionalPlace.ifPresent(place -> place.addTodo(todoList));
    }

    public void deleteTodo(TodoListDto todoListDto) {
        Optional<TodoList> optionalTodoList = todoListRepository.findById(todoListDto.getId());
        if (optionalTodoList.isPresent()) {
            TodoList todoList = optionalTodoList.get();
            todoList.getMember().delTodoList(todoList);
            todoListRepository.delete(todoList);
        } else {
            throw new RuntimeException();
        }
    }

    public void editDone(Long id) {
        Optional<TodoList> optionalTodoList = todoListRepository.findById(id);
        if (optionalTodoList.isPresent()) {
            TodoList todoList = optionalTodoList.get();
            todoList.setDone(!todoList.getDone());
            todoListRepository.save(todoList);
        } else {
            throw new RuntimeException();
        }
    }
}
