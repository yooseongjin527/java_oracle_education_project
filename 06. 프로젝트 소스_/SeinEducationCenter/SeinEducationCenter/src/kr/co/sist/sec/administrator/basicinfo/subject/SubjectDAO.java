package kr.co.sist.sec.administrator.basicinfo.subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.sist.sec.administrator.DAO;
import kr.co.sist.sec.dto.DTOSubject;
import kr.co.sist.sec.dto.DTOSubjectTextbook;

public class SubjectDAO extends DAO {

	public ArrayList<DTOSubjectTextbook> list() {
		try {
			
			String sql = "SELECT sseq, sname, tname FROM vwSubjectTextbook WHERE sstatus = 1 ORDER BY sseq";
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<DTOSubjectTextbook> list = new ArrayList<DTOSubjectTextbook>();
			
			while (rs.next()) {
				DTOSubjectTextbook dto = new DTOSubjectTextbook();
				
				dto.setSseq(rs.getString("sseq"));
				dto.setSname(rs.getString("sname"));
				dto.setTname(rs.getString("tname"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public ArrayList<DTOSubjectTextbook> list(String word) {
		try {
			
			String sql = "SELECT sseq, sname, tname FROM vwSubjectTextbook WHERE lower(sname) LIKE '%' || lower(?) || '%' AND sstatus = 1 ORDER BY sseq";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOSubjectTextbook> list = new ArrayList<DTOSubjectTextbook>();
			
			while (rs.next()) {
				DTOSubjectTextbook dto = new DTOSubjectTextbook();
				
				dto.setSseq(rs.getString("sseq"));
				dto.setSname(rs.getString("sname"));
				dto.setTname(rs.getString("tname"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public ArrayList<DTOSubjectTextbook> exist(String name) {
		try {
			
			String sql = "SELECT * FROM vwSubjectTextbook WHERE sname = ? AND sstatus = 1";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOSubjectTextbook> list = new ArrayList<DTOSubjectTextbook>();
			
			while (rs.next()) {
				
				DTOSubjectTextbook dto = new DTOSubjectTextbook();
				
				dto.setSseq(rs.getString("sseq"));
				dto.setSname(rs.getString("sname"));
				dto.setTseq(rs.getString("tseq"));
				dto.setTname(rs.getString("tname"));
				dto.setTauthor(rs.getString("tauthor"));
				dto.setTpublisher(rs.getString("tpublisher"));
				
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public int addAgain(DTOSubjectTextbook dto) {
		String sql = "UPDATE Subject SET status = 1 WHERE seq = ? AND status = 0";
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSseq());
			
			return pstat.executeUpdate();//1, 0
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int add(DTOSubjectTextbook dto) {
		
		try {
			if (!(dto.getTseq().equals(""))) {
				String sql = "INSERT INTO Subject (seq, name, text_seq) VALUES (subject_seq.nextVal, ?, ?)";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, dto.getSname());
				pstat.setString(2, dto.getTseq());
				
				return pstat.executeUpdate();
				
			} else {
				String sql = "INSERT INTO Subject (seq, name) VALUES (subject_seq.nextVal, ?)";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, dto.getSname());
				
				return pstat.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public DTOSubject get(String seq) {
		try {
			
			String sql = "select * from Subject where seq = ? and status = 1";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOSubject dto = new DTOSubject();
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setText_seq(rs.getString("text_seq"));
				
				return dto;
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public ArrayList<DTOSubjectTextbook> canRegistList(String seq) {
		try {
			
			String sql = "SELECT tseq, tname, tauthor, tpublisher FROM vwSubjectTextbook WHERE sseq = ? AND tstatus = 1";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOSubjectTextbook> list = new ArrayList<DTOSubjectTextbook>();
			
			while (rs.next()) {
				DTOSubjectTextbook dto = new DTOSubjectTextbook();
				
				dto.setTseq(rs.getString("tseq"));
				dto.setTname(rs.getString("tname"));
				dto.setTauthor(rs.getString("tauthor"));
				dto.setTpublisher(rs.getString("tpublisher"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public int edit(DTOSubject dto) {
		try {
			
			String sql = "UPDATE Subject SET name = ?, text_seq = ? WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getText_seq());
			pstat.setString(3, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}

	public int del(String seq) {
		try {
			
			String sql = "UPDATE Subject SET status = 0 WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}

	

}
