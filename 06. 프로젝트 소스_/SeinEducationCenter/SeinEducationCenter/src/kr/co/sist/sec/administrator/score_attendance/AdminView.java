package kr.co.sist.sec.administrator.score_attendance;

import java.util.Scanner;



public class AdminView {

	public static final int TESTSCORESELECT = 6;
	public static final int ATTENDANCESELECT = 7;
	public final static int COMPANY = 8;
	public final static int TEACHERTEST = 9;
	private static Scanner scan;

	static {
		scan = new Scanner(System.in);
	}

	public void adminloginmain() {

		thinLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 로그인");
		System.out.println();
		System.out.println("\t\\t\\t\\t\\t\\t\\t\\t\\t\\t2. 아이디찾기");
		System.out.println();
		System.out.println("\t\\t\\t\\t\\t\\t\\t\\t\\t\\t3. 비밀번호찾기");
		System.out.println();
		System.out.println("\t\\t\\t\\t\\t\\t\\t\\t\\t\\t0. 뒤로돌아가기");
		System.out.println();
		thinLine();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택: ");

	}

	public void loginsuccess() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t로그인 되었습니다.");
		System.out.println();
		System.out.println();
	}

	public void loginFail() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t존재하지 않는 계정입니다. 다시 입력해 주세요.");
		System.out.println();
		System.out.println();

	}

	public void title(int n) {
		if (n == AdminView.TESTSCORESELECT) {
			System.out.println("============================================================================== 시험 관리 및 성적 조회 ===============================================================================================");
			System.out.println();
		} else if (n == AdminView.ATTENDANCESELECT) {
			System.out.println("============================================================================== 출결 관리 및 출결 조회 ===============================================================================================");
			System.out.println();
		} else if (n == AdminView.COMPANY) {
			System.out.println("================================================================================ 기업 공고 관리 ==================================================================================================");
			System.out.println();
		} else if (n == AdminView.TEACHERTEST) {
			System.out.println("================================================================================ 교사 평가 관리 ==================================================================================================");
			System.out.println();
		}

	}

	public void pause() {
		
		System.out.println();
		System.out.println();
		System.out.println("계속하시려면 엔터키를 입력해주세요");
		this.scan.nextLine();
		
	} // pause

	public void adminmain() {
		
		flip();
		
		System.out.println(
				"                                                                 ██╗██╗      ██╗██╗                          ██╗██╗      ██╗██╗ \r\n" + 
				"                                                                ██╔╝╚██╗    ██╔╝╚██╗                        ██╔╝╚██╗    ██╔╝╚██╗\r\n" + 
				"                                                                ██║  ██║    ██║  ██║                        ██║  ██║    ██║  ██║\r\n" + 
				"                                                                ██║  ██║    ██║  ██║                        ██║  ██║    ██║  ██║\r\n" + 
				"                                                                ╚██╗██╔╝    ╚██╗██╔╝                        ╚██╗██╔╝    ╚██╗██╔╝\r\n" + 
				"                                                                 ╚═╝╚═╝      ╚═╝╚═╝                          ╚═╝╚═╝      ╚═╝╚═╝ \r\n" + 
				"                                                                                                                                \r\n" + 
				"                                                                 ██╗              ██╗                      ██╗              ██╗ \r\n" + 
				"                                                                ██╔╝              ╚██╗                    ██╔╝              ╚██╗\r\n" + 
				"                                                                ██║                ██║                    ██║                ██║\r\n" + 
				"                                                                ██║                ██║                    ██║                ██║\r\n" + 
				"                                                                ╚██╗    ██╗██╗    ██╔╝                    ╚██╗    ██╗██╗    ██╔╝\r\n" + 
				"                                                                 ╚═╝    ╚═╝╚═╝    ╚═╝                      ╚═╝    ╚═╝╚═╝    ╚═╝ \r\n" + 
				"                                                                                                                                \r\n" + 
				"                                                                 ██╗                ██╗                  ██╗                ██╗ \r\n" + 
				"                                                                ██╔╝                ╚██╗                ██╔╝                ╚██╗\r\n" + 
				"                                                                ██║                  ██║                ██║                  ██║\r\n" + 
				"                                                                ██║                  ██║                ██║                  ██║\r\n" + 
				"                                                                ╚██╗                ██╔╝                ╚██╗                ██╔╝\r\n" + 
				"                                                                 ╚═╝                ╚═╝                  ╚═╝                ╚═╝ \r\n" + 
				"                                                                                                                                ");
		
		

		
		
//		System.out.println("\t\t\t\t\t\t\t\t\t\t[관리자 메뉴]");
		System.out.println();
		thickLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 기초정보관리");
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 교사계정관리");
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 교육생관리");
		System.out.println("\t\t\t\t\t\t\t\t\t\t4. 개설과정관리");
		System.out.println("\t\t\t\t\t\t\t\t\t\t5. 개설과목관리");
		System.out.println("\t\t\t\t\t\t\t\t\t\t6. 시험 관리 및 성적 조회");
		System.out.println("\t\t\t\t\t\t\t\t\t\t7. 출결 관리 및 출결 조회");
		System.out.println("\t\t\t\t\t\t\t\t\t\t8. 기업공고관리");
		System.out.println("\t\t\t\t\t\t\t\t\t\t9. 교사평가관리");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println();
		thickLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택: ");

	}

	public void testscoreselect() {
		
		System.out.println();
		title(AdminView.TESTSCORESELECT);
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 시험 관리");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 성적 조회");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println();
		thickLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택: ");

	}

	public void scoreinquiry() {
		System.out.println();
		System.out.println("=================================================================================== 성적 조회 ==========================================================================================");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 과목별 성적");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 개인별 성적");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");

	}

	public void attendanceselect() {
		
		title(AdminView.ATTENDANCESELECT);
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 기간별 출결 현황");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 과정별 출결 현황");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 개인별 출결 현황");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");
	}

	public void wrongSel() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t잘못 선택하셨습니다. 다시 선택해 주세요.");
		System.out.println();
		System.out.println();
	}

	public void chooseopencourse() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t개설 된 과정 중 하나를 선택해주세요.");
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");
	}

	public void chooseperson() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t검색할 이름을 입력해주세요.");
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");
	}

	public void choosecoursestartdate() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t과정시작일을 입력해 주세요.");
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t입력 : ");
	}

	public void choosesubject() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t과목번호를 입력해 주세요.");
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t입력 : ");
	}

	public void chooseperiod() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t기간을 입력해 주세요.");
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t입력 : ");
		

	}

	public void chooseback() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t뒤로 돌아가려면 0번을 입력해주세요.");
		System.out.println();
		thinLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t입력 : ");
	}

	/**
	    * 굵은 선 출력 메소드
	    */
	   public void thickLine() {
	      System.out.println("==============================================================================================================================================================================================");
	   }
	   
	   
	   
	   /**
	    * 얆은 긴 선 출력 메소드
	    */
	   public void thinLine() {
	      System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	   }
	   
	   
	   public void flip () {
		   
		   for (int i=0; i<50; i++) {
			   System.out.println();
		   }
	   }
	
} // class
