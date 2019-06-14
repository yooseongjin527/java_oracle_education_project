package kr.co.sist.sec.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.sist.sec.dto.DTOLogin;

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
			System.out.println("main.DAO.Constructor : " + e.toString());
		}
	}
	
	public boolean isConnected() {
		try {
			
			return !this.conn.isClosed();
			
		} catch (Exception e) {
			System.out.println("main.DAO.isConnected() : " + e.toString());
			return false;
		}
	}
	
	public void close() {
		try {
			
			this.conn.close();
			
		} catch (Exception e) {
			System.out.println("main.DAO.close() : " + e.toString());
		}
	}

}
