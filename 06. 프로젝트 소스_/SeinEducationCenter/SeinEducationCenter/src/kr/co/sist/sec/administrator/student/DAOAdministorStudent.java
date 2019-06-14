package kr.co.sist.sec.administrator.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.sist.sec.dto.DTOStudent;
import kr.co.sist.sec.dto.DTOvwAllStudent;
import kr.co.sist.sec.dto.DTOvwSelectedStudent;
import kr.co.sist.sec.main.DBUtil;

public class DAOAdministorStudent {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	
	
	// 초기화 (선행 작업)
	// - DB 접속
	public DAOAdministorStudent() {
		
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
	 * [관리자-교육생] Student 테이블 INSERT 실행 메소드
	 * @param dto 입력받은 교육생 정보 (이름, 주민번호뒷자리, 전화번호, 전공여부)
	 * @return 1:성공, 0:실패
	 */
	public int add(DTOStudent dto) {
		

		String sql = "INSERT INTO Student (seq, name, ssn, tel, regdate, major) "
						+ "VALUES (student_seq.NEXTVAL, ?, ?, ?, TO_DATE(SYSDATE, 'yyyy-mm-dd'), ?)";
		
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getSsn());
			pstat.setString(3, dto.getTel());
			pstat.setString(4, dto.getMajor());
			
			return pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

	
	/**
	 * [관리자-교육생] 모든 학생 정보 조회 실행 메소드
	 * @return 교육생 정보가 들어있는 배열
	 */
	public ArrayList<DTOvwAllStudent> AllStudentlist() {
		
		try {
			
			String sql = "SELECT * FROM vwAllStudent";
			
			ResultSet rs = stat.executeQuery(sql);
			
			ArrayList<DTOvwAllStudent> list = new ArrayList<DTOvwAllStudent>();

			while (rs.next()) {

				DTOvwAllStudent dto = new DTOvwAllStudent();
				
				dto.setSeq(rs.getString("교육생번호"));
				dto.setName(rs.getString("이름"));
				dto.setSsn(rs.getString("주민번호뒷자리"));
				dto.setTel(rs.getString("전화번호"));
				dto.setMajor(rs.getString("전공여부"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	
	/**
	 * [관리자-교육생] 선택된 교육생 세부정보 반환 메소드
	 * @param StudentNum 교육생 고유 번호
	 * @return 선택된 교육생의 세부 정보
	 */
	public ArrayList<DTOvwSelectedStudent> SelectedStudentList(String StudentNum) {
		
		try {
			
			String sql = "SELECT * FROM vwSelectedStudent WHERE 교육생번호 = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, StudentNum);
		    
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOvwSelectedStudent> list = new ArrayList<DTOvwSelectedStudent>();

			while (rs.next()) {
				
				DTOvwSelectedStudent dto = new DTOvwSelectedStudent();
				
				dto.setSeq(rs.getString("교육생번호"));
				dto.setName(rs.getString("이름"));
				dto.setSsn(rs.getString("주민번호뒷자리"));
				dto.setTel(rs.getString("전화번호"));
				dto.setRegdate(rs.getString("등록일"));
				dto.setMajor(rs.getString("전공여부"));
				dto.setCourse_status(rs.getString("수강상태"));
				dto.setCourse_name(rs.getString("과정명"));
				dto.setCourse_start(rs.getString("과정시작일"));
				dto.setCourse_end(rs.getString("과정종료일"));
				dto.setClassroom(rs.getString("강의실"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}


	/**
	 * [관리자-교육생] 교육생 번호로 검색하는 메소드
	 * @param input 교육생 번호
	 * @return 입력한 번호와 일치하는 교육생 정보
	 */
	public DTOStudent searchList(String input) {
		
		
		try {
			
			String sql = "SELECT seq, name, ssn, tel, major, TO_CHAR(regdate, 'yyyy-mm-dd') AS regdate FROM Student WHERE seq = ?";
		
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, input);
			
			ResultSet rs = pstat.executeQuery();
			
			rs.next();
			DTOStudent dto = new DTOStudent();
			
			dto.setSeq(rs.getString("seq"));
			dto.setName(rs.getString("name"));
			dto.setMajor(rs.getString("major"));
			dto.setSsn(rs.getString("ssn"));
			dto.setTel(rs.getString("tel"));
			dto.setRegdate(rs.getString("regdate"));
			
			
			return dto;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;		
		
	}


	/**
	 * [관리자-교육생] 교육생 정보 수정 메소드
	 * @param dto 수정용으로 입력받은 정보
	 * @return 1:성공, 0:실패
	 */
	public int edit(DTOStudent dto) {
		
		try {
			
			String sql = "UPDATE Student SET name = ?, major = ?, ssn = ?, tel = ? WHERE seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getMajor());
			pstat.setString(3, dto.getSsn());
			pstat.setString(4, dto.getTel());
			pstat.setString(5, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;	
	}

	
	/**
	 * [관리자-교육생] 교육생 삭제 처리 메소드
	 * @param input 입력받은 교육생 번호
	 * @return
	 */
	public int del(String input) {
		
		try {
			
			String sql = "UPDATE Student SET status = 0 WHERE seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, input);
		
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}

	
	
	/**
	 * [관리자-교육생] 교육생 이름기준 검색 메소드
	 * @param input 검색어
	 * @return 검색결과 배열
	 */
	public ArrayList<DTOStudent> nameSearch(String input) {
		
		try {
			
			String sql = "SELECT seq, name, ssn, tel, major, TO_CHAR(regdate, 'yyyy-mm-dd') AS regdate FROM Student WHERE name LIKE '%' || ? || '%' and status = 1";
		
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, input);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOStudent> list = new ArrayList<DTOStudent>();
			
			while (rs.next()) {
				
				DTOStudent dto = new DTOStudent();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setMajor(rs.getString("major"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				dto.setRegdate(rs.getString("regdate"));
				
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;		
	}

	
}
