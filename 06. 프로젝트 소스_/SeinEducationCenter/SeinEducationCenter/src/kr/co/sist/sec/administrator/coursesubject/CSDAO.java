package kr.co.sist.sec.administrator.coursesubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.sist.sec.dto.DTOCourseSubject;
import kr.co.sist.sec.dto.DTOCourseSubjectTextbook;
import kr.co.sist.sec.main.DBUtil;


public class CSDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	
	public CSDAO() {
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

	public ArrayList<DTOCourseSubjectTextbook> slistForC(String seq) {
		try {
			String sql = "SELECT sseq, sname, tbname FROM vwCourseSubjectTextbook WHERE cscourse_seq = ? AND sstatus = 1 ORDER BY sseq";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOCourseSubjectTextbook> list = new ArrayList<DTOCourseSubjectTextbook>();
			
			while (rs.next()) {
				DTOCourseSubjectTextbook dto = new DTOCourseSubjectTextbook();
				
				dto.setSseq(rs.getString("sseq"));
				dto.setSname(rs.getString("sname"));
				dto.setTbname(rs.getString("tbname"));
				
				list.add(dto);
			}
			
			return list;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public int add(DTOCourseSubject dto) {
		try {
			String sql = "INSERT INTO CourseSubject (seq, course_seq, subject_seq) VALUES (coursesubject_seq.nextVal, ?, ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getCourse_seq());
			pstat.setString(2, dto.getSubject_seq());
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

	
	
	public int del(ArrayList<DTOCourseSubject> list) {
		try {
			int result = 1;
			for (int i=0; i<list.size(); i++) {
				String sql = "DELETE FROM CourseSubject WHERE seq = ?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, list.get(i).getSeq());
				result *= pstat.executeUpdate();
			}
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

	public ArrayList<DTOCourseSubject> findseq(DTOCourseSubject dto) {
		try {
			String sql = "SELECT * FROM CourseSubject WHERE course_seq = ? AND subject_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getCourse_seq());
			pstat.setString(2, dto.getSubject_seq());
			ResultSet rs = pstat.executeQuery();
			ArrayList<DTOCourseSubject> list = new ArrayList<DTOCourseSubject>();
			while (rs.next()) {
				dto = new DTOCourseSubject();
				dto.setSeq(rs.getString("seq"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	
	public int alreadyRegistCheck(DTOCourseSubject dto) {
		try {
			String sql = "SELECT * FROM CourseSubject WHERE course_seq = ? AND subject_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getCourse_seq());
			pstat.setString(2, dto.getSubject_seq());
			ResultSet rs = pstat.executeQuery();
			ArrayList<DTOCourseSubject> list = new ArrayList<DTOCourseSubject>();
			while (rs.next()) {
				dto = new DTOCourseSubject();
				dto.setSeq(rs.getString("seq"));
				
				list.add(dto);
			}
			
			return list.isEmpty() ? 0 : 1;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return -1;
	}
}
