package com.rolling.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.util.Scanner;

public class LocalDB extends PageController{
	int k = 1;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // jdbc 드라이버 주소 
	static final String DB_URL = "jdbc:mysql://localhost:3306/rollinglocaldb?useSSL=false"; // DB 접속 주소 //localhost는 접속하려는 데이터베이스 주소를 입력하시면 됩니다. localhost를 사용하면 됩니다. //3306은 데이터베이스에 접속할때 사용하는 포터번호입니다. 설치할때 설정한 포트번호를 사용합니다. //databasename에는 접속하려는 database의 name을 입력해줍니다. 
	static final String USERNAME = "root"; // DB ID 
	static final String PASSWORD = ""; // DB Password
	String date;
	public void run() {
		int jujak = 0;
		while(true) {
			Date cal = new Date();
			SimpleDateFormat yMdH =  new SimpleDateFormat("yyMMddHH");
			SimpleDateFormat mm =  new SimpleDateFormat("mm");
			String Date1 = yMdH.format(cal);
			String Date2 = mm.format(cal);
			String Date = Date1 + Date2;
			
			
			
			String traffic[] = new String[10];
			int error = 0;
			URL url;
			if(Isfirst == 0) {
				date = Date;
			} else {date = Date_1;}
			System.out.println(Date);
			while(error != 1) { // API가 연결될때까지 반복
				try {
					url = new URL("http://rollingapi.paas-ta.org/light/get?date=" + date); // +date로 바꾸기
					try {
						InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
						JSONObject object = (JSONObject)JSONValue.parse(isr);
						JSONArray bodyArray = (JSONArray) object.get("list");
						//System.out.println(object.get("date"));
						
						System.out.println("API 연결완료!!");
						error = 1;
						
						for(int i = 0 ; i < bodyArray.size(); i++) {
							JSONObject data = (JSONObject) bodyArray.get(i);        
							//System.out.println(data.get("traffic").toString());
							traffic[i] = data.get("traffic").toString();
							switch(traffic[i]) { // full, half, zero로 바꿈
							case "1":
								traffic[i] = "full"; // traffic이 1이면 최고밝기
								break;
							case "2":
								traffic[i] = "half"; // traffic이 2이면 중간밝기
								break;
							case "3":
								traffic[i] = "zero"; // traffic이 3이면 밝기없음
								break;
							}
							//System.out.println(data.get("name").toString());
							//System.out.println(data.get("date").toString());	
						}
					} catch (IOException e) {
						System.out.println("Re_connecting_API...");
						//e.printStackTrace();
					}	
				} catch (MalformedURLException e) {
					System.out.println("Re_connecting_API...");
					//e.printStackTrace();
				}
			}
			//for(int i = 0; i < 10; i++) { // 확인용, 나중에 지우기
			//	System.out.println(traffic[i]);
			//}
			if(jujak == 0) {
				for(int i = 0; i < 10; i++) {
					traffic[i] = "full";
				}
			}
			
			// MySql에 사용하는여러 객체를 만들어줍니다.
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			System.out.print("User Table 접속 : "); 
			
			try { 
				Class.forName(JDBC_DRIVER); //Class 클래스의 forName()함수를 이용해서 해당 클래스를 메모리로 로드 하는 것입니다. //URL, ID, password를 입력하여 데이터베이스에 접속합니다. 
				conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); // 접속결과를 출력
				if (conn != null){System.out.println("성공");} 
				else{System.out.println("실패");} 
				} catch (ClassNotFoundException e) { 
					System.out.println("Class Not Found Exection"); 
					e.printStackTrace(); 
				} catch (SQLException e) { 
					System.out.println("SQL Exception : " + e.getMessage()); e.printStackTrace(); }
			//
			try { // select 문
				String qu = "select *from iot";
				Statement st = conn.createStatement();
				rs = st.executeQuery(qu);
				int i = 0;
				while(rs.next()) {
					
					int idx = rs.getInt("idx");
					String ip = rs.getString("ip");
					//ip에 해당하는 iot 제어
					try { //iot 밝기 제어 + iot_state 제어값 변경문
				         // Construct data
				         String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8");
				         data += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("홍길동", "UTF-8");
				     
				         // iot 밝기 제어
				         URL url1 = new URL("http://" + ip + ":8080/" + traffic[i] + "");
				         InputStreamReader isr = new InputStreamReader(url1.openConnection().getInputStream(), "UTF-8");
				         URLConnection conn1 = url1.openConnection();
				         System.out.println(url1);
				         
				         //iot_state의 제어값 변경, switch문을 쓰면 update 선언시 오류가 나 if문으로 작성
				         if(traffic[i] == "full") {
				        	 String update="update iot_state set onoff=150 where idx=" + i; //iot 제어값 변경
				        	 stmt = conn.createStatement();
				        	 stmt.executeUpdate(update);
				         }
				         if(traffic[i] == "half") {
				        	 String update="update iot_state set onoff=75 where idx=" + i; //iot 제어값 변경
				        	 stmt = conn.createStatement();
				        	 stmt.executeUpdate(update);
				         }
				         if(traffic[i] == "zero") {
				        	 String update="update iot_state set onoff=0 where idx=" + i; //iot 제어값 변경
				        	 stmt = conn.createStatement();
				        	 stmt.executeUpdate(update);
				         }
				         // If you invoke the method setDoOutput(true) on the URLConnection, it will always use the POST method.
				         /*conn1.setDoOutput(true);
				         OutputStreamWriter wr = new OutputStreamWriter(conn1.getOutputStream());
				         wr.write(data);
				         wr.flush();*/
				     
				         // Get the response
				         /*BufferedReader rd = new BufferedReader(new InputStreamReader(conn1.getInputStream(),"UTF-8"));
				         String line;
				         while ((line = rd.readLine()) != null) {
				            System.out.println(line);
				         }*/
				         //wr.close();
				         //rd.close();
				         
				     } catch (MalformedURLException e) {
				    	 System.out.println("The URL address is incorrect.");
				     } catch(IOException e) {
				    	 System.out.println("It can't connect to the web page.");
				     }
					
					System.out.format("%s %s", idx, ip);
					System.out.println();
					i++;
					}
				st.close();
				
				
			}catch(Exception e) {
				System.out.println("Got an exception!");
				System.out.println(e.getMessage());
			}
			 Scanner scanner = new Scanner(System.in);
			 System.out.println("Next : ");
			 int next = scanner.nextInt();
			 System.out.println(jujak);
		}
	}
}
