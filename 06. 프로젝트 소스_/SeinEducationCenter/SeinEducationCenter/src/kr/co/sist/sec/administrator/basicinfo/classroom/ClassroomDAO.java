package kr.co.sist.sec.administrator.basicinfo.classroom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.sist.sec.administrator.DAO;
import kr.co.sist.sec.dto.DTOClassroom;

public class ClassroomDAO extends DAO {

	public ArrayList<DTOClassroom> list() {
		try {
			
			String sql = "SELECT seq, name, capacity FROM Classroom WHERE status = 1 ORDER BY seq";
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<DTOClassroom> list = new ArrayList<DTOClassroom>();
			
			while (rs.next()) {
				DTOClassroom dto = new DTOClassroom();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setCapacity(rs.getString("capacity"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public ArrayList<DTOClassroom> list(String word) {
		try {
			
			String sql = "SELECT seq, name, capacity FROM Classroom WHERE lower(name) LIKE '%' || lower(?) || '%' AND status = 1 ORDER BY seq";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOClassroom> list = new ArrayList<DTOClassroom>();
			
			while (rs.next()) {
				DTOClassroom dto = new DTOClassroom();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setCapacity(rs.getString("capacity"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public DTOClassroom exist(String name, String capacity) {
		try {
			
			String sql = "SELECT * FROM Classroom WHERE name = ? AND capacity = ? AND status = 1";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setString(2, capacity);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOClassroom dto = new DTOClassroom();
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setCapacity(rs.getString("capacity"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("여기" + e.toString()); // **
		}
		return null;
	}

	public int add(DTOClassroom dto) {
		
		String sql = "INSERT INTO Classroom (seq, name, capacity) VALUES (classroom_seq.nextVal, ?, ?)";
		
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getCapacity());
			
			return pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int add(String name, String capacity) {

		String sql = "INSERT INTO Classroom (seq, name, capacity) VALUES (classroom_seq.nextVal, ?, ?)";
		
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setString(2, capacity);
			
			return pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int addAgain(DTOClassroom dto) {
		String sql = "UPDATE Classroom SET status = 1 WHERE seq = ? AND status = 0";
		
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public DTOClassroom get(String seq) {
		try {
			
			String sql = "select * from Classroom where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOClassroom dto = new DTOClassroom();
			
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

	public int del(String seq) {
		try {
			
			String sql = "UPDATE Classroom SET status = 0 WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}

	

	

}
