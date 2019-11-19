package com.rolling.api.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rolling.api.DTO.LightDTO;
import com.rolling.api.DTO.LightDTOList;

public class DBConnection {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://e72d7123dc3f7125:25afd527f49c2d86@10.10.5.15:3306/op_b42be121_4b4d_4cfc_b520_2f0187335fd6";
	//static final String DB_URL = "jdbc:mysql://127.0.0.1:81/op_b42be121_4b4d_4cfc_b520_2f0187335fd6";
	//local test용
	static final String USER = "e72d7123dc3f7125";
	static final String PASS = "25afd527f49c2d86";


	public DBConnection() {

	}


	public Connection connect() {
		Connection conn = null;

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Load Memory.....");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("connect to DB.....");
			if (conn != null) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public LightDTOList getDatafromDB(String date) {
		
		LightDTO dto =null;
		LightDTOList dtolist=new LightDTOList();
		ArrayList<LightDTO> tempdtolist = new ArrayList<LightDTO>();
		int size=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =connect();
		try {
			pstmt = conn.prepareStatement("select rate, sector.name, time from traffic left join sector on (traffic.idx=sector.idx) where time =? limit 5");
			pstmt.setString(1, date);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto= new LightDTO();
				dto.setTraffic(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setDate(rs.getString(3));

				tempdtolist.add(dto);
				size++;
			}
			dtolist.setList(tempdtolist);
			dtolist.setSuccess(true);
			dtolist.setTotal_count(size);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dtolist;
	}
}
