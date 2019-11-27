package com.rolling.getRest;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import com.rolling.api.DB.DBConnection;

public class GetRest {
	DBConnection DB;
	static int date = 0000;
	static int round = 1;
	private static String key = "1573438503600";
    static String ReqType = "2";
	static String MinX = "126.9236591";
	static String MaxX = "127.0164932";
	static String MinY = "37.1657027";
	static String MaxY = "37.2362136";
	static String[] Road = {"2010100400","2330022605","2230077200","2330026202","2230079100","2330022502","2330022505","2330026102","2333471800","2230079800"};
	
	public GetRest(){
		DB= new DBConnection();
	}
	public void getRest() {
		try{
			System.out.println("aa");
			
			String queryParams = "key="+key+"&ReqType="+ReqType+"&MinX="+MinX+"&MaxX="+MaxX+"&MinY="+MinY+"&MaxY="+MaxY;
	        String url = "http://openapi.its.go.kr/api/NTrafficInfo?"+queryParams;
	        
	        XPath xpath = XPathFactory.newInstance().newXPath();
	        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
 
			doc.getDocumentElement().normalize();
			
			for(int i=0; i<10; i++){
				// xml to String
				//System.out.println("##########################");
				String str = "//data/roadsectionid[text()="+Road[i]+"]";
				XPathExpression expr = xpath.compile(str);
				String roadsectionid = (String) expr.evaluate(doc, XPathConstants.STRING);
				//System.out.println("roadsectionid..............."+roadsectionid);
				
				str = "//data/roadsectionid[text()="+Road[i]+"]/following-sibling::avgspeed";
				expr = xpath.compile(str);
				String avgspeed = (String) expr.evaluate(doc, XPathConstants.STRING);
				//System.out.println("avgspeed................"+avgspeed);
				
				str = "//data/roadsectionid[text()="+Road[i]+"]/following-sibling::generatedate";
				expr = xpath.compile(str);
				String generatedate = (String) expr.evaluate(doc, XPathConstants.STRING);
				generatedate = generatedate.substring(0, 12);
				//System.out.println("generatedate................"+generatedate);	
				
				// Preprocessing
				try {
					String rate;
					int idx = i;
					String temp=generatedate.substring(2);
					int time = Integer.parseInt(temp);
					if(Integer.parseInt(avgspeed) >= 80) {
						rate = "3";
					} else if(Integer.parseInt(avgspeed) < 80 || Integer.parseInt(avgspeed) >= 50) {
						rate = "2";
					} else {
						rate = "1";
					}
					DB.insert(idx, time, rate);
				} catch (NumberFormatException e) {
					System.out.println(e);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
