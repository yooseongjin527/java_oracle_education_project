package kr.co.sist.sec.student;

import java.util.Scanner;

import kr.co.sist.sec.main.View;

public class StudentView {
	
	public final static int SCORE = 1; // 성적조회
	public final static int GEUNTAE = 2; // 출결관리 및 출결 조회
	public final static int BOARDMENU = 3; // 자유게시판
	public final static int NOTICE = 4; // 원하는 기업 찾기
	public final static int QNA = 5; // QnA
	public final static int TEACHEREVALUATION = 6;// 교사평가
	public final static int BOARDLIST = 7; //게시판조회 
	public final static int BOARDWRITE = 8; //게시판작성 
	public final static int BOARDEDIT = 9; //게시판수정
	public final static int BOARDDELETE = 10; //게시판삭제
	

	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}

	public void begin() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\tㆍ교육생 여러분 환영합니다ㆍ");
		System.out.println();
		System.out.println();

	}

	public void end() {
		System.out.println("\t\t\t\t\t\t\t\t\t\tㆍ메인화면으로 돌아갑니다ㆍ");
		;

	}
	
	public void menu() { //교육생메뉴 

		thickLine();
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t\t\t[교육생]");
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 성적조회");
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 출결관리 및 출결 조회");
		System.out.println();
		
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 자유게시판");
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t\t\t4. 원하는 기업 찾기");
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t\t\t5. QnA");
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t\t\t6. 교사평가");
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 종료");
		System.out.println();
		thickLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");
		
	}

	// title
		// 각 업무별 제목 출력
		public void title(int n) {

			if (n == StudentView.SCORE) {
				System.out.println("================================================================================== [성적 조회] =========================================================================================");
			} else if (n == StudentView.GEUNTAE) {
				System.out.println("================================================================================= [출결 관리 및 출결 조회] ========================================================================================");
			} else if (n == StudentView.BOARDMENU) {
				System.out.println("=================================================================================== [자유 게시판] ==========================================================================================");
			} else if (n == StudentView.NOTICE) {
				System.out.println("=================================================================================== [원하는 기업 찾기] ==========================================================================================");
			} else if (n == StudentView.QNA) {
				System.out.println("=================================================================================== [QnA] ==========================================================================================");
			} else if (n == StudentView.TEACHEREVALUATION) {
				System.out.println("=================================================================================== [교사 평가] ==========================================================================================");
			} else if (n == StudentView.BOARDLIST) {
				System.out.println("[게시판조회]");
			} else if (n == StudentView.BOARDWRITE) {
				System.out.println("[게시판작성]");
			} else if (n == StudentView.BOARDEDIT) {
				System.out.println("[게시판수정]");
			} else if (n == StudentView.BOARDDELETE) {
				System.out.println("[게시판삭제]");
			} 

		}

		public void pause() {
			System.out.println("\t\t\t\t\t\t\t\t\t\t계속 하려면 엔터키를 입력하세요.");
			Scanner scan = new Scanner(System.in);
			scan.nextLine();
			
			thinLine();

		}

		public void boardmenu() { //게시판메뉴
			
			thickLine();
			System.out.println();

			System.out.println("\t\t\t\t\t\t\t\t\t\t[게시판]");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t1. 게시판 조회");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t2. 게시글 작성");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t3. 게시글 수정");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t4. 게시글 삭제");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
			System.out.println();
			thickLine();
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");
			
		}
		
		public void teacherevaluation() { //교사평가 창
			
			
			System.out.println();
			
			System.out.println("=================================================================================== [교사 평가] ==========================================================================================");
			
			System.out.println("\t\t\t\t\t\t\t\t\t\t1. 교사평가");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
			System.out.println();
			thickLine();
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");
		}
			
		public void teacherevaluationmenu() {
			
		
			thickLine();
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t교사 평가 항목");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t1. 강의계획서 이행 점수");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t2. 강의내용 전달 및 이해점수");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t3. 교육생과의 소통 점수");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t4. 강의 유익성 점수");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t5. 과정 전체 만족도 점수");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t6. 건의사항");
			thickLine();
			
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t평가를 시작 하시겠습니까? (y/n)");
			
		}
		
		
		public void geuntaemenu() {
			
			flip();
			thickLine();
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t[출결관리]");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t1. 금일 출결관리");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t2. 기간 별 출결관리");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로가기");
			
			System.out.println();
			thickLine();
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");
		}
		

		public void termInput() {
			
		
			System.out.println();
			
			System.out.println("[기간 별 출결관리]");
			System.out.println();
			
			System.out.println("날짜를 OOOO-OO-OO 형식으로 입력해주세요.");
			System.out.println("예) 2018-03-01");
			System.out.println();
			
		}
		
		
		//----------------------------
		public void	QnA() {
			
			System.out.println("                                 [QnA]");
			System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
			System.out.println();
			System.out.println("                                 1. 질문 작성");
			System.out.println();
			System.out.println("                                 2. 질문 수정");
			System.out.println();
			System.out.println("                                 3. 질문 삭제");
			System.out.println();
			System.out.println("                                 4. 교사 답변 조회");
			System.out.println();
			System.out.println("                                 0. 뒤로 돌아가기");
			System.out.println();
			System.out.println("                                 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
			System.out.print("                                 선택 : ");
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
		
		   
		   public void flip() {
			   for (int i=0; i<50; i++) {
				   System.out.println();
			   }
		   }

}







