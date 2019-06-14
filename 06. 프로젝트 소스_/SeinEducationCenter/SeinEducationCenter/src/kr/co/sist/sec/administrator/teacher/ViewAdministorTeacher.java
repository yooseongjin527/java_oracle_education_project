package kr.co.sist.sec.administrator.teacher;

import java.util.Scanner;

public class ViewAdministorTeacher {
	
	// title()에서 사용
	public final static int ADD = 101;
	public final static int LIST = 102;
	public final static int EDIT = 103;
	public final static int DEL = 104;
	public final static int SEARCH = 105;
	
	// columnName()에서 사용
	public final static int TEACHER = 201;
	public final static int ALLTEACHER = 202;
	public final static int SELECTEDTEACHER = 203;
	public final static int TEACHERALLOCATED = 204;
	public final static int COURSEDATE = 205;
	public final static int SUBJECTINFO = 206;
	
	

	/**
	 * [관리자-교사관리] 시작화면 출력 메소드
	 */
	public void begin() {

		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t교사 관리로 이동합니다.");
		
	}

	
	/**
	 * [관리자-교사관리] 관리 종료화면 출력 메소드
	 */
	public void end() {
		
		System.out.println("\t\t\t\t\t\t\t\t\t\t교사 관리를 종료합니다.");
		
	}
	
	
	/**
	 * [관리자-교사관리] 화면 일시정지 메소드
	 */
	public void pause() {
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t계속 하시려면 ENTER를 입력하세요.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine().trim();
		
	}

	
	/**
	 * [관리자-교사관리] 화면 넘기기 메소드
	 */
	public void flip() {
		
		for (int i = 0; i < 50; i++) {
			
			System.out.println();
		}
		
	}


	/**
	 * [관리자-교사생관리] 관리 메뉴 출력 메소드
	 */
	public void menu() {

		flip();
		thickLine();
		System.out.println();
		System.out.println( 
						"\t\t\t\t\t\t████████╗███████╗ █████╗  ██████╗██╗  ██╗███████╗██████╗     ██╗███╗   ██╗███████╗ ██████╗ \r\n" + 
						"\t\t\t\t\t\t╚══██╔══╝██╔════╝██╔══██╗██╔════╝██║  ██║██╔════╝██╔══██╗    ██║████╗  ██║██╔════╝██╔═══██╗\r\n" + 
						"\t\t\t\t\t\t   ██║   █████╗  ███████║██║     ███████║█████╗  ██████╔╝    ██║██╔██╗ ██║█████╗  ██║   ██║\r\n" + 
						"\t\t\t\t\t\t   ██║   ██╔══╝  ██╔══██║██║     ██╔══██║██╔══╝  ██╔══██╗    ██║██║╚██╗██║██╔══╝  ██║   ██║\r\n" + 
						"\t\t\t\t\t\t   ██║   ███████╗██║  ██║╚██████╗██║  ██║███████╗██║  ██║    ██║██║ ╚████║██║     ╚██████╔╝\r\n" + 
						"\t\t\t\t\t\t   ╚═╝   ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝    ╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝");
		System.out.println();
		thickLine();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 교사 정보 등록");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 교사 정보 조회");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 교사 정보 수정");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t4. 교사 정보 삭제");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		
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
	 * [관리자-교사관리] 선택 화면 출력 메소드
	 */
	public void select() {

		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");	
		
	}
	
	
	/**
	 * [관리자-교사관리] 입력 화면 출력 메소드
	 */
	public void input() {
		
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t입력 : ");	
		
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * [관리자-교사관리] 타이틀 출력 메소드
	 * @param 타이틀 번호
	 */
	public void title(int n) {

		if (n == ViewAdministorTeacher.ADD) {
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
		
		} else if (n == ViewAdministorTeacher.LIST) {
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
		
		} else if (n == ViewAdministorTeacher.EDIT) {
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
		
		} else if (n == ViewAdministorTeacher.DEL) {
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
		
		} else if (n == ViewAdministorTeacher.SEARCH) {
			flip();
			thickLine();
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t[ 교사 정보 검색 ]");
			System.out.println();
			thickLine();
			
		}
	}


	/**
	 * [관리자-교사관리] 컬럼명 출력 메소드
	 * @param 컬럼 이름 번호
	 */
	public void columnName(int n) {
		
		if (n == ViewAdministorTeacher.TEACHER) {
			
			System.out.println("\t\t\t\t\t\t\t[번호]\t\t[이름]\t\t[전화번호]\t\t[주민번호뒷자리]");
			
		} else if (n == ViewAdministorTeacher.TEACHERALLOCATED) {
			
			System.out.println("\t\t\t\t\t[번호]\t[이름]\t\t\t[과정명]");
			
		} else if (n == ViewAdministorTeacher.COURSEDATE) {
			
			System.out.println("\t\t\t\t\t[과정시작일]\t\t[과정종료일]\t\t[강의실]");
			
		} else if (n == ViewAdministorTeacher.SUBJECTINFO) {
			
			System.out.println("\t\t\t\t\t[과목시작일]\t[과목종료일]\t[과목명]\t\t\t\t[교재명]");
			
		}
		
	}


	/**
	 * 교사 정보 수정 시 확인 메시지 출력
	 */
	public void editCheck() {

		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 해당 교사 정보 수정");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 재검색");
		
	}


	/**
	 * 교사 정보 삭제 시 확인 메시지 출력
	 */
	public void delCheck() {

		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 해당 교사 정보 삭제");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 재검색");
		
	}


	/**
	 * 교사 조회 메뉴 출력 메소드
	 */
	public void readMenu() {
		
		flip();
		thickLine();
		System.out.println();
		title(ViewAdministorTeacher.LIST);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 전체 교사 보기");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 특정 교사 검색");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		
	}


	public void askSelectTeacher() {

		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 특정 교사 세부 정보 조회");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		
	}
	
	
	
	
}
