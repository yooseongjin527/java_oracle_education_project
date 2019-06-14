package kr.co.sist.sec.administrator.basicinfo.textbook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.sist.sec.administrator.DAO;
import kr.co.sist.sec.dto.DTOCourse;
import kr.co.sist.sec.dto.DTOTextbook;

public class TextbookDAO extends DAO {

	public ArrayList<DTOTextbook> list() {
		try {
			
			String sql = "SELECT seq, name, author, publisher FROM Textbook WHERE status = 1 ORDER BY seq";
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<DTOTextbook> list = new ArrayList<DTOTextbook>();
			
			while (rs.next()) {
				DTOTextbook dto = new DTOTextbook();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public ArrayList<DTOTextbook> list(String word) {
		try {
			
			String sql = "SELECT seq, name, author, publisher FROM Textbook WHERE lower(name) LIKE '%' || lower(?) || '%' AND status = 1 ORDER BY seq";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOTextbook> list = new ArrayList<DTOTextbook>();
			
			while (rs.next()) {
				DTOTextbook dto = new DTOTextbook();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public DTOTextbook exist(String name) {
		try {
			
			String sql = "SELECT * FROM Textbook WHERE name = ? AND status = 1";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOTextbook dto = new DTOTextbook();
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public int addAgain(DTOTextbook dto) {
		String sql = "UPDATE Textbook SET status = 1 WHERE seq = ? AND status = 0";
		
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int add(DTOTextbook dto) {
		String sql = "INSERT INTO Textbook (seq, name, publisher, author) VALUES (textbook_seq.nextVal, ?, ?, ?)";
		
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getPublisher());
			pstat.setString(3, dto.getAuthor());
			
			return pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int add(String name, String author, String publisher) {
		
		String sql = "INSERT INTO Textbook (seq, name, publisher, author) VALUES (textbook_seq.nextval, ?, ?, ?)";
		
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setString(2, author);
			pstat.setString(3, publisher);
			
			return pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public DTOTextbook get(String seq) {
		try {
			
			String sql = "select * from Textbook where seq = ? and status = 1";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOTextbook dto = new DTOTextbook();
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				
				return dto;
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public int edit(DTOTextbook dto) {
		try {
			
			String sql = "UPDATE Textbook SET name = ?, author = ?, publisher = ? WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getAuthor());
			pstat.setString(3, dto.getPublisher());
			pstat.setString(4, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}

	public int del(String seq) {
		try {
			
			String sql = "UPDATE Textbook SET status = 0 WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}

}
