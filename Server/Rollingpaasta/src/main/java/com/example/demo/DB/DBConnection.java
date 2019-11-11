package com.example.demo.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.demo.DTO.LightDTO;
import com.example.demo.DTO.LightDTOList;
import com.jcraft.jsch.Session;

public class DBConnection {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://ff8148416cbb8c44:25afd527f49c2d86@10.10.5.15:3306/op_b42be121_4b4d_4cfc_b520_2f0187335fd6";
	//static final String DB_URL = "jdbc:mysql://127.0.0.1:81/op_b42be121_4b4d_4cfc_b520_2f0187335fd6";
	//local test용
	static final String USER = "ff8148416cbb8c44";
	static final String PASS = "25afd527f49c2d86";

	private static Session session = null;

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

	public LightDTOList getDatafromDB() {
		
		LightDTO dto =null;
		LightDTOList dtolist=new LightDTOList();
		ArrayList<LightDTO> tempdtolist = new ArrayList<LightDTO>();
		int size;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =connect();
		try {
			pstmt = conn.prepareStatement("SELECT * FROM testTable LIMIT 5");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto= new LightDTO();
				dto.setMessage(rs.getString(1));
				
				tempdtolist.add(dto);
			}
			size = rs.getRow()+1;
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
