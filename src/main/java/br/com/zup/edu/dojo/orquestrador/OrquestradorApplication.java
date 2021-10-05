package br.com.zup.edu.dojo.orquestrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration
public class OrquestradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrquestradorApplication.class, args);
	}

}
