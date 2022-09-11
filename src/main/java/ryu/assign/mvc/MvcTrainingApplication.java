package ryu.assign.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MvcTrainingApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MvcTrainingApplication.class, args);
	}

}
