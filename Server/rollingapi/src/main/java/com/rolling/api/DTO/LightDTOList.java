package com.rolling.api.DTO;

import java.util.ArrayList;


public class LightDTOList {
	private boolean success;
	private ArrayList<LightDTO> list;
	private int total_count;
	
	public ArrayList<LightDTO> getList() {
		return list;
	}

	public void setList(ArrayList<LightDTO> list) {
		this.list = list;
	}
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
}
