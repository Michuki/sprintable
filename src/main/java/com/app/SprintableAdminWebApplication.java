package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = { SprintableAdminWebApplication.class, Jsr310JpaConverters.class })
public class SprintableAdminWebApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SprintableAdminWebApplication.class, args);
	}
}
