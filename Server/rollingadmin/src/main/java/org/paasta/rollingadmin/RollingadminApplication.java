package org.paasta.rollingadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer

public class RollingadminApplication {

	public static void main(String[] args) {
		SpringApplication.run(RollingadminApplication.class, args);
	}

}
