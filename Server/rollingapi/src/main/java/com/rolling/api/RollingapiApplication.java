package com.rolling.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rolling.getRest.GetRest;
import com.rolling.getRest.ThreadGetRest;

@SpringBootApplication
public class RollingapiApplication {
	static GetRest getrest;
	static ThreadGetRest thread;
	
	public static void main(String[] args) {
		SpringApplication.run(RollingapiApplication.class, args);
		 getrest = new GetRest();
		 thread = new ThreadGetRest(getrest);
		 thread.start();
		
	}

}
