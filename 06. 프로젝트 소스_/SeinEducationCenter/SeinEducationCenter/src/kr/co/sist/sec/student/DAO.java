package kr.co.sist.sec.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import kr.co.sist.sec.dto.DTOAnswerInformation;
import kr.co.sist.sec.dto.DTOBoard;
import kr.co.sist.sec.dto.DTOCompany;
import kr.co.sist.sec.dto.DTOLogin;
import kr.co.sist.sec.dto.DTONoticeApplication;
import kr.co.sist.sec.dto.DTOQnA;
import kr.co.sist.sec.dto.DTOStudent;
import kr.co.sist.sec.dto.DTOStudentBoard;
import kr.co.sist.sec.dto.DTOStudentCheckAttendance;
import kr.co.sist.sec.dto.DTOStudentTestCheck;
import kr.co.sist.sec.dto.DTOTeacherEvaluation;
import kr.co.sist.sec.dto.DTOViewCompany;
import kr.co.sist.sec.main.DBUtil;

public class DAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	
	public DAO() {
		try {
			
			DBUtil util = new DBUtil();
			this.conn = util.connect();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("student.DAO.Constructor : " + e.toString());
		}
	}
	
	public boolean isConnected() {
		try {
			
			return !this.conn.isClosed();
			
		} catch (Exception e) {
			System.out.println("student.DAO.isConnected() : " + e.toString());
			return false;
		}
	}
	
	public void close() {
		try {
			
			this.conn.close();
			
		} catch (Exception e) {
			System.out.println("student.DAO.close() : " + e.toString());
		}
	}

	public DTOLogin get(String id) {
		
		try {
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i=0; i<10; i++) {
				int indexOf = id.indexOf(i+"");
				
				if (indexOf != -1) {
					list.add(indexOf);
				}
			}
			
			if (!list.isEmpty()) {
				int min = Collections.min(list);
				
				int l = id.length();
				String seq = id.substring(l-3, l);
				String sql = "select * from Student where seq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				ResultSet rs = pstat.executeQuery();
				
				DTOLogin dto = new DTOLogin();
				
				if (rs.next()) {
					
					dto.setId(rs.getString("name") + rs.getString("seq"));
					dto.setPw(rs.getString("ssn"));
					
					return dto;
					
				}
			}
			
		} catch (Exception e) {
			System.out.println("student.DAO.get() : " + e.toString());
		}
		
		return null;
	}

	public DTOStudent getSeqFromNameAndTel(DTOStudent dto) {
		try {
			String sql = "select * from Student where name = ? and tel = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getTel());
			
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				dto.setSeq(rs.getString("seq"));
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public DTOStudent getPwFromId(DTOStudent dto) {
		try {
			String sql = "select * from Student where name = ? and seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getSeq());
			
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				dto.setSsn(rs.getString("ssn"));
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	//========================성적조회========================\\

	public ArrayList<DTOStudentTestCheck> score() {

		try {

			String sql = "select * from vwStudentTestCheck where 교육생이름 = '윤빈정'";

			ResultSet rs = stat.executeQuery(sql);


			ArrayList<DTOStudentTestCheck> list = new ArrayList<DTOStudentTestCheck>();
		
			while (rs.next()) {
				
				DTOStudentTestCheck dto = new DTOStudentTestCheck();
				
				dto.setName(rs.getString("교육생이름"));	// 컬럼값 -> DTO 멤버 변수
				dto.setSubject_name(rs.getString("과목명"));
				dto.setStart_date(rs.getString("과목시작일"));
				dto.setEnd_date(rs.getString("과목종료일"));
				dto.setTextbook_name(rs.getString("교재명"));
				dto.setTeacher_name(rs.getString("교사명"));
				dto.setScore_attendance(rs.getString("출결점수"));
				dto.setScore_written_test(rs.getString("필기점수"));
				dto.setScore_performance_test(rs.getString("실기점수"));
				dto.setOpenSubject_writtentest_date(rs.getString("필기시험날짜"));
				dto.setOpenSubject_performancetest_date(rs.getString("실기시험날짜"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	//========================성적조회========================\\
	
	//========================출석조회========================\\
	public ArrayList<DTOStudentCheckAttendance> geuntae(String beginDate, String endDate) {

		try {
			
			//TODO 로그인한 회원 번호 추가해야함
			String sql = "select * from vwStudentCheckAttendance where \"교육생번호\" = " + 10 + " and 날짜 between '"+beginDate+ "' and '" +endDate+"' order by \"날짜\"";
		
//			pstat = conn.prepareStatement(sql);
//			
//			pstat.setString(1, "10");


			ResultSet rs = stat.executeQuery(sql);

			
			ArrayList<DTOStudentCheckAttendance> list = new ArrayList<DTOStudentCheckAttendance>();
			
			while (rs.next()) {
			
				DTOStudentCheckAttendance dto = new DTOStudentCheckAttendance();
				
				dto.setCourse_seq(rs.getString("과정번호"));
				dto.setStudent_seq(rs.getString("교육생번호"));
				dto.setName(rs.getString("교육생이름"));	
				dto.setStatus(rs.getString("상태"));
				dto.setGeuntae_date(rs.getString("날짜"));
				dto.setCheck_in(rs.getString("입실시간"));
				dto.setCheck_out(rs.getString("퇴실시간"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	//========================출석조회========================\\
	
	//========================게시판========================\\
	public ArrayList<DTOStudentBoard> boardlist() { 
		//@ 게시판  > 조회 
		try {

			String sql = "select * from vwStudentBoard order by 글번호 desc";

			ResultSet rs = stat.executeQuery(sql);

			
			ArrayList<DTOStudentBoard> list = new ArrayList<DTOStudentBoard>();
			
			while (rs.next()) {
				
				DTOStudentBoard dto = new DTOStudentBoard();
				
				dto.setBoard_seq(rs.getString("글번호"));
				dto.setStudent_name(rs.getString("작성자"));	
				dto.setBoard_regdate(rs.getString("날짜"));
				dto.setBoard_title(rs.getString("제목"));
				dto.setBoard_content(rs.getString("내용"));
				
				list.add(dto);
			}
			
			
		
			
			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	public int boardwrite (DTOBoard dto) {
		//@ 게시판 > 작성
		//insert into Board(seq, student_seq, title, content, regdate) values (101,101,'테스트','테스트입니다',to_date(sysdate,'yyyy-mm-dd'));
		String sql = "insert into Board(seq, student_seq, title, content, regdate)"
						+ "values (board_seq.nextval, ?,?,?,to_date(sysdate,'yyyy-mm-dd'))";
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, Service.student_seq);
			pstat.setString(2, dto.getTitle());
			pstat.setString(3, dto.getContent());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return 0;
		
		
	}
	
	
	public int edit(DTOStudentBoard dto) {
		//@ 게시판  > 수정 
		try {
 
			String sql = "update Board set title = ? , content = ? where seq = ?";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getBoard_title());
			pstat.setString(2, dto.getBoard_content());
			pstat.setString(3, dto.getBoard_seq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("edit");
		}

		return 0;
	}

	
	
	public ArrayList<DTOStudentBoard> boardelist() { 
		//게시판 목록 불러오기 

		try {

			String sql = "select * from vwStudentBoard order by 글번호 desc";

			ResultSet rs = stat.executeQuery(sql);

			
			ArrayList<DTOStudentBoard> list = new ArrayList<DTOStudentBoard>();
			
			while (rs.next()) {
				
				DTOStudentBoard dto = new DTOStudentBoard();
				
				dto.setBoard_seq(rs.getString("글번호"));
				dto.setStudent_name(rs.getString("작성자"));	
				dto.setBoard_regdate(rs.getString("날짜"));
				dto.setBoard_title(rs.getString("제목"));
				dto.setBoard_content(rs.getString("내용"));
				
				list.add(dto);
			}
			
			
			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public DTOStudentBoard choicenumber(String seq) { 
		//선택된 목록 불러오기

		try {

			String sql = "select seq, to_char(regdate, 'yyyy-mm-dd') as regdate, title, content from Board where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOStudentBoard dto = new DTOStudentBoard();

			if(rs.next()) {
			
				dto.setBoard_seq(rs.getString("seq"));
				dto.setBoard_regdate(rs.getString("regdate"));
				dto.setBoard_title(rs.getString("title"));
				dto.setBoard_content(rs.getString("content"));
				
				return dto;
				
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			
		}
		return null;
	} //선택된 목록 불러오기 목록

	public int boarddelete(String seq) { 
		//게시판 게시글 삭제
	
		try {
			
			String sql = "delete from Board where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
			
		}
		
		return 0;
	}

	//========================게시판========================\\
	
	//========================교사평가========================\\
	public int teacherTest(DTOTeacherEvaluation dto) {
		//교사평가
		//계획서이행점수, 이해및전달점수, 소통점수, 유익성점수, 통합만족도점수, '건의사항' 
		String sql = "insert into TeacherEvaluation "
						+ "( seq, plan_score, content_score, communication_score, benefit_score ,satisfaction_score , suggestion , opencoursestudent_seq) "
							+ "VALUES (teacherevaluation_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getPlan_score());
			pstat.setString(2, dto.getContent_score());
			pstat.setString(3, dto.getCommunication_score());
			pstat.setString(4, dto.getBenefit_score());
			pstat.setString(5, dto.getSatisfaction_score());
			pstat.setString(6, dto.getSuggestion());
			pstat.setString(7, dto.getOpencoursestudent_seq());
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return 0;
		
	}
	//========================교사평가========================\\


	//========================기업공고========================\\
	public ArrayList<DTOViewCompany> list(String word, String area, String scale) {

		try {

			String sql = "SELECT * FROM vwViewCompany2 WHERE 연봉 >= ? AND 지역 IN(" + area + ") AND 계열_규모 = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);
			pstat.setString(2, scale);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOViewCompany> list = new ArrayList<DTOViewCompany>();

			while (rs.next()) {

				DTOViewCompany dto = new DTOViewCompany();

				dto.setSeq(rs.getString("공고번호"));
				dto.setName(rs.getString("기업명"));
				dto.setScale(rs.getString("계열_규모"));
				dto.setEmployee_num(rs.getString("사원수"));
				dto.setEstablished_year(rs.getString("설립년도"));
				dto.setArea(rs.getString("지역"));
				dto.setSalary(rs.getString("연봉"));
				dto.setDeadline(rs.getString("마감일"));
				dto.setCourse(rs.getString("요구과정"));
				dto.setMajor(rs.getString("요구전공여부"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;

	}// list

	public ArrayList<DTOCompany> list_1() {

		try {

			String sql = "SELECT * FROM vwViewCompanyhi";

			ResultSet rs = stat.executeQuery(sql);

			ArrayList<DTOCompany> list = new ArrayList<DTOCompany>();

			while (rs.next()) {

				DTOCompany dto = new DTOCompany();

				dto.setSeq(rs.getString("기업번호"));
				dto.setName(rs.getString("기업명"));
				dto.setScale(rs.getString("규모"));
				dto.setEmployee_num(rs.getString("사원수"));
				dto.setEstablished_year(rs.getString("연혁"));
				dto.setArea(rs.getString("지역"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;

	}// list

	public int add(DTONoticeApplication dto) {

		
		String sql = "INSERT INTO NoticeApplication (seq , notice_seq,  application_date , opencoursestudent_seq) VALUES ( noticeApplication_seq.nextval, ? , SYSDATE , ?)";
		
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getNotice_seq());
			pstat.setString(2, dto.getOpencoursestudent_seq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return 0;
	}
	//========================기업공고========================\\
	
	
	//**
	public DTOQnA getQuestion() {
		
		try {
				
				String sql = "select * from QnA where seq = 1";
				
		//			pstat = conn.prepareStatement(sql);
		//			pstat.setString(1, sel);
				
				ResultSet rs = stat.executeQuery(sql);
				
				DTOQnA dto = new DTOQnA();//복사용
				
				if (rs.next()) {
					//dto.setSeq(rs.getString("seq"));
					dto.setQuestion_title(rs.getString("question_title"));
					dto.setQuestion_content(rs.getString("question_content"));
					dto.setQuestion_date(rs.getString("question_date"));
					
					return dto;//return 빼먹지 말것!!!!!!!!!!!!!
				}
				
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
				return null;
		}
		
		public ArrayList<DTOQnA> getQuestion2() {
			try {
				
				String sql = "select * from QnA where seq = 1";
				
		//			pstat = conn.prepareStatement(sql);
		//			pstat.setString(1, sel);
				
				ResultSet rs = stat.executeQuery(sql);
				
				DTOQnA dto = new DTOQnA();//복사용
				
				ArrayList<DTOQnA> list = new ArrayList<DTOQnA>();
				
				if (rs.next()) {

					dto.setSeq(rs.getString("seq"));
					dto.setAnswer_title(rs.getString("question_title"));
					dto.setAnswer_content(rs.getString("question_content"));
					dto.setAnswer_date(rs.getString("to_char(question_date)"));
					
					list.add(dto);
				}
				
				return list;//return 빼먹지 말것!!!!!!!!!!!!!	
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
				return null;
		}

		public int addQuestion(DTOQnA dto) {
				
			try {
				String sql = "update qna set question_title = ?, question_content = ?, question_date = to_date(?, 'yy-mm-dd') where seq = 1";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, dto.getQuestion_title());
				pstat.setString(2, dto.getQuestion_content());
				pstat.setString(3, dto.getQuestion_date());
			
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		public int delQuestion(String sel) {
			
		try {
				
				String sql = "update qna set question_title = ' ', question_content = ' ', question_date = '' where seq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, sel);
			
			
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return 0;
		}

		public ArrayList<DTOAnswerInformation> AnswerList(String sel) {
			
		try {
				
				String sql = "select num, student_name, teacher_name, answer_title, answer_content, to_char(answer_date) from vwteacheranswerinformation where num = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, sel);
				
				ResultSet rs = pstat.executeQuery();
				
				DTOAnswerInformation dto = new DTOAnswerInformation();//복사용
				
				ArrayList<DTOAnswerInformation> list = new ArrayList<DTOAnswerInformation>();
				
				if (rs.next()) {
					//dto.setSeq(rs.getString("seq"));
					dto.setNum(rs.getString("num"));
					dto.setTeacher_name(rs.getString("teacher_name"));
					dto.setAnswer_title(rs.getString("answer_title"));
					dto.setAnswer_content(rs.getString("answer_content"));
					dto.setAnswer_date(rs.getString("to_char(answer_date)"));
					
					list.add(dto);
				}
				
				return list;//return 빼먹지 말것!!!!!!!!!!!!!
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		return null;
		}

	
	
	
}//class DAO
	
	
