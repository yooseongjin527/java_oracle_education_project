package kr.co.sist.sec.administrator.score_attendance;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.sec.dto.DTOAllSubjectScore;
import kr.co.sist.sec.dto.DTOCourseAttendance;
import kr.co.sist.sec.dto.DTOCourseAttendanceSelect;
import kr.co.sist.sec.dto.DTONotice;
import kr.co.sist.sec.dto.DTOPeriodAttendance;
import kr.co.sist.sec.dto.DTOPersonalAttendance;
import kr.co.sist.sec.dto.DTOPersonalAttendanceDetail;
import kr.co.sist.sec.dto.DTOPersonalScoreDetail;
import kr.co.sist.sec.dto.DTOSubjectSelect;
import kr.co.sist.sec.dto.DTOTestManage;
import kr.co.sist.sec.dto.DTOTestManageDetail;
import kr.co.sist.sec.dto.DTOViewCompany;
import kr.co.sist.sec.dto.DTOvwCheckStudentmanager;
import kr.co.sist.sec.dto.DTOvwEvaluationTeacher;

public class AdminService implements IService {

	private static AdminView adminview;
	private static Scanner scan;
	private static IService service;

	static {
		adminview = new AdminView();
		service = new AdminService();
		scan = new Scanner(System.in);

	}

	@Override
	public void testmanage() {
		
		
		boolean loop = true;

		while (loop) {
			adminview.flip();

			System.out.println();
			System.out.println("============================================================================   개설된 과정 목록   ================================================================================================");
			System.out.println();
			System.out.println("[과정번호]\t[과정명]\t\t\t\t\t\t\t\t\t[시작일]\t\t\t[종료일]\t\t[강의실명]");
			System.out.println();
			
			DAO dao = new DAO();

			ArrayList<DTOTestManage> list = dao.TestOClist();

			for (DTOTestManage tm : list) {
				System.out.printf("%s\t\t%s\t\t%s\t\t\t%s\t\t%s\n\n", tm.getCourse_seq(), tm.getCourse_name(),
						tm.getStart_date(), tm.getEnd_date(), tm.getClassroom_name());
			}

			dao.close();
			
			adminview.thickLine();
			System.out.println();
			adminview.chooseopencourse();
			String sel = scan.nextLine();
			System.out.println();
			
			adminview.flip();
			
			adminview.thickLine();
			System.out.println();
			
			System.out.println("[과정번호]\t[과정명]\t\t\t\t\t\t\t\t\t[시작일]\t\t\t[종료일]\t\t[강의실명]");
			dao = new DAO();

			ArrayList<DTOTestManage> list1 = dao.TestOClistSearch(sel);

			for (DTOTestManage tm1 : list1) {
				System.out.printf("%s\t\t%s\t\t%s\t\t\t%s\t\t%s\n\n", tm1.getCourse_seq(), tm1.getCourse_name(),
						tm1.getStart_date(), tm1.getEnd_date(), tm1.getClassroom_name());
			}

			dao.close();

			adminview.thickLine();
			dao = new DAO();

			adminview.choosecoursestartdate();
			String inputdate = scan.nextLine();

			adminview.flip();
			
			adminview.thickLine();
			System.out.println();
			System.out.println("[과정 내 과목]\t\t\t[과정시작일]\t\t\t[시험문제파일등록여부]\t\t[성적등록여부]");
			System.out.println();

			dao = new DAO();

			ArrayList<DTOTestManageDetail> list2 = dao.TestManage(inputdate);

			for (DTOTestManageDetail tmd : list2) {
				System.out.printf("%s\t%s\t\t\t%s\t\t\t\t%s\n", tmd.getCourse_subject(), tmd.getCourse_start_date(),
						tmd.getTestfile_ox(), tmd.getScore_ox());
			}

			dao.close();
			adminview.thickLine();
			
			adminview.pause();

			loop = false;
		} // while

	} // 6- 1- 1 시험관리 -과정선택

