package com.rolling.api.RestControl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rolling.api.DTO.LightDTOList;
import com.rolling.api.DB.DBConnection;

@RestController
public class LightController {
	private DBConnection db;

	public LightController() {
		db = new DBConnection();
	}

	@GetMapping("/light/get")
	public LightDTOList get(@RequestParam("date") String param) {
		LightDTOList result = null;
		if (param != null) {
			result = db.getDatafromDB(param);
		}
		if (result == null) {
			result = new LightDTOList();
			result.setList(null);
			result.setSuccess(false);
			result.setTotal_count(0);
		}
		return result;

	}

}
