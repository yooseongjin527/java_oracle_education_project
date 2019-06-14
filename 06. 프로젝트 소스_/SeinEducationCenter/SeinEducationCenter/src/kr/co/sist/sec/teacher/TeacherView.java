package kr.co.sist.sec.teacher;

import java.util.Scanner;

public class TeacherView {

private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}

	public void teacherMenu() {
		System.out.println();
		System.out.println();
		System.out.println("              █████████████████████████████████████████████████████████");
		System.out.println();
		System.out.println("              ████████╗███████╗ █████╗  ██████╗██╗  ██╗███████╗██████╗ \r\n" + 
				"              ╚══██╔══╝██╔════╝██╔══██╗██╔════╝██║  ██║██╔════╝██╔══██╗\r\n" + 
				"                 ██║   █████╗  ███████║██║     ███████║█████╗  ██████╔╝\r\n" + 
				"                 ██║   ██╔══╝  ██╔══██║██║     ██╔══██║██╔══╝  ██╔══██╗\r\n" + 
				"                 ██║   ███████╗██║  ██║╚██████╗██║  ██║███████╗██║  ██║\r\n" + 
				"                 ╚═╝   ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\r\n" + 
				"                                                         ");
		System.out.println("              █████████████████████████████████████████████████████████");
		
		System.out.println();
		System.out.println("                              [교사 메뉴]");
		System.out.println("                              ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println();
		System.out.println("                              1. 강의스케줄 조회");
		System.out.println();
		System.out.println("                              2. 출결관리 및 출결조회");
		System.out.println();
		System.out.println("                              3. 배점 관리");
		System.out.println();
		System.out.println("                              4. 성적 관리");
		System.out.println();
		System.out.println("                              5. QnA 관리");
		System.out.println();
		System.out.println("                              6. 교사 평가 조회");
		System.out.println();
		System.out.println("                              0. 뒤로 돌아가기");
		System.out.println();
		System.out.println("                              ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                              선택 : ");
	}
	
	public void teacherTask() {
		
		
	}
	
	
	//각 업무별 제목 출력
//	public void title(int n) {
//		if (n == View_teacher.LOGIN) { System.out.println("[메모 입력하기]");
//		} else if (n == View_teacher.IDFIND) { System.out.println("[메모 목록보기]");
//		} else if (n == View_teacher.PWFIND) { System.out.println("[메모 수정하기]");
//		} else if (n == View_teacher.BACK) { System.out.println("[메모 삭제하기]");
//		} else if (n == View_teacher.END) {	System.out.println("[메모 검색하기]");
//		}
//		
//	}

	public void pause() {
		System.out.println();
		System.out.println("계속하려면 엔터를 입력하세요.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
	}
	
	public void back() {
		System.out.println();
		System.out.println("뒤로 돌아가려면 엔터를 입력하세요.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
	}
	
	
	public void wrongSel() {
		System.out.println();
		System.out.println("              잘못 입력하셨습니다. 다시 입력해주세요.");
		
	}
	
	public void end() {
		System.out.println();
		System.out.println("              세인교육센터 프로그램을 종료합니다.");
	}
	
	public void loginFail() {
		System.out.println("[로그인에 실패했습니다. 다시 입력해주세요.]");
		
	}

	public void course() {

	    System.out.println("==============================================================================================================================================================================================");
		System.out.println("교사님이 맡고 있는 과정 목록");
	    System.out.println("==============================================================================================================================================================================================");
		System.out.println("[과정번호]\t[과정명]\t\t\t\t\t[과정기간]");
		System.out.println("==============================================================================================================================================================================================");

	}
	
	public void allot() {
		
		System.out.println("                                 [배점 관리]");
		System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println();
		System.out.println("                                 1. 배점 조회");
		System.out.println();
		System.out.println("                                 2. 배점 입력");
		System.out.println();
		System.out.println("                                 3. 배점 수정");
		System.out.println();
		System.out.println("                                 4. 시험 등록");
		System.out.println();
		System.out.println("                                 5. 뒤로 돌아가기");
		System.out.println();
		System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                 선택 : ");
	}
	
	public void teacherEvaluation() {
		
		System.out.println("                                 [교사평가조회]");
		System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println();
		System.out.println("                                 1. 교사평가 조회");
		System.out.println();
		System.out.println("                                 2. 뒤로 돌아가기");
		System.out.println();
		System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                 선택 : ");
	}
	
	public void	score() {
		
		System.out.println("                                 [성적 관리]");
		System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println();
		System.out.println("                                 1. 성적 조회");
		System.out.println();
		System.out.println("                                 2. 성적 입력");
		System.out.println();
		System.out.println("                                 3. 성적 수정");
		System.out.println();
		System.out.println("                                 4. 뒤로 돌아가기");
		System.out.println();
		System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                 선택 : ");
	}
	
	
	public void test_add() {
		System.out.println("                                 [시험 등록]");
		System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println();
		System.out.println("                                 1. 필기 시험(문제, 날짜)");
		System.out.println();
		System.out.println("                                 2. 실기 시험(문제, 날짜)");
		System.out.println();
		System.out.println("                                 3. 뒤로 돌아가기");
		System.out.println();
		System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                 선택 : ");
	}

	public void qna() {
		System.out.println("                                 [QnA 관리]");
		System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println();
		System.out.println("                                 1. 질문 조회하기");
		System.out.println();
		System.out.println("                                 2. 질문 답변하기");
		System.out.println();
		System.out.println("                                 3. 답변 조회하기");
		System.out.println();
		System.out.println("                                 4. 답변 수정하기");
		System.out.println();
		System.out.println("                                 5. 답변 삭제하기");
		System.out.println();
		System.out.println("                                 6. 뒤로 돌아가기");
		System.out.println();
		System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                 선택 : ");
		
	}
	
	public void studysearch() {
		
		System.out.println();
		System.out.println();
		System.out.println("                              [강의스케줄 조회]");
		System.out.println("                              ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println();
		System.out.println("                              1. 교사 이름으로 조회");
		System.out.println();
		System.out.println("                              2. 특정 과정 선택 "); // 특정 과정 선택시 과정듣는 교육생 정보 추력
		System.out.println();
		System.out.println("                              ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                              선택 : ");
		
	}
	public void attendanceManagement() {
		
		System.out.println();
		System.out.println();
		System.out.println("                              [출결 관리 및 조회]");
		System.out.println("                              ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println();
		System.out.println("                              1. 날짜 검색");
		System.out.println();
		System.out.println("                              2. 과정 검색");
		System.out.println();
		System.out.println("                              3. 이름 검색");
		System.out.println();
		System.out.println("                              ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                              선택 : ");
		
	}
	
	   /**
	    * 굵은 선 출력 메소드
	    */
	   public void thickLine() {
	      
		   System.out.println("==================================================================================================================================================================================================================");
	   }

	   
	   /**
	    * 얆은 긴 선 출력 메소드
	    */
	   public void thinLine() {
	      System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	   }
}