	@Override
	public void subjectselect() {
		boolean loop = true;

		while (loop) {
			
			adminview.flip();
			adminview.thickLine();
			System.out.println();
			System.out.println("[번호]\t\t[과목명]");
			System.out.println();

			DAO dao = new DAO();

			ArrayList<DTOSubjectSelect> list = dao.subjectsel();

			for (DTOSubjectSelect ss : list) {
				System.out.printf("%s\t\t%s\n", ss.getSubject_seq(), ss.getSubject_name());
			}

			dao.close();

			adminview.thickLine();
			adminview.choosesubject();
			String sel = scan.nextLine();
			
			adminview.flip();
			
			adminview.thickLine();
			System.out.println();
			System.out.println("[학생명]\t\t[주민번호]\t\t[과목번호]\t\t[필기]\t\t[실기]");
			System.out.println();

			dao = new DAO();

			ArrayList<DTOAllSubjectScore> list1 = dao.allsubjectscore(sel);

			for (DTOAllSubjectScore as : list1) {
				System.out.printf("%s\t\t\t%s\t\t\t%s\t\t\t%s\t\t%s\n", as.getStudent_name(), as.getSsn(),
						as.getSubject_seq(), as.getWritten_test(), as.getPerformance_test());
			}
			dao.close();
			
			adminview.thickLine();
			adminview.pause();

			loop = false;

		} // while

	} // 6 - 2 - 1 과목별성적 (완)

	@Override
	public void personalscodetail() {

		boolean loop = true;

		while (loop) {
			
			adminview.flip();
			adminview.thinLine();
			
			adminview.chooseperson();
			String name = scan.nextLine();
			
			adminview.flip();
			System.out.println();
			System.out.println("[학생명]\t\t[개설과목명]\t\t[개설과목기간]\t\t[교사명]\t\t[출결]\t\t[필기]\t\t[실기]");
			System.out.println();

			DAO dao = new DAO();

			ArrayList<DTOPersonalScoreDetail> list = dao.personalscoredetail(name);

			for (DTOPersonalScoreDetail dtopsd : list) {
				System.out.printf("%s\t\t%s%s\t\t%s\t\t%s\t\t%s\t\t%s\n", dtopsd.getStudent_name(),
						dtopsd.getOpensubject_name(), dtopsd.getOpensubject_period(), dtopsd.getTeacher_name(),
						dtopsd.getAttendance(), dtopsd.getWritten_test(), dtopsd.getPerformance_test());
			}
			dao.close();
			
			adminview.thickLine();
			adminview.pause();

			loop = false;
		}
	} // 개인별 성적 세부조회 6-2-2

