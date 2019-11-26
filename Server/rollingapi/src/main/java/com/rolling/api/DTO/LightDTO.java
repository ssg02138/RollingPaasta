package com.rolling.api.DTO;

public class LightDTO {

	private String traffic;
	private String name;
	private int date;

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String message) {
		this.traffic = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}
	
}
