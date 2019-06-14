package kr.co.sist.sec.teacher;

import java.util.Scanner;

import kr.co.sist.sec.main.View;

public class TeacherController {

	private static View view;
	private static TeacherView teacherView;
	private static IService service;
	private static Scanner scan;
	
	static {
		view = new View();
		teacherView = new TeacherView();
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

	public boolean teacher(String seq) {
		
		boolean loop = true;
		while (loop) {
		
			teacherView.teacherMenu();
			String sel = scan.nextLine();
			
			if (sel.equals("1")) service.studySearch(); // 1. 강의스케줄 조회
			else if (sel.equals("2")) service.attendanceManagement(); // 2. 출결 관리 및 출결 조회
			else if (sel.equals("3")) service.Allot(seq); // 3. 배점 관리
			else if (sel.equals("4")) service.Score(seq); // 4. 성적 관리
			else if (sel.equals("5")) service.QnA(seq); // 5. QnA 관리
			else if (sel.equals("6")) service.TeacherEvaluation(seq); // 6. 교사 평가 조회
			else if (sel.equals("0")) { // 0. 뒤로 돌아가기
				loop = false;
				return false;
			}
			else  view.wrongSel();
			
		}
		return true;
	}
	
}
