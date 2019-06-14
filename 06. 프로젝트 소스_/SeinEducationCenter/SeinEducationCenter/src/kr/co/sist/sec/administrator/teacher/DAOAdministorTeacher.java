package kr.co.sist.sec.administrator.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.sist.sec.dto.DTOTeacher;
import kr.co.sist.sec.dto.DTOvwAllTeacher;
import kr.co.sist.sec.dto.DTOvwSelectedTeacher;
import kr.co.sist.sec.main.DBUtil;


public class DAOAdministorTeacher {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	
	
	// 초기화 (선행 작업)
	// - DB 접속
	public DAOAdministorTeacher() {
		
		try {

			DBUtil util = new DBUtil();
			this.conn = util.connect();
			this.stat = conn.createStatement();
		
		} catch (SQLException e) {
			System.out.println("AdministorStudentDAO.Constructor - " + e.toString());
		}
		
	}
	
	
	/**
	 * DB와의 연결 확인 메소드
	 * @return true : 연결, false : 비연결
	 */
	public boolean isConnected() {
		
		try {
			return !this.conn.isClosed();
		} catch (SQLException e) {
			System.out.println("AdministorStudentDAO.isConnected() - " + e.toString());
		}
		
		return false;
		
	}
	
	
	/**
	 * DB 접속해제 메소드
	 */
	public void close() {
		
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("오류 위치 : AdministorStudentDAO.clse() - " + e.toString());
		}
		
	}

		
	
	/**
	 * [관리자-교사] Teacher 테이블 INSERT 실행 메소드
	 * @param dto
	 * @return 1:성공, 0:실패
	 */
	public int add(DTOTeacher dto) {

		
		String sql = "INSERT INTO Teacher (seq, name, ssn, tel) "
						+ "VALUES (teacher_seq.NEXTVAL, ?, ?, ?)";
		
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getSsn());
			pstat.setString(3, dto.getTel());
			
			return pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			return 0;
	}


	public DTOTeacher searchList(String input) {
		
		try {
			
			String sql = "SELECT seq, name, ssn, tel FROM Teacher WHERE seq = ?";
		
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, input);
			
			ResultSet rs = pstat.executeQuery();
			
			rs.next();
			DTOTeacher dto = new DTOTeacher();
			
			dto.setSeq(rs.getString("seq"));
			dto.setName(rs.getString("name"));
			dto.setSsn(rs.getString("ssn"));
			dto.setTel(rs.getString("tel"));			
			
			return dto;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;		
	}


	/**
	 * [관리자-교사] 교사 정보 수정 메소드
	 * @param dto 수정용으로 입력받은 정보
	 * @return 1:성공, 0:실패
	 */
	public int edit(DTOTeacher dto) {
		
		try {
			
			String sql = "UPDATE Student SET name = ?, ssn = ?, tel = ? WHERE seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getSsn());
			pstat.setString(3, dto.getTel());
			pstat.setString(4, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;	
	}


	
	/**
	 * [관리자-교사] 교사 삭제 처리 메소드
	 * @param input 입력받은 교사 번호
	 * @return
	 */
	public int del(String input) {

		try {
			
			String sql = "UPDATE Teacher SET status = 0 WHERE seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, input);
		
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}


	public ArrayList<DTOvwAllTeacher> AllTeacherlist() {
		
		try {
			
			String sql = "SELECT * FROM vwAllTeacher";
			
			ResultSet rs = stat.executeQuery(sql);
			
			ArrayList<DTOvwAllTeacher> list = new ArrayList<DTOvwAllTeacher>();

			while (rs.next()) {
				
	
				DTOvwAllTeacher dto = new DTOvwAllTeacher();
				
				dto.setSeq(rs.getString("교사번호"));
				dto.setName(rs.getString("교사명"));
				dto.setTel(rs.getString("전화번호"));
				dto.setSsn(rs.getString("주민번호뒷자리"));
				dto.setStatus(rs.getString("상태"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}


	public ArrayList<DTOvwSelectedTeacher> SelectedTeacherList(String teacherNum) {
		
		try {
			
			String sql = "SELECT * FROM vwSelectedTeacher WHERE 교사번호 = ? order by 과목시작일";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, teacherNum);
		    
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOvwSelectedTeacher> list = new ArrayList<DTOvwSelectedTeacher>();

			while (rs.next()) {
				
				DTOvwSelectedTeacher dto = new DTOvwSelectedTeacher();
				
				dto.setSeq(rs.getString("교사번호"));
				dto.setName(rs.getString("교사이름"));
				dto.setAllocated_course(rs.getString("배정된과정명"));
				dto.setAllocated_start(rs.getString("과정시작일"));
				dto.setAllocated_end(rs.getString("과정종료일"));
				dto.setClassroom(rs.getString("강의실명"));
				dto.setSubject_name(rs.getString("배정개설과목명"));
				dto.setSubject_start(rs.getString("과목시작일"));
				dto.setSubject_end(rs.getString("과목종료일"));
				dto.setTextbook(rs.getString("교재명"));

				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}


	public ArrayList<DTOTeacher> nameSearch(String input) {
		
		try {
			
			String sql = "SELECT * FROM Teacher WHERE name LIKE '%' || ? || '%'";
		
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, input);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOTeacher> list = new ArrayList<DTOTeacher>();
			
			while (rs.next()) {
				
				DTOTeacher dto = new DTOTeacher();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));				
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
