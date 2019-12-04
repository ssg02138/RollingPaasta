package com.rolling.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class RollingviewApplication{
	
	
	public static void main(String[] args) {
		
		LocalDB db = new LocalDB();
		System.out.println("메인 어플리케이션을 실행합니다.");
		SpringApplication.run(RollingviewApplication.class, args);
		db.run(); // api 받아서 가로등제어 및 db 수정
		
	}
	
	@GetMapping
	public String HelloWorld() {
		return "Koo Bon Sang";
		
	}
}