	@Override
	public void periattendance() {
		boolean loop = true;

		while (loop) {
			
			adminview.flip();
			adminview.thinLine();
			System.out.println();

			adminview.chooseperiod();
			String input = scan.nextLine(); // 19-01-02 ~ 19-01-07
			adminview.flip();
			
			System.out.println("[날짜]\t\t\t[개설과정명]\t\t\t\t\t\t\t\t\t[학생명]\t[상태]");
			System.out.println();

			DAO dao = new DAO();

			ArrayList<DTOPeriodAttendance> list = dao.periodattendance(input);

			for (DTOPeriodAttendance dtopa : list) {
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n", dtopa.getGeuntae_date(), dtopa.getOpencourse_name(),
						dtopa.getStudent_name(), dtopa.getStatus());
			}
			dao.close();
			
			
			adminview.thinLine();
			adminview.pause();

			loop = false;
		} // while

	} // 기간별 출결조회 7 -1 (완)

	@Override
	public void courseattendancesel() {
		boolean loop = true;

		while (loop) {
			
			adminview.flip();
			adminview.thickLine();
			System.out.println();
			System.out.println("[과정번호]\t[개설과정명]");
			System.out.println();

			DAO dao = new DAO();

			ArrayList<DTOCourseAttendanceSelect> list = dao.courseattendanceselect();

			for (DTOCourseAttendanceSelect dtocas : list) {
				System.out.printf("%s\t\t%s\n\n", dtocas.getOpencourse_seq(), dtocas.getOpencourse_name());
			}
			dao.close();

			adminview.thinLine();
			
			adminview.chooseopencourse();
			String sel = scan.nextLine();  //19-01-02
			
			adminview.flip();
			System.out.println();
			System.out.println("[날짜]\t\t\t[과정번호]\t[과정명]\t\t\t\t\t\t\t\t[정상]\t[지각]\t[외출]\t[병가]");
			System.out.println();

			dao = new DAO();

			ArrayList<DTOCourseAttendance> list1 = dao.courseattendance(sel);

			for (DTOCourseAttendance dtoca : list1) {
				System.out.printf("%s\t\t%s\t\t%s\t%s\t%s\t%s\t%s\n\n", dtoca.getGeuntae_date(), dtoca.getCourse_seq(),
						dtoca.getCourse_name(), dtoca.getAttendance(), dtoca.getLate(), dtoca.getGo_out(),
						dtoca.getAbsence());
			}
			dao.close();
			
			adminview.thinLine();
			adminview.pause();

			loop = false;

		} // while

	} // 과정별 출결 조회 -과정선택 - 세부조회 - 7 -2 (완)

	@Override
	public void personalattendance() {
		boolean loop = true;

		while (loop) {
			
			adminview.flip();
			adminview.thinLine();
			System.out.println();
			System.out.println("[학생명]\t\t[전화번호]");
			System.out.println();

			DAO dao = new DAO();

			ArrayList<DTOPersonalAttendance> list = dao.personalattendance();

			for (DTOPersonalAttendance dtopa : list) {
				System.out.printf("%s\t\t\t%s\n", dtopa.getStudent_name(), dtopa.getTel());
			}
			dao.close();

			adminview.thinLine();
			System.out.println();

			adminview.chooseperson();
			String input = scan.nextLine();
			System.out.println();
			
			adminview.flip();
			
			adminview.thinLine();
			System.out.println("[날짜]\t\t\t[과정명]\t\t\t\t\t\t\t\t\t[학생명]\t[근태상황]");
			System.out.println();

			dao = new DAO();

			ArrayList<DTOPersonalAttendanceDetail> list1 = dao.personalattendancedetail(input);

			for (DTOPersonalAttendanceDetail dtopad : list1) {
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n\n", dtopad.getGeuntae_date(), dtopad.getCourse_name(),
						dtopad.getStudent_name(), dtopad.getGeuntae_status());
			}
			dao.close();

			adminview.pause();

			loop = false;

		} // while
	} // 개인별 출결조회 - 학생검색

	@Override
	public void company() {
		
		adminview.flip();
		adminview.title(AdminView.COMPANY);
		
		System.out.println();
		System.out.println("[공고번호]\t[기업명]\t\t\t\t\t[계열_규모]\t[사원수]\t[설립년도]\t[지역]\t\t\t[연봉]\t\t[마감일]\t[요구전공여부]\t[요구과정]");

		DAO dao = new DAO();

		ArrayList<DTOViewCompany> list = dao.list();

		for (DTOViewCompany dto : list) {
			System.out.printf("%s\t\t%s%s\t%s\t\t%s\t\t%s\t\t%s\t%s\t%s\t\t%s\n", dto.getSeq(), dto.getName(), dto.getScale(),
					dto.getEmployee_num(), dto.getEstablished_year(), dto.getArea(), dto.getSalary(), dto.getDeadline(),
					 dto.getMajor(), dto.getCourse());

		}

		adminview.pause();

		adminview.thinLine();
		System.out.println("1 . 기업 공고 작성");
		System.out.println();
		System.out.println("2 . 기업 공고 삭제 ");
		System.out.println();
		System.out.println("3 . 신청학생 관리");
		System.out.println();
		adminview.thinLine();
		System.out.print("선택 : ");
		String sinchung = scan.nextLine();
		

		if (sinchung.equals("1")) {
			
			adminview.thinLine();
			System.out.println();
			System.out.println("기업 공고 작성");

			System.out.print("공고 작성 번호 : ");
			String numberseq = scan.nextLine();

			System.out.print("기업 번호 : ");
			String numbercompany = scan.nextLine();

			System.out.print("연봉 : ");
			String salary = scan.nextLine();

			System.out.print("마감일 : ");
			String dead = scan.nextLine();

			System.out.print("요구과정 : ");
			String course = scan.nextLine();

			System.out.print("전공 여부 :");
			String major = scan.nextLine();

			dao = new DAO();

			DTONotice dto = new DTONotice();

			dto.setSeq(numberseq);
			dto.setCompany_seq(numbercompany);
			dto.setSalary(salary);
			dto.setDeadline(dead);
			dto.setCourse(course);
			dto.setMajor(major);

			int result = dao.addlist(dto);

			dao.close();

			if (result == 1) {
				System.out.println("공고 신청 완료");

			} else {
				System.out.println("공고 신청 실패");
			}

			adminview.pause();

		} else if (sinchung.equals("2")) {

			adminview.thinLine();
			System.out.println();
			System.out.println("기업 공고 삭제");
			System.out.println();
			System.out.print("삭제할 번호: ");
			String seq = scan.nextLine();

			dao = new DAO();

			int result = dao.del(seq);

			if (result == 1) {
				System.out.println("공고 삭제 성공");

			} else {
				System.out.println("공고 삭제 실패");
			}

		} else if (sinchung.equals("3")) {
			
			adminview.flip();
			adminview.thinLine();
			System.out.println();
			System.out.println("신청학생 관리");
			System.out.println();

			System.out.println("[기업번호]\t[기업명]\t\t\t\t\t[연봉]\t\t[마감일]\t\t[요구과정]\t\t\t\t\t\t\t\t[전공여부]\t[학생명]\t[과정명]\t\t\t\t\t\t\t\t[신청일]");

			dao = new DAO();

			ArrayList<DTOvwCheckStudentmanager> list1 = dao.list_1();

			for (DTOvwCheckStudentmanager dto : list1) {

				System.out.printf("%s\t\t%s%s\t%s\t\t%s\t%s\t\t%s\t\t%s\t%s\n", dto.getSeq(), dto.getName1(), dto.getSalary(),
						dto.getDeadline(), dto.getCourse(), dto.getMajor(), dto.getName2(), dto.getName3(),
						dto.getApplication_date());

			}
			



//			dao = new DAO();
//
//			ArrayList<DTOvwCheckStudentmanager> list1 = dao.list_1();
//
//			System.out.println("\t\t==============================================================================================================================================");
////			System.out.println("\t\t[학생명]\t\t[신청일]\t\t\t[기업번호][마감일]\t\t\t[기업명]");
//			System.out.println("\t\t[기업번호]\t[기업명]\t\t\t[마감일]\t\t[신청학생명]\t[신청일]");
//			System.out.println("\t\t----------------------------------------------------------------------------------------------------------------------------------------------");
//
//			for (DTOvwCheckStudentmanager dto : list1) {
//
//				System.out.printf("\t\t%s\t\t%s\t\t%s\t%s\t\t%s\n"
//									, dto.getSeq()
//									, dto.getName1()
//									, dto.getDeadline()
//									, dto.getName2()
//									, dto.getApplication_date()
//
//									);
//
//
//			}
//			
			
			
			
			dao.close();
			adminview.pause();

		} else {
			System.out.println("다시 입력해 주세요.");
		}

	}

	@Override
	public void teachertest() {
		
		adminview.thinLine();
		System.out.println();
		System.out.println("교사평가 조회");
		System.out.println();

		System.out.println("[학생명]\t[선생님명]\t[과정명]\t\t\t\t\t\t\t[과정기간]\t[번호]\t[강의계획서 이행 점수]\t[강의내용 이해 점수]\t[교사 소통점수]\t[강의 유익성 점수]\t[전반적 만족도]\t[건의사항]");
		
		DAO dao = new DAO();

		ArrayList<DTOvwEvaluationTeacher> list1 = dao.list_2();
		
		for (DTOvwEvaluationTeacher dto : list1) {
													
			System.out.printf("%s\t\t%s\t\t%s%s\t\t%s\t%s\t\t\t%s\t\t\t%s\t\t%s\t\t\t%s\t\t%s\n", dto.getName(), dto.getName2(),dto.getName3(), dto.getDate(),
					dto.getSeq(), dto.getPlan_score(), dto.getContent_score(), dto.getCommunication_score(),
					dto.getBenefit_score(), dto.getSatisfaction_score(), dto.getSuggestion());


		}
		dao.close();
		adminview.pause();
		
		
		
	}

	
} // class
