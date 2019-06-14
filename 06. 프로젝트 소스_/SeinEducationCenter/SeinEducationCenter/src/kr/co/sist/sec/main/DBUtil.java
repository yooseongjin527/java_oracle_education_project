package kr.co.sist.sec.main;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
//	public static final String SERVER = "localhost";
//	public static final String ID = "hong";
//	public static final String PW = "java1234";

	public Connection connect() {
		Connection conn = null;
		
		String url = "jdbc:oracle:thin:@211.63.89.46:1521:xe"; // 211.63.89.46 //localhost
		String id = "Projectsh"; // Projectsh //mn(집) //hong(센터)
		String pw = "java1234";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			return conn;
			
		} catch (Exception e) {
			System.out.println("DBUtil.connect() : " + e.toString());
		}
		return null;
	}
	
	public Connection connect(String server, String id, String pw) {
		Connection conn = null;
		
		String url = "jdbc:oracle:thin:@" + server + ":1521:xe";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			return conn;
		} catch (Exception e) {
			System.out.println("DBUtil.connect()_overloading : " + e.toString());
		}
		return null;
	}

}
