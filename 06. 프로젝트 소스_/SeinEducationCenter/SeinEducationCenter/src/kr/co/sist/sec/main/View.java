package kr.co.sist.sec.main;

import java.util.Scanner;

public class View {
	
	public final static int ADMIN = 1;
	public final static int TEACHER = 2;
	public final static int STUDNET = 3;
	public final static int EXIT = 0;
	
	public final static int FINDID = 2;
	public final static int FINDPW = 3;
	
	public void begin() {
		System.out.println();
		System.out.println("\t\t\t\t\t        ████████████████████████████████████████████████████████████████████████");        
		System.out.println();
		System.out.println(
				"\t\t\t\t\t                             ███████╗███████╗██╗███╗   ██╗                       \r\n" + 
				"\t\t\t\t\t                             ██╔════╝██╔════╝██║████╗  ██║                       \r\n" + 
				"\t\t\t\t\t                             ███████╗█████╗  ██║██╔██╗ ██║                       \r\n" + 
				"\t\t\t\t\t                             ╚════██║██╔══╝  ██║██║╚██╗██║                       \r\n" + 
				"\t\t\t\t\t                             ███████║███████╗██║██║ ╚████║                       \r\n" + 
				"\t\t\t\t\t                             ╚══════╝╚══════╝╚═╝╚═╝  ╚═══╝                       \r\n" + 
				"\t\t\t\t\t                                                                                \r\n" + 
				"\t\t\t\t\t        ███████╗██████╗ ██╗   ██╗ ██████╗ █████╗ ████████╗██╗ ██████╗ ███╗   ██╗\r\n" + 
				"\t\t\t\t\t        ██╔════╝██╔══██╗██║   ██║██╔════╝██╔══██╗╚══██╔══╝██║██╔═══██╗████╗  ██║\r\n" + 
				"\t\t\t\t\t        █████╗  ██║  ██║██║   ██║██║     ███████║   ██║   ██║██║   ██║██╔██╗ ██║\r\n" + 
				"\t\t\t\t\t        ██╔══╝  ██║  ██║██║   ██║██║     ██╔══██║   ██║   ██║██║   ██║██║╚██╗██║\r\n" + 
				"\t\t\t\t\t        ███████╗██████╔╝╚██████╔╝╚██████╗██║  ██║   ██║   ██║╚██████╔╝██║ ╚████║\r\n" + 
				"\t\t\t\t\t        ╚══════╝╚═════╝  ╚═════╝  ╚═════╝╚═╝  ╚═╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝\r\n" + 
				"\t\t\t\t\t                                                                                \r\n" + 
				"\t\t\t\t\t          	   ██████╗███████╗███╗   ██╗████████╗███████╗██████╗ \r\n" + 
				"\t\t\t\t\t                  ██╔════╝██╔════╝████╗  ██║╚══██╔══╝██╔════╝██╔══██╗\r\n" + 
				"\t\t\t\t\t          	  ██║     █████╗  ██╔██╗ ██║   ██║   █████╗  ██████╔╝\r\n" + 
				"\t\t\t\t\t          	  ██║     ██╔══╝  ██║╚██╗██║   ██║   ██╔══╝  ██╔══██╗\r\n" + 
				"\t\t\t\t\t          	  ╚██████╗███████╗██║ ╚████║   ██║   ███████╗██║  ██║\r\n" + 
				"\t\t\t\t\t          	   ╚═════╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝╚═╝  ╚═╝         \r\n" + 
				"\t\t\t\t\t                                                                          ");
		
		System.out.println("\t\t\t\t\t        ████████████████████████████████████████████████████████████████████████");  
	}
	
