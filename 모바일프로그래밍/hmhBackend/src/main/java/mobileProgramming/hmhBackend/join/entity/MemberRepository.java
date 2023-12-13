package mobileProgramming.hmhBackend.join.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JPA를 사용하여 데이터베이스와 상호작용하는 데 사용되는 Repository 인터페이스
// JPA에서 제공하는 기본적인 데이터베이스 CRUD 작업을 지원하는 인터페이스 상속 - JpaRepository<Member, Long> : JpaRepository<엔티티, 엔티티 PK 타입>
public interface MemberRepository extends JpaRepository<Member, Long> {

    // findByEmail은 JPA에서 지원하는 쿼리 메소드이며 이메일이 일치하는 Member를 찾아준다
    Optional<Member> findByEmail(String email);
    // null 대신 Optional.empty() 반환
}
