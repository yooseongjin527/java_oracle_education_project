package kr.co.sist.sec.administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import kr.co.sist.sec.main.DBUtil;

public class DAO {

	protected Connection conn;
	protected Statement stat;
	protected PreparedStatement pstat;
	
	public DAO() {
		try {
			DBUtil util = new DBUtil();
			this.conn = util.connect();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DAO.Constructor");
		}
	}
	
	public void close() {

		try {

			this.conn.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	
	
	
}
