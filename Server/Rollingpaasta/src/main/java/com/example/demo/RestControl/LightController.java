package com.example.demo.RestControl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DB.DBConnection;
import com.example.demo.DTO.LightDTOList;

@RestController
public class LightController {
	private DBConnection db;
	public LightController(){
		db= new DBConnection();
	}
	@GetMapping("/light/get")
	public LightDTOList get(@RequestParam("param") String param) {
		
		LightDTOList result=db.getDatafromDB();
		if(result==null) {
			result=new LightDTOList();
			result.setList(null);
			result.setSuccess(false);
			result.setTotal_count(0);
		}
		return result;
		

	}

}
