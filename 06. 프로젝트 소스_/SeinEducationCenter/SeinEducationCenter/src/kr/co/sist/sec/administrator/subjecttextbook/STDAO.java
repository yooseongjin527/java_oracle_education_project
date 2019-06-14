package kr.co.sist.sec.administrator.subjecttextbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.sist.sec.dto.DTOCourseSubjectTextbook;
import kr.co.sist.sec.dto.DTOSubject;
import kr.co.sist.sec.main.DBUtil;


public class STDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	
	public STDAO() {
		try {
			
			DBUtil util = new DBUtil();
			this.conn = util.connect();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void isConnedted() {
		try {
			System.out.println(!conn.isClosed() == true ? "연결" : "비연결");
		} catch (Exception e) {
			System.out.println("DAO.isConnected() : " + e.toString());
		}
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO.close()");
		}
	}
//===========================================================
	/**
	 * 해당 과정 교재 목록 가져오기
	 * @param sseq
	 * @return
	 */
	public ArrayList<DTOCourseSubjectTextbook> tlistForS(String sseq) {
		try {
			String sql = "SELECT * FROM vwSubjectTextbook WHERE sseq = ? AND tstatus = 1 ORDER BY tseq";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sseq);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOCourseSubjectTextbook> list = new ArrayList<DTOCourseSubjectTextbook>();
			
			if (rs.next()) {
				DTOCourseSubjectTextbook dto = new DTOCourseSubjectTextbook();
				
				dto.setSseq(rs.getString("sseq"));
				dto.setSname(rs.getString("sname"));
				dto.setTbseq(rs.getString("tseq"));
				dto.setTbname(rs.getString("tname"));
				dto.setTbauthor(rs.getString("tauthor").isEmpty() ? "" : rs.getString("tauthor"));
				dto.setTbpublisher(rs.getString("tpublisher").isEmpty() ? "" : rs.getString("tpublisher"));
				
				
				list.add(dto);
			}
			
			return list;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public int alreadyRegistCheck(DTOSubject dto) {
		try {
			String sql = "SELECT * FROM Subject WHERE seq = ? AND text_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSeq());
			pstat.setString(2, dto.getText_seq());
			ResultSet rs = pstat.executeQuery();
			ArrayList<DTOSubject> list = new ArrayList<DTOSubject>();
			while (rs.next()) {
				dto = new DTOSubject();
				dto.setSeq(rs.getString("seq"));
				
				list.add(dto);
			}
			
			return list.isEmpty() ? 0 : 1;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return -1;
	}

	public int add(DTOSubject dto) {
		try {
			String sql = "UPDATE Subject SET text_seq = ? WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getText_seq());
			pstat.setString(2, dto.getSeq());
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

	public int del(DTOSubject dto) {
		try {
			String sql = "UPDATE Subject SET text_seq = NULL WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSeq());
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

}
