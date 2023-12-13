package mobileProgramming.hmhBackend.place.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mobileProgramming.hmhBackend.join.entity.Member;
import mobileProgramming.hmhBackend.posting.domain.Posting;
import mobileProgramming.hmhBackend.todolist.domain.TodoList;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Place {

    @Id
    private Long id;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member", referencedColumnName = "id", nullable = false)
    private Member member;

    @Column
    private String name;

    @Column
    private String address;

    @OneToMany(mappedBy = "place")
    private List<Posting> postings;

    @OneToMany(mappedBy = "place")
    private List<TodoList> todoLists;

    public void addPosting(Posting posting) {
        postings.add(posting);
    }

    public void delPosting(Posting posting) {
        postings.remove(posting);
    }

    public void addTodo(TodoList todoList) {
        todoLists.add(todoList);
    }

    public void delTodo(TodoList todoList) {
        todoLists.remove(todoList);
    }

}
