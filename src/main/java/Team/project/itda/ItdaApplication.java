package Team.project.itda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
public class ItdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItdaApplication.class, args);
	}
// 깃 이그노어 테스트
}
