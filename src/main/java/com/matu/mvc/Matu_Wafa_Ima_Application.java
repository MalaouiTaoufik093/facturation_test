package com.matu.mvc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class Matu_Wafa_Ima_Application extends  SpringBootServletInitializer {

	
	public static void main(String[] args) {
		SpringApplication.run(Matu_Wafa_Ima_Application.class, args);
	}


	@Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(Matu_Wafa_Ima_Application.class);
	   }

	  	
	
}
