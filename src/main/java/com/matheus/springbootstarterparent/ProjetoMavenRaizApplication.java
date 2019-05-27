package com.matheus.springbootstarterparent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@SpringBootApplication
@EnableHypermediaSupport(type = { HypermediaType.HAL })
public class ProjetoMavenRaizApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoMavenRaizApplication.class, args);
	}
	

}
