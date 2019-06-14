package kr.co.sist.sec.administrator;

import java.util.Scanner;

import kr.co.sist.sec.administrator.basicinfo.classroom.ClassroomService;
import kr.co.sist.sec.administrator.basicinfo.classroom.IClassroomService;
import kr.co.sist.sec.administrator.basicinfo.course.CourseService;
import kr.co.sist.sec.administrator.basicinfo.course.ICourseService;
import kr.co.sist.sec.administrator.basicinfo.subject.ISubjectService;
import kr.co.sist.sec.administrator.basicinfo.subject.SubjectService;
import kr.co.sist.sec.administrator.basicinfo.textbook.ITextbookService;
import kr.co.sist.sec.administrator.basicinfo.textbook.TextbookService;
import kr.co.sist.sec.administrator.coursesubject.CSController;
import kr.co.sist.sec.administrator.opencourse.OCController;
import kr.co.sist.sec.administrator.score_attendance.AdminService;
import kr.co.sist.sec.administrator.score_attendance.SAController;
import kr.co.sist.sec.administrator.student.MainControllerAdministorStudent;
import kr.co.sist.sec.administrator.subjecttextbook.STController;
import kr.co.sist.sec.administrator.teacher.MainControllerAdministorTeacher;
import kr.co.sist.sec.main.View;

public class AdminController {

	private static View view;
	private static AdminView adminView;
	private static IService service;
	private static Scanner scan;
	
	static {
		view = new View();
		adminView = new AdminView();
		service = new Service();
		scan = new Scanner(System.in);
	}
	
	public void main(int n) {
		
		boolean loop = true;
		while (loop) {
			view.loginMenu(n);
			String sel = scan.nextLine();
			
			if (sel.equals("1")) service.login(n); // 1. 로그인
			else if (sel.equals("2")) service.findId(view.FINDID); // 2. 아이디 찾기
			else if (sel.equals("3")) service.findPw(view.FINDPW); // 3. 비밀번호 찾기
			else if (sel.equals("0")) loop = false; // 0. 뒤로 돌아가기
			else view.wrongSel();
		
		}
	}

	public boolean admin() {
		
		boolean loop = true;
		while (loop) {
			adminView.adminMenu();
			String sel = scan.nextLine();
			
			MainControllerAdministorTeacher teacher = new MainControllerAdministorTeacher(); // 2. 교사 계정 관리
			MainControllerAdministorStudent student = new MainControllerAdministorStudent(); // 3. 교육생 계정 관리
			CSController cscontroller = new CSController(); // 4. 과정별 과목 관리
			STController stcontroller = new STController(); // 5. 과목별 교재 관리
			OCController occontroller = new OCController(); // 6. 개설 과정 관리1
			SAController amc = new SAController(); // 7. 시험 관리 및 성적 조회 // 8. 출결 관리 및 출결 조회
			AdminService as = new AdminService(); // 9. 교사평가관리 // 10. 기업공고관리
			
			if (sel.equals("1")) basicInfoManage(); // 1. 기초 정보 관리
			else if (sel.equals("2")) teacher.teacherMain();// 2. 교사 계정 관리
			else if (sel.equals("3")) student.studentMain();// 3. 교육생 계정 관리
			else if (sel.equals("4")) cscontroller.coursesubject();// 4. 과정별 과목 관리
			else if (sel.equals("5")) stcontroller.subjecttextbook(); // 5. 과목별 교재 관리
			else if (sel.equals("6")) occontroller.opencourse(); // 6. 개설 과정 관리
			else if (sel.equals("7")) amc.testscoreselect(); // 7. 시험 관리 및 성적 조회
			else if (sel.equals("8")) amc.attendanceselect(); // 8. 출결 관리 및 출결 조회
			else if (sel.equals("9")) as.teachertest(); // 9. 교사평가관리
			else if (sel.equals("10")) as.company(); // 10. 기업공고관리
			else if (sel.equals("0")) { // 0. 뒤로 돌아가기
				loop = false;
				return false;
			}
			else  view.wrongSel();
			
		}
		return true;
	}

	public void basicInfoManage() {

		boolean loop = true;
		while (loop) {
			
			adminView.basicInfoManageMenu();
			String sel = scan.nextLine();
			
			if (sel.equals("1")) course(); // 1. 과정
			else if (sel.equals("2")) subject(); // 2. 과목
			else if (sel.equals("3")) classroom(); // 3. 강의실
			else if (sel.equals("4")) textbook(); // 4. 교재
			else if (sel.equals("0")) loop = false;// 0. 뒤로 돌아가기
			else  view.wrongSel();
		}
	}


	public void course() {

		boolean loop = true;
		while (loop) {
			view.flip();
			view.thickLine();
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t[과정]");
			System.out.println();
			System.out.println();
			ICourseService courseService = new CourseService();
			courseService.list();
			System.out.println();
			System.out.println();
			adminView.courseCRUDMenu();
			String sel = scan.nextLine();
			
			if (sel.equals("1")) courseService.search(); // 1. 과정 검색
			else if (sel.equals("2")) courseService.add(); // 2. 과정 등록
			else if (sel.equals("3")) courseService.edit(); // 3. 과정명 수정
			else if (sel.equals("4")) courseService.del(); // 4. 과정 삭제
			else if (sel.equals("0")) loop = false;// 0. 뒤로 돌아가기
			else  view.wrongSel();
		}
		
	}
	
	
	public void subject() {
		
		boolean loop = true;
		while (loop) {
			
			ISubjectService subjectService = new SubjectService();
			subjectService.list();
			
			adminView.subjectCRUDMenu();
			String sel = scan.nextLine();
			
			if (sel.equals("1")) subjectService.search(); // 1. 과목 검색
			else if (sel.equals("2")) subjectService.add(); // 2. 과목 등록
			else if (sel.equals("3")) subjectService.edit(); // 3. 과목 정보 수정
			else if (sel.equals("4")) subjectService.del(); // 4. 과목 삭제
			else if (sel.equals("0")) loop = false;// 0. 뒤로 돌아가기
			else  view.wrongSel();
		}
		
	}
	
	public void classroom() {

		boolean loop = true;
		while (loop) {
			
			IClassroomService classroomService = new ClassroomService();
			classroomService.list();
			
			adminView.classroomCRUDMenu();
			String sel = scan.nextLine();
			
			if (sel.equals("1")) classroomService.search(); // 1. 강의실 검색
			else if (sel.equals("2")) classroomService.add(); // 2. 강의실 등록
			else if (sel.equals("3")) classroomService.del(); // 3. 강의실 삭제
			else if (sel.equals("0")) loop = false;// 0. 뒤로 돌아가기
			else  view.wrongSel();
		}
		
	}

	public void textbook() {
		
		boolean loop = true;
		while (loop) {
			
			ITextbookService textbookService = new TextbookService();
			textbookService.list();
			
			adminView.textbookCRUDMenu();
			String sel = scan.nextLine();
			
			if (sel.equals("1")) textbookService.search(); // 1. 교재 검색
			else if (sel.equals("2")) textbookService.add(); // 2. 교재 등록
			else if (sel.equals("3")) textbookService.edit(); // 3. 교재 정보 수정
			else if (sel.equals("4")) textbookService.del(); // 4. 교재  삭제
			else if (sel.equals("0")) loop = false;// 0. 뒤로 돌아가기
			else  view.wrongSel();
		}
		
	}
	
	
}
