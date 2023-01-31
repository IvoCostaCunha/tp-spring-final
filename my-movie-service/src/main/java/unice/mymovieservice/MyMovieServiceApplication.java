package unice.mymovieservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MyMovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMovieServiceApplication.class, args);
	}

}