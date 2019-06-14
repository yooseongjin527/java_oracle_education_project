package kr.co.sist.sec.administrator.basicinfo.course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.sist.sec.administrator.DAO;
import kr.co.sist.sec.dto.DTOCourse;

public class CourseDAO extends DAO {

	public ArrayList<DTOCourse> list() {
		try {
			
			String sql = "SELECT seq, name FROM Course WHERE status = 1 ORDER BY seq";
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<DTOCourse> list = new ArrayList<DTOCourse>();
			
			while (rs.next()) {
				DTOCourse dto = new DTOCourse();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public ArrayList<DTOCourse> list(String word) {
		try {
			
			String sql = "SELECT seq, name FROM Course WHERE lower(name) LIKE '%' || lower(?) || '%' AND status = 1 ORDER BY seq";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOCourse> list = new ArrayList<DTOCourse>();
			
			while (rs.next()) {
				DTOCourse dto = new DTOCourse();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	
	public DTOCourse exist(String name) {
		try {
			
			String sql = "SELECT * FROM Course WHERE name = ? AND status = 1";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOCourse dto = new DTOCourse();
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	public int add(String name) {
		
		String sql = "INSERT INTO Course (seq, name) VALUES (COURSE_SEQ.nextVal, ?)";
		
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			
			return pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public DTOCourse get(String seq) {
		try {
			
			String sql = "select * from Course where seq = ? and status = 1";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOCourse dto = new DTOCourse();
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				
				return dto;
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public int edit(DTOCourse dto) {
		try {
			
			String sql = "UPDATE Course SET name = ? WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}

	public int del(String seq) {
		try {
			
			String sql = "UPDATE Course SET status = 0 WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}
}
