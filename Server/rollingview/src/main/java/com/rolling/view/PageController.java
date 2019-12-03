package com.rolling.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class PageController {

	@RequestMapping(value="/suwon")
	public String suwon() {
		return "suwon";
	}
	@RequestMapping(value="/log")
	public String log() {
		return "log";
	}
	@RequestMapping(value="/home")
	public String index() {
		return "index";
	}
	@RequestMapping(value="/test")
	public String test() {
		return "test";
	}
}
