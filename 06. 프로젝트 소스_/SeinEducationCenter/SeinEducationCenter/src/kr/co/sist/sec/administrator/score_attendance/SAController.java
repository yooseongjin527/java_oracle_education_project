package kr.co.sist.sec.administrator.score_attendance;

import java.util.Scanner;

import kr.co.sist.sec.administrator.student.MainControllerAdministorStudent;
import kr.co.sist.sec.administrator.teacher.MainControllerAdministorTeacher;

public class SAController {

	private static AdminView adminview;
	private static IService service;
	private static Scanner scan;

	static {
		adminview = new AdminView();
		service = new AdminService();
		scan = new Scanner(System.in);
	}

//	public void main()  {
//		
//		adminview.flip();
//		adminview.loginsuccess();
//		// 관리자 메뉴 연결 메소드 여기에 놓기!!
//		AdminView view = new AdminView();
//
//		MainControllerAdministorTeacher teacher = new MainControllerAdministorTeacher();
//		MainControllerAdministorStudent student = new MainControllerAdministorStudent();
//		
//		
//		boolean loop = true;
//
//		while (loop) {
//
//			view.adminmain();
//			String sel = scan.nextLine();
//
////		if (sel.equals("1")) adminview.basicinfomanage(1);
//		if (sel.equals("2")) teacher.teacherMain();
//		else if (sel.equals("3")) student.studentMain();
////		else if (sel.equals("4")) 
////		else if (sel.equals("5"))
//		else if (sel.equals("6")) testscoreselect(); // 시험관리및성적조회
//		else if (sel.equals("7")) attendanceselect(); // 출결관리 및 출결조회
//		else if (sel.equals("8")) service.company();
//		else if (sel.equals("9")) service.teachertest();
////		else if (sel.equals("0")) loop = false;
//			else 
//				view.wrongSel();
//			
//		}
//
//	}

	public void testscoreselect() {
		
		adminview.flip();
		
		boolean loop = true;

		while (loop) {
			
			adminview.flip();
			adminview.testscoreselect();
			String sel = scan.nextLine();

			if (sel.equals("1")) {
				service.testmanage(); // 시험관리
			} else if (sel.equals("2")) {
				scoreselect();

			} else if (sel.equals("0")) {
				loop = false;
			}
		} // while
	} // testscoreselect

	
	private void scoreselect() {

		boolean loop = true;

		while (loop) {
			
			adminview.flip();
			adminview.scoreinquiry(); // 성적조회
			
			String input = scan.nextLine();
			
			
			if (input.equals("1")) {
				service.subjectselect(); // 과목별 성적
			} else if (input.equals("2")) {
				service.personalscodetail(); // 개인별 성적
			} else if (input.equals("0")) {
				loop = false;
			}
			
		} // while		
	} // scoreselect

	
	
	public void attendanceselect() {


		boolean loop = true;

		while (loop) {
			
			adminview.flip();
			adminview.attendanceselect();
			String sel = scan.nextLine();

			if (sel.equals("1")) {
				// adminview.기간입력하는뷰
				service.periattendance(); // 기간별 출결조회
			} else if (sel.equals("2")) {
				service.courseattendancesel(); // 과정선택
			} else if (sel.equals("3")) {
				service.personalattendance(); // 개인별 출결조회 - 학생검색
			} else if (sel.equals("0")) {
				loop = false;
			}
		}
	} // attendanceselect
}
