package hello.hellospring;
//hello의 hello-spring 이 패키지를 포함해서 이 "하위"들은 자동으로
//spring이 다 뒤져서 스프링 빈으로 등록

//이 하위 패키지가 이거랑 동일하거나 하위 패키지가 아닌 애들은 springbin으로 컴포넌트 스캔 안함


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
