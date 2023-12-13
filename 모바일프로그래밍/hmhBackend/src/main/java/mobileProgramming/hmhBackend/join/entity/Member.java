package mobileProgramming.hmhBackend.join.entity;

import lombok.*;
import mobileProgramming.hmhBackend.follow.domain.Follow;
import mobileProgramming.hmhBackend.place.domain.Place;
import mobileProgramming.hmhBackend.posting.domain.Posting;
import mobileProgramming.hmhBackend.todolist.domain.TodoList;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

// 엔티티 클래스 정의
@Getter // 자동으로 getter 메서드 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서의 직접 객체 생성을 막는다.
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 자동으로 생성
@Builder // 빌더 패턴을 제공하여 객체를 생성할 때 명확한 구문을 제공
@Entity // JPA 엔티티클래스임을 나타낸다
public class Member extends BaseTimeEntity{


    @Id
    @Column(unique = true, nullable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 45)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column
    private String birth;

    @Column(nullable = false)
    private String password;

    @Column
    private String image;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void addUserAuthority() {
        this.role = Role.USER;
    }
    // password를 암호화하는 메서드
    // 주어진 'passwordEncoder'를 사용하여 비밀번호를 암호화하고 해당 값을 'password' 필드에 저장.
    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void setImage(String imageFile) {
        this.image = imageFile;
    }

    @OneToMany(mappedBy = "member")
    private List<Follow> follows;

    @OneToMany(mappedBy = "member")
    private List<Place> places;

    @OneToMany(mappedBy = "member")
    private List<Posting> postings;

    @OneToMany(mappedBy = "member")
    private List<TodoList> todoLists;

    public void addFollow(Follow follow) { follows.add(follow); }
    public void delFollow(Follow follow) { follows.remove(follow); }

    public void addPlace(Place place) { places.add(place); }
    public void delPlace(Place place) { places.remove(place); }

    public void addPosting(Posting posting) { postings.add(posting); }
    public void delPosting(Posting posting) { postings.remove(posting); }

    public void addTodoList(TodoList todoList) { todoLists.add(todoList); }
    public void delTodoList(TodoList todoList) { todoLists.remove(todoList); }
}
