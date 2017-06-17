package com.cricketerlk;

import com.cricketerlk.common.Configurations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

//TODO extends SpringBootServletInitializer
@SpringBootApplication
@ComponentScan
public class CricketerlkApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CricketerlkApplication.class);
	}

	public static void main(String[] args) {
		Configurations config = new Configurations();
		SpringApplication.run(CricketerlkApplication.class, args);
	}
}
