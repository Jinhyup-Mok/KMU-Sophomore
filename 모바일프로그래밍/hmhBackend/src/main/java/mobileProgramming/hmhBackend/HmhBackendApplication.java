package mobileProgramming.hmhBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 엔티티의 생성일 및 수정일과 같은 감사 필드를 자동으로 설정하고 유지하는 기능 활성화
@SpringBootApplication
public class HmhBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmhBackendApplication.class, args);
	}

}
