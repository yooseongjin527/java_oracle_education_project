package kr.co.sist.sec.administrator.student;

import java.util.Scanner;


public class ViewAdministorStudent {
	
	// title()에서 사용
	public final static int ADD = 101;
	public final static int LIST = 102;
	public final static int EDIT = 103;
	public final static int DEL = 104;
	public final static int SEARCH = 105;
	
	// columnName()에서 사용
	public final static int STUDENT = 201;
	public final static int ALLSTUDENT = 202;
	public static final int SELECTEDSTUDENT = 203;

	
	/**
	 * [관리자-교육생관리] 화면 일시정지 메소드
	 */
	public void pause() {
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t계속 하시려면 ENTER를 입력하세요.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine().trim();
		
	}
	
	
	/**
	 * [관리자-교육생관리] 화면 넘기기 메소드
	 */
	public void flip() {
		
		for (int i = 0; i < 50; i++) {
			
			System.out.println();
		}
		
	}
	

	/**
	 * [관리자-교육생관리] 시작화면 출력 메소드
	 */
	public void begin() {
		
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t교육생 관리로 이동합니다.");
		
	}
	
	
	/**
	 * [관리자-교육생관리] 관리 종료화면 출력 메소드
	 */
	public void end() {
		
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t교육생 관리를 종료합니다.");
		
	}		
	
	
	/**
	 * [관리자-교육생관리] 관리 메뉴 출력 메소드
	 */
	public void menu() {
		
		flip();
		thickLine();
		System.out.println();
		System.out.println(
						"\t\t\t\t\t\t███████╗████████╗██╗   ██╗██████╗ ███████╗███╗   ██╗████████╗    ██╗███╗   ██╗███████╗ ██████╗ \r\n" + 
						"\t\t\t\t\t\t██╔════╝╚══██╔══╝██║   ██║██╔══██╗██╔════╝████╗  ██║╚══██╔══╝    ██║████╗  ██║██╔════╝██╔═══██╗\r\n" + 
						"\t\t\t\t\t\t███████╗   ██║   ██║   ██║██║  ██║█████╗  ██╔██╗ ██║   ██║       ██║██╔██╗ ██║█████╗  ██║   ██║\r\n" + 
						"\t\t\t\t\t\t╚════██║   ██║   ██║   ██║██║  ██║██╔══╝  ██║╚██╗██║   ██║       ██║██║╚██╗██║██╔══╝  ██║   ██║\r\n" + 
						"\t\t\t\t\t\t███████║   ██║   ╚██████╔╝██████╔╝███████╗██║ ╚████║   ██║       ██║██║ ╚████║██║     ╚██████╔╝\r\n" + 
						"\t\t\t\t\t\t╚══════╝   ╚═╝    ╚═════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝   ╚═╝       ╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝");
		System.out.println();
		thickLine();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 교육생 정보 등록");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 교육생 정보 조회");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 교육생 정보 수정");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t4. 교육생 정보 삭제");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		
	}
	
	
	/**
	 * [관리자-교육생관리] 선택 화면 출력 메소드
	 */
	public void select() {
		
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");		
		
	}
	
	
	
	/**
	 * [관리자-교육생관리] 입력 화면 출력 메소드
	 */
	public void input() {
		
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t입력 : ");	
		
	}
	

	
	/**
	 * [관리자-교육생관리] 타이틀 출력 메소드
	 * @param 타이틀 번호
	 */
	public void title(int n) {

		if (n == ViewAdministorStudent.ADD) {
			flip();
			thickLine();
			System.out.println();
			System.out.println(
					"\t\t\t\t\t\t██████╗ ███████╗ ██████╗ ██╗███████╗████████╗██████╗  █████╗ ████████╗██╗ ██████╗ ███╗   ██╗\r\n" + 
					"\t\t\t\t\t\t██╔══██╗██╔════╝██╔════╝ ██║██╔════╝╚══██╔══╝██╔══██╗██╔══██╗╚══██╔══╝██║██╔═══██╗████╗  ██║\r\n" + 
					"\t\t\t\t\t\t██████╔╝█████╗  ██║  ███╗██║███████╗   ██║   ██████╔╝███████║   ██║   ██║██║   ██║██╔██╗ ██║\r\n" + 
					"\t\t\t\t\t\t██╔══██╗██╔══╝  ██║   ██║██║╚════██║   ██║   ██╔══██╗██╔══██║   ██║   ██║██║   ██║██║╚██╗██║\r\n" + 
					"\t\t\t\t\t\t██║  ██║███████╗╚██████╔╝██║███████║   ██║   ██║  ██║██║  ██║   ██║   ██║╚██████╔╝██║ ╚████║\r\n" + 
					"\t\t\t\t\t\t╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝");
			System.out.println();
			thickLine();
		
		} else if (n == ViewAdministorStudent.LIST) {
			flip();
			thickLine();
			System.out.println();
			System.out.println(
					"\t\t\t\t\t\t\t\t██╗███╗   ██╗███████╗ ██████╗     ██╗     ██╗███████╗████████╗\r\n" + 
					"\t\t\t\t\t\t\t\t██║████╗  ██║██╔════╝██╔═══██╗    ██║     ██║██╔════╝╚══██╔══╝\r\n" + 
					"\t\t\t\t\t\t\t\t██║██╔██╗ ██║█████╗  ██║   ██║    ██║     ██║███████╗   ██║   \r\n" + 
					"\t\t\t\t\t\t\t\t██║██║╚██╗██║██╔══╝  ██║   ██║    ██║     ██║╚════██║   ██║   \r\n" + 
					"\t\t\t\t\t\t\t\t██║██║ ╚████║██║     ╚██████╔╝    ███████╗██║███████║   ██║   \r\n" + 
					"\t\t\t\t\t\t\t\t╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝     ╚══════╝╚═╝╚══════╝   ╚═╝  ");
			System.out.println();
			thickLine();
		
		} else if (n == ViewAdministorStudent.EDIT) {
			flip();
			thickLine();
			System.out.println();
			System.out.println(
					"\t\t\t\t\t\t\t\t██╗███╗   ██╗███████╗ ██████╗     ███████╗██████╗ ██╗████████╗\r\n" + 
					"\t\t\t\t\t\t\t\t██║████╗  ██║██╔════╝██╔═══██╗    ██╔════╝██╔══██╗██║╚══██╔══╝\r\n" + 
					"\t\t\t\t\t\t\t\t██║██╔██╗ ██║█████╗  ██║   ██║    █████╗  ██║  ██║██║   ██║   \r\n" + 
					"\t\t\t\t\t\t\t\t██║██║╚██╗██║██╔══╝  ██║   ██║    ██╔══╝  ██║  ██║██║   ██║   \r\n" + 
					"\t\t\t\t\t\t\t\t██║██║ ╚████║██║     ╚██████╔╝    ███████╗██████╔╝██║   ██║   \r\n" + 
					"\t\t\t\t\t\t\t\t╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝     ╚══════╝╚═════╝ ╚═╝   ╚═╝   ");
			System.out.println();
			thickLine();
		
		} else if (n == ViewAdministorStudent.DEL) {
			flip();
			thickLine();
			System.out.println();
			System.out.println(
					"\t\t\t\t\t\t\t██╗███╗   ██╗███████╗ ██████╗     ██████╗ ███████╗██╗     ███████╗████████╗███████╗\r\n" + 
					"\t\t\t\t\t\t\t██║████╗  ██║██╔════╝██╔═══██╗    ██╔══██╗██╔════╝██║     ██╔════╝╚══██╔══╝██╔════╝\r\n" + 
					"\t\t\t\t\t\t\t██║██╔██╗ ██║█████╗  ██║   ██║    ██║  ██║█████╗  ██║     █████╗     ██║   █████╗  \r\n" + 
					"\t\t\t\t\t\t\t██║██║╚██╗██║██╔══╝  ██║   ██║    ██║  ██║██╔══╝  ██║     ██╔══╝     ██║   ██╔══╝  \r\n" + 
					"\t\t\t\t\t\t\t██║██║ ╚████║██║     ╚██████╔╝    ██████╔╝███████╗███████╗███████╗   ██║   ███████╗\r\n" + 
					"\t\t\t\t\t\t\t╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝     ╚═════╝ ╚══════╝╚══════╝╚══════╝   ╚═╝   ╚══════╝");
			System.out.println();
			thickLine();
		
		} else if (n == ViewAdministorStudent.SEARCH) {
			flip();
			thickLine();
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t[ 교육생 정보 검색 ]");
			System.out.println();
			thickLine();
			
		}
	}


	/**
	 * [관리자-교육생관리] 컬럼명 출력 메소드
	 * @param 컬럼 이름 번호
	 */
	public void columnName(int n) {
		
		if (n == ViewAdministorStudent.ALLSTUDENT) {
			
			System.out.println("\t\t\t\t\t[번호]\t\t[이름]\t\t[전공여부]\t\t[전화번호]\t\t\t[주민번호뒷자리]");
			
		} else if (n == ViewAdministorStudent.SELECTEDSTUDENT) {
			
			System.out.println("\t\t\t\t\t[수강상태]\t[과정시작일]\t[과정종료일]\t[강의실]\t\t[과정명]");
		
		} else if (n == ViewAdministorStudent.STUDENT) {
			
			System.out.println("\t\t\t\t\t[번호]\t\t[이름]\t\t[전공여부]\t\t[전화번호]\t\t[등록일]\t\t\t[주민번호뒷자리]");
			
		} else if (n == ViewAdministorStudent.SEARCH) {
			
			//TODO ?
		}
				
		
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

	
	/**
	 * 얇은 짧은 선 출력 메소드
	 */
	public void thinShortLine() {
		System.out.println("------------------------------------------");
	}
	
	
	/**
	 * 교육생 조회 메뉴 출력 메소드
	 */
	public void readMenu() {
		
		flip();
		thickLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[ 교육생 정보 조회 ]");
		System.out.println();
		thickLine();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 전체 교육생 보기");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 특정 교육생 검색");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");

	}

	
	/**
	 * 교육생 정보 수정 시 확인 메시지 출력
	 */
	public void editCheck() {

		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 해당 교육생 정보 수정");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 재검색");
		
	}

	
	/**
	 * 교육생 정보 삭제 시 확인 메시지 출력
	 */
	public void delCheck() {

		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 해당 교육생 정보 삭제");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 재검색");
		
	}

	
	/**
	 * 세부 정보 조회 확인 메시지 출력
	 */
	public void askSelectStudent() {
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 특정 교육생 세부 정보 조회");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		
	}


	/**
	 * 잘못된 번호 입력 시 알림 메시지 출력
	 * @throws InterruptedException 
	 */
	public void wrongNumber() {
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[!] 보기에 있는 숫자만 입력해주세요.");
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}


}