	public void end() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t세인교육센터 프로그램을 종료합니다.");

	}
	
	/**
	 * 홈 화면 메뉴
	 */
	public void menu() {
		System.out.println();
		System.out.println();
		thickLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 관리자");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 교사");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 교육생");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 프로그램 종료");
		System.out.println();
		thickLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");
			
	}
	
	public String loginMenuTitle(int n) {
		if (n == View.ADMIN) { return "관리자";
		} else if (n == View.TEACHER) { return "교사";
		} else if (n == View.STUDNET) { return "학생";
		}
		return null;
	}

	/**
	 * 로그인 메뉴
	 * @param n : 선택된 번호
	 */
	public void loginMenu(int n) {
		
		flip();
		if(n == 1) {
			System.out.println(
					"\t\t\t\t\t\t █████╗ ██████╗ ███╗   ███╗██╗███╗   ██╗██╗███████╗████████╗██████╗  █████╗ ████████╗ ██████╗ ██████╗ \r\n" + 
							"\t\t\t\t\t\t██╔══██╗██╔══██╗████╗ ████║██║████╗  ██║██║██╔════╝╚══██╔══╝██╔══██╗██╔══██╗╚══██╔══╝██╔═══██╗██╔══██╗\r\n" + 
							"\t\t\t\t\t\t███████║██║  ██║██╔████╔██║██║██╔██╗ ██║██║███████╗   ██║   ██████╔╝███████║   ██║   ██║   ██║██████╔╝\r\n" + 
							"\t\t\t\t\t\t██╔══██║██║  ██║██║╚██╔╝██║██║██║╚██╗██║██║╚════██║   ██║   ██╔══██╗██╔══██║   ██║   ██║   ██║██╔══██╗\r\n" + 
							"\t\t\t\t\t\t██║  ██║██████╔╝██║ ╚═╝ ██║██║██║ ╚████║██║███████║   ██║   ██║  ██║██║  ██║   ██║   ╚██████╔╝██║  ██║\r\n" + 
					"\t\t\t\t\t\t╚═╝  ╚═╝╚═════╝ ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝");
			}
			else if( n == 2) {
			System.out.println("\t\t\t\t\t\t\t\t████████╗███████╗ █████╗  ██████╗██╗  ██╗███████╗██████╗ \r\n" + 
					"\t\t\t\t\t\t\t\t╚══██╔══╝██╔════╝██╔══██╗██╔════╝██║  ██║██╔════╝██╔══██╗\r\n" + 
					"\t\t\t\t\t\t\t\t   ██║   █████╗  ███████║██║     ███████║█████╗  ██████╔╝\r\n" + 
					"\t\t\t\t\t\t\t\t   ██║   ██╔══╝  ██╔══██║██║     ██╔══██║██╔══╝  ██╔══██╗\r\n" + 
					"\t\t\t\t\t\t\t\t   ██║   ███████╗██║  ██║╚██████╗██║  ██║███████╗██║  ██║\r\n" + 
					"\t\t\t\t\t\t\t\t   ╚═╝   ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\r\n" + 
					"                                                        ");	
			}
			else if( n == 3) {
				System.out.println("\t\t\t\t\t\t\t\t███████╗████████╗██╗   ██╗██████╗ ███████╗███╗   ██╗████████╗\r\n" + 
						"\t\t\t\t\t\t\t\t██╔════╝╚══██╔══╝██║   ██║██╔══██╗██╔════╝████╗  ██║╚══██╔══╝\r\n" + 
						"\t\t\t\t\t\t\t\t███████╗   ██║   ██║   ██║██║  ██║█████╗  ██╔██╗ ██║   ██║   \r\n" + 
						"\t\t\t\t\t\t\t\t╚════██║   ██║   ██║   ██║██║  ██║██╔══╝  ██║╚██╗██║   ██║   \r\n" + 
						"\t\t\t\t\t\t\t\t███████║   ██║   ╚██████╔╝██████╔╝███████╗██║ ╚████║   ██║   \r\n" + 
						"\t\t\t\t\t\t\t\t╚══════╝   ╚═╝    ╚═════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝   ╚═╝   \r\n" + 
						"                                                             ");
			}
		System.out.println();
		thickLine();
		System.out.println();
		System.out.printf("\t\t\t\t\t\t\t\t\t\t[%s]\n", loginMenuTitle(n));
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 로그인");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 아이디 찾기");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 비밀번호 찾기");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println();
		thickLine();
		System.out.println();
	  	System.out.print("\t\t\t\t\t\t\t\t\t\t선택(번호) : ");
	}
	
	public void loginTitle(int n) {
		System.out.printf("\t\t\t\t\t\t\t\t\t\t[%s 로그인]\n", loginMenuTitle(n));
	}
	
	public void wrongSel() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t잘못 입력하셨습니다. 계속하시려면 엔터를 입력해주세요. ");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
	}
	
	public void wrongSelAndSel() {
		System.out.println();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t잘못 입력하셨습니다. 다시 입력하시려면 '엔터'를, 취소하시려면 '0'을 입력해주세요. ");
		System.out.println();
		System.out.println();
	}
	
	public void pause() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t계속하려면 엔터를 입력하세요.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
	}

	public void loginSuccess() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t로그인 되었습니다.");
		System.out.println();
		System.out.println();
	}

	public void noExistAccount() {
		System.out.println();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t존재하지 않는 계정입니다. 다시 입력하시려면 '엔터'를, 취소하시려면 '0'을 입력해주세요. ");
		System.out.println();
		System.out.println();
		
	}

	public void findTitle(int n) {
		System.out.println();
		System.out.println();
		     if (n == View.FINDID) System.out.println("\t\t\t\t\t\t\t\t\t\t[아이디 찾기]");
		else if (n == View.FINDPW) System.out.println("\t\t\t\t\t\t\t\t\t\t[비밀번호 찾기]");
		     System.out.println();
				System.out.println();
	}

	public void findFail(String str) {
		System.out.println();
		System.out.printf("\t\t\t\t\t\t\t\t\t\t해당하는 %s를 찾을 수 없습니다. 다시 입력하시려면 '엔터'를, 취소하시려면 '0'을 입력해주세요. ", str);
		
	}

	public void cancelSel() {
		System.out.println("\t\t\t\t\t\t\t\t\t\t취소하셨습니다. 다시 입력하시려면 '엔터'를, 뒤로 돌아가려면 '0'을 입력해주세요.");
		
	}

	public void line() {
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
		
	}
	
	/**
	 * 굵은 선 출력 메소드
	 */
	public void thickLine() {
		System.out.println(
				"==============================================================================================================================================================================================");
	}
	
	/**
	 * 얆은 긴 선 출력 메소드
	 */
	public void thinLine() {
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	public void flip() {

		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
}
