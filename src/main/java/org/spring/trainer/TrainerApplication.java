package org.spring.trainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.spring.trainer.mapper")
public class TrainerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TrainerApplication.class, args);
	}
}
