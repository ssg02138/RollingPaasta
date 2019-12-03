package com.rolling.getRest;

public class ThreadGetRest extends Thread {

	GetRest getrest;
	public ThreadGetRest(GetRest getrest) {
		this.getrest=getrest;
	}
	public void run() {
		while(true) {
			try {
				getrest.getRest();
				Thread.sleep(300000);//5분
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
