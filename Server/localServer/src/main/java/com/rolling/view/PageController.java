package com.rolling.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController{
	static Date cal_1 = new Date();
	static SimpleDateFormat yMdH_1 =  new SimpleDateFormat("yyMMddHH");
	static SimpleDateFormat mm_1 =  new SimpleDateFormat("mm");
	static String Date1_1 = yMdH_1.format(cal_1);
	static String Date2_1 = mm_1.format(cal_1);
	static String Date_1;
	int Isfirst = 0;
	
	@RequestMapping(value="/")
	public String newfile() {
		return "NewFile";
	}
	@RequestMapping(value="/map")
	public String suwon() {
		return "suwon";
	}
	@RequestMapping(value="/home")
	public String index() {
		return "index";
	}
	/*
	@GetMapping("/previous")
	public String left() {
		Isfirst++;
		System.out.println("left");
		SimpleDateFormat min =  new SimpleDateFormat("mm");
		String min_string = min.format(cal_1);
		int min_int = Integer.parseInt(min_string);
		min_int -= 5;
		if(min_int < 0) {
			min_int += 60;
		}
		min_string = Integer.toString(min_int);
		Date_1 = Date1_1 + min_string;
		System.out.println("Date : " + Date_1);
		return "tlqkf";
	}
	
	@GetMapping("/next")
	public String right() {
		Isfirst = 1;
		System.out.println("right");
		SimpleDateFormat min =  new SimpleDateFormat("mm");
		String min_string = min.format(cal_1);
		int min_int = Integer.parseInt(min_string);
		min_int += 5;
		if(min_int < 0) {
			min_int += 60;
		}
		min_string = Integer.toString(min_int);
		Date_1 = Date1_1 + min_string;
		System.out.println("Date : " + Date_1);
		return "next";
	}
	 */
}
