package kr.co.sist.sec.student;

import java.util.Scanner;

import kr.co.sist.sec.main.MainController;
import kr.co.sist.sec.main.View;

public class StudentController {

	private static View view;
	private static StudentView studentView;
	private static IService service;
	private static Scanner scan;
	
	static {
		view = new View();
		studentView = new StudentView();
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

	public boolean student(String seq) {
		
		boolean loop = true;
		while (loop) {
		
			studentView.menu();
			String sel = scan.nextLine();
			
			if (sel.equals("1")) service.score(); // 1. 성적관리
			else if (sel.equals("2")) geuntae(); // 2. 출석관리
			else if (sel.equals("3")) StudentController.board(); // 3. 게시판
			else if (sel.equals("4")) service.notice(); // 4. 기업공고
			else if (sel.equals("5")) service.studentQnA(); // 5. QnA
			else if (sel.equals("6")) StudentController.teacherevaluation(); // 6. 교사평가
			else if (sel.equals("0")) { // 0. 뒤로 돌아가기
				loop = false;
				return false;
			}
			else  view.wrongSel();
			
		}
		return false;
	}
	
	private static void geuntae() { //출결관리
		boolean loop = true;
		while (loop) {

			studentView.geuntaemenu();

			String sel = scan.nextLine();
			
			view.flip();
			
			if (sel.equals("1")) 
				service.geuntae(sel); //금일 출결관리 
			
			else if(sel.equals("2")) //기간별 출결관리
				service.geuntae(sel);
			
			else if(sel.equals("0")) //뒤로가기 
				loop = false;
			
			else loop = false;
			
		}
		
		
	}

	private static void teacherevaluation() { //교사평가
		
		boolean loop = true;
		while (loop) {

			studentView.teacherevaluation();

			String sel = scan.nextLine();

			if (sel.equals("1")) 
				service.teacherevaluation(); //교사평가하기

			else if(sel.equals("0")) //뒤로가기 
				loop = false;
			
			else loop = false;
		}	
	}

	public static void board() { //게시판 
	
		boolean loop = true;
		while (loop) {

			studentView.boardmenu();

			String sel = scan.nextLine();
			
			view.flip();
			
			if (sel.equals("1")) 
				service.boardlist(); //자유게시판 불러오기
			else if (sel.equals("2"))
				service.boardwrite(); //게시글 작성
			else if (sel.equals("3"))
				service.boardedit(); //게시글 수정
			else if (sel.equals("4"))
				service.boarddelete(); //게시글 삭제
			else if (sel.equals("5"))
				loop = false; //뒤로가기 
			
			else 
				loop = false;

		}
	}
	
	
	
	
}
