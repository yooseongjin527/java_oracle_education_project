package kr.co.sist.sec.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import kr.co.sist.sec.dto.DTOAllot;
import kr.co.sist.sec.dto.DTOAllotInformation;
import kr.co.sist.sec.dto.DTOAnswerInformation;
import kr.co.sist.sec.dto.DTOCourseInformation;
import kr.co.sist.sec.dto.DTOLogin;
import kr.co.sist.sec.dto.DTOOpenSubject;
import kr.co.sist.sec.dto.DTOPerformanceTest;
import kr.co.sist.sec.dto.DTOQnA;
import kr.co.sist.sec.dto.DTOQuestionInformation;
import kr.co.sist.sec.dto.DTOScore;
import kr.co.sist.sec.dto.DTOScoreInformation;
import kr.co.sist.sec.dto.DTOTeacher;
import kr.co.sist.sec.dto.DTOTeacherEvaluationInformation;
import kr.co.sist.sec.dto.DTOTeacherEvaluationRate;
import kr.co.sist.sec.dto.DTOTeacher_2;
import kr.co.sist.sec.dto.DTOTeacher_3;
import kr.co.sist.sec.dto.DTOTeacher_4;
import kr.co.sist.sec.dto.DTOWrittenTest;
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
			System.out.println("teacher.DAO.Constructor : " + e.toString());
		}
	}
	
	public boolean isConnected() {
		try {
			
			return !this.conn.isClosed();
			
		} catch (Exception e) {
			System.out.println("teacher.DAO.isConnected() : " + e.toString());
			return false;
		}
	}
	
	public void close() {
		try {
			
			this.conn.close();
			
		} catch (Exception e) {
			System.out.println("teacher.DAO.close() : " + e.toString());
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
				String seq = id.substring(min, l);
				String sql = "select * from Teacher where seq = ?";
				
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
			System.out.println("teacher.DAO.get() : " + e.toString());
		}
		
		return null;
	}



	public DTOTeacher getSeqFromNameAndTel(DTOTeacher dto) {
		try {
			String sql = "select * from Teacher where name = ? and tel = ?";
			
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

	public DTOTeacher getPwFromId(DTOTeacher dto) {
		try {
			String sql = "select * from Teacher where name = ? and seq = ?";
			
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
	
	public ArrayList<DTOCourseInformation> courseList(String seq) {
		
		try {
			
			String sql = "select * from vwCourseInformation where course_num = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOCourseInformation> list = new ArrayList<DTOCourseInformation>();
			
			while (rs.next()) {
				//레코드 1개 -> DTO 1개
				DTOCourseInformation dto = new DTOCourseInformation();
				
				dto.setSeq(rs.getString("course_num")); // 컬럼값 -> DTO 멤버변수
				dto.setCourse_name(rs.getString("course_name"));
				dto.setCourse_period(rs.getString("course_period"));
				
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public ArrayList<DTOAllotInformation> subjectList() {
		
	try {
			
			String sql = "select * from vwAllotInformation where course_num = 1";
			
			ResultSet rs = stat.executeQuery(sql);
			
			ArrayList<DTOAllotInformation> list = new ArrayList<DTOAllotInformation>();
			
			while (rs.next()) {
				//레코드 1개 -> DTO 1개
				DTOAllotInformation dto = new DTOAllotInformation();
				
				dto.setNum(rs.getString("num"));
				dto.setCourse_num(rs.getString("course_num"));
				dto.setCourse_name(rs.getString("course_name")); // 컬럼값 -> DTO 멤버변수
				dto.setCourse_period(rs.getString("course_period"));
				dto.setClassroom(rs.getString("classroom"));
				dto.setSubject_name(rs.getString("subject_name"));
				dto.setSubject_period(rs.getString("subject_period"));
				dto.setTextbook_name(rs.getString("textbook_name"));
				dto.setSubject_end(rs.getString("subject_end"));
				dto.setAttendance_allot(rs.getInt("attendance_allot"));
				dto.setWrittentest_allot(rs.getInt("writtentest_allot"));
				dto.setPerformancetest_allot(rs.getInt("performancetest_allot"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public ArrayList<DTOAllotInformation> subjectList(String sel) {
		
		try {
				
				String sql = "select * from vwAllotInformation where course_num = 1 and num = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1,  sel);
				
				ResultSet rs = pstat.executeQuery();
				
				ArrayList<DTOAllotInformation> list = new ArrayList<DTOAllotInformation>();
				
				while (rs.next()) {
					//레코드 1개 -> DTO 1개
					DTOAllotInformation dto = new DTOAllotInformation();
					
					dto.setNum(rs.getString("num"));
					dto.setCourse_num(rs.getString("course_num"));
					dto.setCourse_name(rs.getString("course_name")); // 컬럼값 -> DTO 멤버변수
					dto.setCourse_period(rs.getString("course_period"));
					dto.setClassroom(rs.getString("classroom"));
					dto.setSubject_name(rs.getString("subject_name"));
					dto.setSubject_period(rs.getString("subject_period"));
					dto.setTextbook_name(rs.getString("textbook_name"));
					dto.setSubject_end(rs.getString("subject_end"));
					dto.setAttendance_allot(rs.getInt("attendance_allot"));
					dto.setWrittentest_allot(rs.getInt("writtentest_allot"));
					dto.setPerformancetest_allot(rs.getInt("performancetest_allot"));
					
					list.add(dto);
				}
				
				return list;
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return null;
		}

	
	public int edit(DTOAllot dto) {
		
		try {
			String sql = "update allot set attendance_allot = ?, writtentest_allot = ?, performancetest_allot = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getAttendance_allot());
			pstat.setString(2, dto.getWrittentest_allot());
			pstat.setString(3, dto.getPerformancetest_allot());
			pstat.setString(4, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public DTOAllot getAllot(int sel) {
		
		try {
			
			String sql = "select * from Allot where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sel);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOAllot dto = new DTOAllot();//복사용
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setAttendance_allot(rs.getString("attendance_allot"));
				dto.setWrittentest_allot(rs.getString("writtentest_allot"));
				dto.setPerformancetest_allot(rs.getString("performancetest_allot"));
				dto.setOpensubject_seq(rs.getString("opensubject_seq"));
				
				return dto;//return 빼먹지 말것!!!!!!!!!!!!!
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public int add(DTOAllot dto) {
		
		try {
			String sql = "update allot set attendance_allot = ?, writtentest_allot = ?, performancetest_allot = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getAttendance_allot());
			pstat.setString(2, dto.getWrittentest_allot());
			pstat.setString(3, dto.getPerformancetest_allot());
			pstat.setString(4, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public DTOWrittenTest getWrittenTest(String sel) {
		
		try {
			
			String sql = "select * from WrittenTest where opensubject_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOWrittenTest dto = new DTOWrittenTest();//복사용
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setQuestion(rs.getString("question"));
				dto.setAnswer(rs.getString("answer"));
				dto.setOpensubject_seq(rs.getString("opensubject_seq"));
				
				return dto;//return 빼먹지 말것!!!!!!!!!!!!!
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public DTOPerformanceTest getPerformanceTest(String sel) {
		
		try {
			
			String sql = "select * from PerformanceTest where opensubject_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOPerformanceTest dto = new DTOPerformanceTest();//복사용
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setQuestion(rs.getString("question"));
				dto.setAnswer(rs.getString("answer"));
				dto.setOpensubject_seq(rs.getString("opensubject_seq"));
				
				return dto;//return 빼먹지 말것!!!!!!!!!!!!!
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;
	}

	

	public DTOOpenSubject getWrittenTest_date(String sel3) {
		
		
	try {
			
			String sql = "select to_char(writtentest_date) from openSubject where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel3);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOOpenSubject dto = new DTOOpenSubject();//복사용
			
			if (rs.next()) {
				dto.setWrittentest_date(rs.getString("to_char(writtentest_date)"));
				
				return dto;//return 빼먹지 말것!!!!!!!!!!!!!
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	
	}
	
	public int WTInput(DTOWrittenTest dto) {
		
		try {
			String sql = "update WrittenTest set question = ?, answer = ?  where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getQuestion());
			pstat.setString(2, dto.getAnswer());
			pstat.setString(3, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int WTDInput(DTOOpenSubject dto2) {
		
		try {
			String sql = "update opensubject set writtentest_date = to_date(?, 'yy-mm-dd') where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto2.getWrittentest_date());
			pstat.setString(2, dto2.getSeq());
	
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public DTOOpenSubject PerformanceTest_date(String sel3) {
		
		try {
			
			String sql = "select to_char(performancetest_date) from openSubject where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel3);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOOpenSubject dto = new DTOOpenSubject();//복사용
			
			if (rs.next()) {
				dto.setPerformancetest_date(rs.getString("to_char(performancetest_date)"));
				
				return dto;//return 빼먹지 말것!!!!!!!!!!!!!
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public int PTInput(DTOPerformanceTest dto) {
		
		try {
			String sql = "update PerformanceTest set question = ?, answer = ?  where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getQuestion());
			pstat.setString(2, dto.getAnswer());
			pstat.setString(3, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int PTDInput(DTOOpenSubject dto2) {
		
		try {
			String sql = "update opensubject set performancetest_date = to_date(?, 'yy-mm-dd') where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto2.getPerformancetest_date());
			pstat.setString(2, dto2.getSeq());
	
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<DTOScoreInformation> scoreList(String sel) {
		
		try {
			
			String sql = "select * from vwScoreInformation where oc_seq = 1 and os_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1,  sel);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOScoreInformation> list = new ArrayList<DTOScoreInformation>();
			
			while (rs.next()) {
				//레코드 1개 -> DTO 1개
				DTOScoreInformation dto = new DTOScoreInformation();
				
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setQuit_status(rs.getString("quit_status"));// 컬럼값 -> DTO 멤버변수
				dto.setSubject_name(rs.getString("subject_name"));
				dto.setAttendance_score(rs.getInt("attendance_score"));
				dto.setWrittentest_score(rs.getInt("writtentest_score"));
				dto.setPerformance_score(rs.getInt("performancetest_score"));
				dto.setTotal(rs.getInt("total"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}


	public DTOScore getScore(String sel) {
		
	try {
			
			String sql = "select * from Score where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOScore dto = new DTOScore();//복사용
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setAttendance(rs.getInt("attendance"));
				dto.setWritten_test(rs.getInt("written_test"));
				dto.setPerformance_test(rs.getInt("performance_test"));
				
				return dto;//return 빼먹지 말것!!!!!!!!!!!!!
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public int addScore(DTOScore dto) {
		
		try {
			String sql = "update Score set attendance = ?, written_test = ?, performance_test = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, dto.getAttendance());
			pstat.setInt(2, dto.getWritten_test());
			pstat.setInt(3, dto.getPerformance_test());
			pstat.setString(4, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<DTOQuestionInformation> qnaList() {
		
		try {
			
			String sql = "select * from vwstudentQuestionInformation";
			
		
			ResultSet rs = stat.executeQuery(sql);
			
			ArrayList<DTOQuestionInformation> list = new ArrayList<DTOQuestionInformation>();
			
			while (rs.next()) {
				//레코드 1개 -> DTO 1개
				DTOQuestionInformation dto = new DTOQuestionInformation();
				
				dto.setNum(rs.getString("num"));
				dto.setStudent_name(rs.getString("student_name"));
				dto.setQuestion_date(rs.getString("question_date"));
				dto.setQuestion_title(rs.getString("question_title"));
				dto.setQuestion_content(rs.getString("question_content"));
				dto.setAnswer_status(rs.getString("answer_status"));
		
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public int delAnswer(String sel) {
		
		try {
			
			String sql = "update qna set answer_title = null, answer_content = null, answer_date = null where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}

	public DTOQnA getQnA(String sel) {
		
		try {
			
			String sql = "select * from QnA where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			ResultSet rs = pstat.executeQuery();
			
			DTOQnA dto = new DTOQnA();//복사용
			
			if (rs.next()) {
				//dto.setSeq(rs.getString("seq"));
				dto.setAnswer_title(rs.getString("answer_title"));
				dto.setAnswer_content(rs.getString("answer_content"));
				dto.setAnswer_date(rs.getString("answer_date"));
				
				return dto;//return 빼먹지 말것!!!!!!!!!!!!!
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	public int editAnswer(DTOQnA dto) {
		
		try {
			String sql = "update qna set answer_title = ?, answer_content = ?, answer_date = to_date(?, 'yy-mm-dd') where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getAnswer_title());
			pstat.setString(2, dto.getAnswer_content());
			pstat.setString(3, dto.getAnswer_date());
			pstat.setString(4, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<DTOQuestionInformation> qnaList(String sel) {
		
			try {
			
			String sql = "select * from vwstudentQuestionInformation where num = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sel);
			
			ResultSet rs = pstat.executeQuery();
			
			ArrayList<DTOQuestionInformation> list = new ArrayList<DTOQuestionInformation>();
			
			while (rs.next()) {
				//레코드 1개 -> DTO 1개
				DTOQuestionInformation dto = new DTOQuestionInformation();
				
				dto.setNum(rs.getString("num"));
				dto.setStudent_name(rs.getString("student_name"));
				dto.setQuestion_date(rs.getString("question_date"));
				dto.setQuestion_title(rs.getString("question_title"));
				dto.setQuestion_content(rs.getString("question_content"));
				dto.setAnswer_status(rs.getString("answer_status"));
		
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public ArrayList<DTOTeacherEvaluationRate> TERList() {
			
			try {
			
			String sql = "select * from vwEvaluationRate";
				
			ResultSet rs = stat.executeQuery(sql);
			
			ArrayList<DTOTeacherEvaluationRate> list = new ArrayList<DTOTeacherEvaluationRate>();
			
			while (rs.next()) {
				//레코드 1개 -> DTO 1개
				DTOTeacherEvaluationRate dto = new DTOTeacherEvaluationRate();
				
				dto.setCourse_people(rs.getInt("course_people"));
				dto.setEvaluation_people(rs.getInt("evaluation_people"));
				dto.setEvaluation_participation_rate(rs.getString("evaluation_participation_rate"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public ArrayList<DTOTeacherEvaluationInformation> TEList() {
		
		try {
			
			String sql = "select * from vwteacherEvaluationInformation where num between 1 and 26";
				
			ResultSet rs = stat.executeQuery(sql);
			
			ArrayList<DTOTeacherEvaluationInformation> list = new ArrayList<DTOTeacherEvaluationInformation>();
			
			while (rs.next()) {
				//레코드 1개 -> DTO 1개
				DTOTeacherEvaluationInformation dto = new DTOTeacherEvaluationInformation();
				
				dto.setNum(rs.getInt("num"));
				dto.setAnonymous(rs.getString("anonymous"));
				dto.setStatus(rs.getString("status"));
				dto.setPlan_score(rs.getInt("plan_score"));
				dto.setContent_score(rs.getInt("content_score"));
				dto.setCommunication_score(rs.getInt("communication_score"));
				dto.setBenefit_score(rs.getInt("benefit_score"));
				dto.setSatisfaction_score(rs.getInt("satisfaction_score"));
				dto.setSuggestion(rs.getString("suggestion"));
			
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
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
				dto.setStudent_name(rs.getString("student_name"));
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

	public ArrayList<DTOTeacher_2> list(String word) {
		
		try {

			String sql = "select * from vwTeacher_2 WHERE \"교사 이름\" = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOTeacher_2> list = new ArrayList<DTOTeacher_2>();

			while (rs.next()) {

				DTOTeacher_2 dto = new DTOTeacher_2();

				dto.setTeacher_seq(rs.getString("교사 번호"));
				dto.setTeacher_name(rs.getString("교사 이름"));
				dto.setCourse_name(rs.getString("배정된 과정명"));
				dto.setOpenCourse_start_date(rs.getString("과정 시작일"));
				dto.setOpenCourse_end_date(rs.getString("과정 종료일"));
				dto.setSubject_name(rs.getString("배정 개설과목명"));
				dto.setOpenSubject_start_date(rs.getString("과목 시작일"));
				dto.setOpenSubject_end_date(rs.getString("과목 종료일"));
				dto.setClassroom_name(rs.getString("강의실명"));
				dto.setTextbook_name(rs.getString("교재명"));
				dto.setOpenCourse_seq(rs.getString("인원수"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;
	}

	public ArrayList<DTOTeacher_4> list1(String course) {
		
		try {

			String sql = "SELECT * FROM vwTeacher_5 WHERE 과목번호 = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, course);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOTeacher_4> list = new ArrayList<DTOTeacher_4>();

			while (rs.next()) {

				DTOTeacher_4 dto = new DTOTeacher_4();

				dto.setOpenSubject_seq(rs.getString("과목번호"));
				dto.setStudent_name(rs.getString("학생명"));
				dto.setStudent_tel(rs.getString("전화번호"));
				dto.setStudent_regdate(rs.getString("등록일"));
				dto.setOpenCourseStudent_status(rs.getString("상태"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;

	}

	public ArrayList<DTOTeacher_3> list_1(String date) {
		
		try {

			String sql = "SELECT * FROM vwTeacher_4 WHERE 날짜 = ? order by 과정번호, 교육생번호";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, date);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOTeacher_3> list = new ArrayList<DTOTeacher_3>();

			while (rs.next()) {

				DTOTeacher_3 dto = new DTOTeacher_3();

				dto.setCourse_seq(rs.getString("과정번호"));
				dto.setStudent_seq(rs.getString("교육생번호"));
				dto.setStudent_name(rs.getString("교육생 이름"));
				dto.setStatus_status(rs.getString("상태"));
				dto.setGeuntae_geuntae_date(rs.getString("날짜"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;
	}

	public ArrayList<DTOTeacher_3> list_2(String course) {
		
		try {

			String sql = "SELECT * FROM vwTeacher_4 WHERE 과정번호 = ? order by 교육생번호, 날짜";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, course);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOTeacher_3> list = new ArrayList<DTOTeacher_3>();

			while (rs.next()) {

				DTOTeacher_3 dto = new DTOTeacher_3();

				dto.setCourse_seq(rs.getString("과정번호"));
				dto.setStudent_seq(rs.getString("교육생번호"));
				dto.setStudent_name(rs.getString("교육생 이름"));
				dto.setStatus_status(rs.getString("상태"));
				dto.setGeuntae_geuntae_date(rs.getString("날짜"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;
	}

	public ArrayList<DTOTeacher_3> list_3(String name) {
		
		try {

			String sql = "SELECT * FROM vwTeacher_4 WHERE \"교육생 이름\" = ? order by 날짜";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOTeacher_3> list = new ArrayList<DTOTeacher_3>();

			while (rs.next()) {

				DTOTeacher_3 dto = new DTOTeacher_3();

				dto.setCourse_seq(rs.getString("과정번호"));
				dto.setStudent_seq(rs.getString("교육생번호"));
				dto.setStudent_name(rs.getString("교육생 이름"));
				dto.setStatus_status(rs.getString("상태"));
				dto.setGeuntae_geuntae_date(rs.getString("날짜"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;
	}
}
