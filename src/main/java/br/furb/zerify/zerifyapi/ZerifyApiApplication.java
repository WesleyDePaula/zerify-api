package br.furb.zerify.zerifyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ZerifyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZerifyApiApplication.class, args);
	}

}
