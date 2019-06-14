package kr.co.sist.sec.administrator.score_attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.sist.sec.dto.DTOAllSubjectScore;
import kr.co.sist.sec.dto.DTOCourseAttendance;
import kr.co.sist.sec.dto.DTOCourseAttendanceSelect;
import kr.co.sist.sec.dto.DTONotice;
import kr.co.sist.sec.dto.DTOPeriodAttendance;
import kr.co.sist.sec.dto.DTOPersonalAttendance;
import kr.co.sist.sec.dto.DTOPersonalAttendanceDetail;
import kr.co.sist.sec.dto.DTOPersonalScore;
import kr.co.sist.sec.dto.DTOPersonalScoreDetail;
import kr.co.sist.sec.dto.DTOSubjectSelect;
import kr.co.sist.sec.dto.DTOTestManage;
import kr.co.sist.sec.dto.DTOTestManageDetail;
import kr.co.sist.sec.dto.DTOViewCompany;
import kr.co.sist.sec.dto.DTOvwCheckStudentmanager;
import kr.co.sist.sec.dto.DTOvwEvaluationTeacher;
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
			e.printStackTrace();
			System.out.println("DAO.Constructor");
		}
	}

	public void close() {

		try {

			this.conn.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	/**
	 * 
	 * @안상현 시험관리 과목별 시험정보 과정 선택 [과정명] [시작일] [종료일] [강의실명]
	 */
	public ArrayList<DTOTestManage> TestOClist() {
		
		try {

			String sql = "select * from vwTestManage";

			ResultSet rs = stat.executeQuery(sql);

			ArrayList<DTOTestManage> list = new ArrayList<DTOTestManage>();

			while (rs.next()) {

				DTOTestManage dtotm = new DTOTestManage();

				dtotm.setCourse_seq(rs.getString("과정번호"));
				dtotm.setCourse_name(rs.getString("과정명"));
				dtotm.setStart_date(rs.getString("시작일"));
				dtotm.setEnd_date(rs.getString("종료일"));
				dtotm.setClassroom_name(rs.getString("강의실명"));

				list.add(dtotm);

			} // while

			return list;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	} // 시험관리 - 과정선택

	public ArrayList<DTOTestManage> TestOClistSearch(String word) {

		try {

			String sql = "select * from vwTestManage where 과정번호 = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOTestManage> list1 = new ArrayList<DTOTestManage>();

			while (rs.next()) {

				DTOTestManage dtotm = new DTOTestManage();

				dtotm.setCourse_seq(rs.getString("과정번호"));
				dtotm.setCourse_name(rs.getString("과정명"));
				dtotm.setStart_date(rs.getString("시작일"));
				dtotm.setEnd_date(rs.getString("종료일"));
				dtotm.setClassroom_name(rs.getString("강의실명"));

				list1.add(dtotm);

			} // while

			return list1;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	} // 시험관리 - 과정선택후 조회

//----------------------------------------------------------------------------------------

	/**
	 * @안상현 시험관리 과목별 시험정보 [과정내 과목] [성적등록여부] [시험문제파일등록여부]
	 */
	public ArrayList<DTOTestManageDetail> TestManage(String word) {

		try {

			String sql = "select * from vwTestManageDetail where 과정시작일 = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOTestManageDetail> list2 = new ArrayList<DTOTestManageDetail>();

			while (rs.next()) {

				DTOTestManageDetail dtotmd = new DTOTestManageDetail();

				dtotmd.setCourse_subject(rs.getString("과정내과목"));
				dtotmd.setCourse_start_date(rs.getString("과정시작일"));
				dtotmd.setTestfile_ox(rs.getString("시험문제파일등록여부"));
				dtotmd.setScore_ox(rs.getString("성적등록여부"));

				list2.add(dtotmd);
			}
			return list2;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // 시험관리 - 과목별 시험정보

	/**
	 * 
	 * @안상현 과목별 성적조회 과목 선택부분 [과목명]
	 * 
	 */
	public ArrayList<DTOSubjectSelect> subjectsel() {

		try {
			String sql = "select * from vwSubjectSelect";

			ResultSet rs = stat.executeQuery(sql);

			ArrayList<DTOSubjectSelect> list = new ArrayList<DTOSubjectSelect>();

			while (rs.next()) {

				DTOSubjectSelect dtoss = new DTOSubjectSelect();

				dtoss.setSubject_seq(rs.getString("과목번호"));
				dtoss.setSubject_name(rs.getString("과목명"));

				list.add(dtoss);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // 과목선택

	/*
	 * -- 과목별 성적으로 모든 교육생 조회 -- [학생명] [주민번호] [필기] [실기] -- 선택한 과목에 대한 그 개인정보가 모두 출력
	 */
	public ArrayList<DTOAllSubjectScore> allsubjectscore(String word) {

		try {

			String sql = "select * from vwAllSubjectScore where 과목번호 = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOAllSubjectScore> list1 = new ArrayList<DTOAllSubjectScore>();

			while (rs.next()) {

				DTOAllSubjectScore dtoasc = new DTOAllSubjectScore();

				dtoasc.setStudent_name(rs.getString("학생명"));
				dtoasc.setSsn(rs.getString("주민번호"));
				dtoasc.setSubject_seq(rs.getString("과목번호"));
				dtoasc.setWritten_test(rs.getString("필기"));
				dtoasc.setPerformance_test(rs.getString("실기"));

				list1.add(dtoasc);
			}
			return list1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}// 과목별 성적으로 모든 교육생 출력

	/**
	 * @안상현 개인별 성적조회 개인별 성적 전체조회 -- [학생명] [주민번호] [개설과정명] [개설과정기간] [교사명] [강의실명] --
	 */

	public ArrayList<DTOPersonalScore> PersonalScore() {

		try {

			String sql = "select * from vwPersonalScore";

			ResultSet rs = stat.executeQuery(sql);

			ArrayList<DTOPersonalScore> list = new ArrayList<DTOPersonalScore>();

			while (rs.next()) {

				DTOPersonalScore dtops = new DTOPersonalScore();

				dtops.setStudent_name(rs.getString("학생명"));
				dtops.setSsn(rs.getString("주민번호"));
				dtops.setOpencourse_name(rs.getString("개설과정명"));
				dtops.setOpencourse_period(rs.getString("개설과정기간"));
				dtops.setTeacher_name(rs.getString("교사명"));

				list.add(dtops);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @안상현 개인별 성적 출력 -- 개인별 성적 세부조회 -- [개설과목명] [개설과목기간] [교사명] [출결] [필기] [실기]
	 * 
	 */

	public ArrayList<DTOPersonalScoreDetail> personalscoredetail(String word) {

		try {

			String sql = "select * from vwpersonalscoredetail where \"학생명\" = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOPersonalScoreDetail> list = new ArrayList<DTOPersonalScoreDetail>();

			while (rs.next()) {

				DTOPersonalScoreDetail dtopsd = new DTOPersonalScoreDetail();

				dtopsd.setStudent_name(rs.getString("학생명"));
				dtopsd.setOpensubject_name(rs.getString("개설과목명"));
				dtopsd.setOpensubject_period(rs.getString("개설과목기간"));
				dtopsd.setTeacher_name(rs.getString("교사명"));
				dtopsd.setAttendance(rs.getString("출결"));
				dtopsd.setWritten_test(rs.getString("필기"));
				dtopsd.setPerformance_test(rs.getString("실기"));

				list.add(dtopsd);

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // 개인별 성적 세부조회

	/**
	 * @안상현 출결 관리 및 조회 기간별 출결 조회 -- [개설과정명] [학생명] [상태]
	 */

	public ArrayList<DTOPeriodAttendance> periodattendance(String word) {

		try {

			String sql = "select * from vwPeriodAttendance where 날짜 = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOPeriodAttendance> list = new ArrayList<DTOPeriodAttendance>();

			while (rs.next()) {

				DTOPeriodAttendance dtopa = new DTOPeriodAttendance();

				dtopa.setGeuntae_date(rs.getString("날짜"));
				dtopa.setOpencourse_name(rs.getString("개설과정명"));
				dtopa.setStudent_name(rs.getString("학생명"));
				dtopa.setStatus(rs.getString("상태"));

				list.add(dtopa);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // 기간별 출결 조회

	/**
	 * @안상현 출결 관리 및 조회 과정 선택하기 과정별 출결 조회 [개설과정명]
	 */
	public ArrayList<DTOCourseAttendanceSelect> courseattendanceselect() {

		try {

			String sql = "select * from vwCourseAttendanceSelect";

			ResultSet rs = stat.executeQuery(sql);

			ArrayList<DTOCourseAttendanceSelect> list = new ArrayList<DTOCourseAttendanceSelect>();

			while (rs.next()) {

				DTOCourseAttendanceSelect dtocas = new DTOCourseAttendanceSelect();

				dtocas.setOpencourse_seq(rs.getString("과정번호"));
				dtocas.setOpencourse_name(rs.getString("개설과정명"));

				list.add(dtocas);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // 출결 조회 - 과정별출결- 과정선택

	/**
	 * 
	 * @안상현 출결 관리 및 조회 과정별 출결 조회
	 * 
	 *      --[날짜] [과정] [정상] [지각] [외출] [[병가] --2019-03-01 Java&python 20 0 0 0
	 *      --2019-03-02 Java&python 23 0 0 0 --.. --2019-04-02
	 */

	public ArrayList<DTOCourseAttendance> courseattendance(String word) {

		try {

			String sql = "select * from vwCourseAttendance where 과정번호 = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOCourseAttendance> list1 = new ArrayList<DTOCourseAttendance>();

			while (rs.next()) {

				DTOCourseAttendance dtoca = new DTOCourseAttendance();

				dtoca.setGeuntae_date(rs.getString("날짜"));
				dtoca.setCourse_seq(rs.getString("과정번호"));
				dtoca.setCourse_name(rs.getString("과정명"));
				dtoca.setAttendance(rs.getString("정상"));
				dtoca.setLate(rs.getString("지각"));
				dtoca.setGo_out(rs.getString("외출"));
				dtoca.setAbsence(rs.getString("병가"));

				list1.add(dtoca);
			}
			return list1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // 과정별 출결 세부사항

	/**
	 * 
	 * @안상현 출결 관리 및 조회 -- 개인별 출결조회 학생 검색 [학생명] [전화번호]
	 */
	public ArrayList<DTOPersonalAttendance> personalattendance() {

		try {

			String sql = "select * from vwPersonalAttendance";

			ResultSet rs = stat.executeQuery(sql);

			ArrayList<DTOPersonalAttendance> list = new ArrayList<DTOPersonalAttendance>();

			while (rs.next()) {

				DTOPersonalAttendance dtoper = new DTOPersonalAttendance();

				dtoper.setStudent_name(rs.getString("학생명"));
				dtoper.setTel(rs.getString("전화번호"));

				list.add(dtoper);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // 개인별 출결조회

	/**
	 * @안상현 출결 관리 및 조회 학생 검색 개인별 출결 조회 [과정명] [학생명] [근태상황]
	 */

	public ArrayList<DTOPersonalAttendanceDetail> personalattendancedetail(String word) {

		try {

			String sql = "select * from vwPersonalAttendanceDetail where \"학생명\" = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, word);

			ResultSet rs = pstat.executeQuery();

			ArrayList<DTOPersonalAttendanceDetail> list1 = new ArrayList<DTOPersonalAttendanceDetail>();

			while (rs.next()) {

				DTOPersonalAttendanceDetail dtopad = new DTOPersonalAttendanceDetail();

				dtopad.setGeuntae_date(rs.getString("날짜"));
				dtopad.setCourse_name(rs.getString("과정명"));
				dtopad.setStudent_name(rs.getString("학생명"));
				dtopad.setGeuntae_status(rs.getString("근태상황"));

				list1.add(dtopad);
			}
			return list1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}// 개인별 출결세부조회

	//////////////////////

	public ArrayList<DTOViewCompany> list() {

		try {

			String sql = "SELECT * FROM vwViewCompany";
			ResultSet rs = stat.executeQuery(sql);

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

	public int addlist(DTONotice dto) {

		String sql = "INSERT INTO Notice (seq, company_seq, salary, deadline, course, major) VALUES (?, ?,  ? , ? , ? , ?)";

		try {

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSeq());
			pstat.setString(2, dto.getCompany_seq());
			pstat.setString(3, dto.getSalary());
			pstat.setString(4, dto.getDeadline());
			pstat.setString(5, dto.getCourse());
			pstat.setString(6, dto.getMajor());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return 0;

	}// list

	public int del(String seq) {

		try {

			String sql = "DELETE FROM Notice WHERE seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return 0;

	}// list

	public ArrayList<DTOvwCheckStudentmanager> list_1() {

		try {

			String sql = "SELECT * FROM vwCheckStudentmanager";
			ResultSet rs = stat.executeQuery(sql);

			ArrayList<DTOvwCheckStudentmanager> list = new ArrayList<DTOvwCheckStudentmanager>();

			while (rs.next()) {

				DTOvwCheckStudentmanager dto = new DTOvwCheckStudentmanager();

				dto.setSeq(rs.getString("기업번호"));
				dto.setName1(rs.getString("기업명"));
				dto.setSalary(rs.getString("연봉"));
				dto.setDeadline(rs.getString("마감일"));
				dto.setCourse(rs.getString("요구과정"));
				dto.setMajor(rs.getString("전공여부"));
				dto.setName2(rs.getString("학생명"));
				dto.setName3(rs.getString("과정명"));
				dto.setApplication_date(rs.getString("신청일"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;

	}// list

	public ArrayList<DTOvwEvaluationTeacher> list_2() {

		try {

			String sql = "SELECT * FROM vwEvaluationTeacher";
			ResultSet rs = stat.executeQuery(sql);

			ArrayList<DTOvwEvaluationTeacher> list = new ArrayList<DTOvwEvaluationTeacher>();

			while (rs.next()) {

				DTOvwEvaluationTeacher dto = new DTOvwEvaluationTeacher();

				dto.setName(rs.getString("학생명"));
				dto.setName2(rs.getString("선생님명"));
				dto.setName3(rs.getString("과정명"));
				dto.setDate(rs.getString("과정기간"));
				dto.setSeq(rs.getString("번호"));
				dto.setPlan_score(rs.getString("강의계획서 이행 점수"));
				dto.setContent_score(rs.getString("강의내용 이해 점수"));
				dto.setCommunication_score(rs.getString("교사 소통점수"));
				dto.setBenefit_score(rs.getString("강의 유익성 점수"));
				dto.setSatisfaction_score(rs.getString("전반적 만족도"));
				dto.setSuggestion(rs.getString("건의사항"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;

	}// list

} // class
