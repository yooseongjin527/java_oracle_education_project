package kr.co.sist.sec.administrator.opencourse;

import java.util.Scanner;

import kr.co.sist.sec.administrator.AdminView;
import kr.co.sist.sec.main.View;

public class OCController {
	
	private static View view;
	private static AdminView adminView;
	private static OCView ocView;
	private static IOCService ocService;
	private static Scanner scan;
	
	static {
		view = new View();
		adminView = new AdminView();
		ocView = new OCView();
		ocService = new OCService();
		scan = new Scanner(System.in);
		
	}

	public void opencourse() {
		
		System.out.println();
		boolean loop = true;
		while (loop) {
			view.flip();
			System.out.println("\t\t\t\t\t\t\t\t\t\t[개설 과정 관리]");
			System.out.println();
			System.out.println();
			//개설 과정 정보
			System.out.println("\t\t\t\t\t\t\t\t\t\t[개설 과정 정보]");
			ocView.schema();
			ocService.list();
			System.out.println("====================================================================================================================================================================================================================================");
			
			ocView.menu();
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) ocService.listByCondition();
			else if (sel.equals("2")) ocService.add();
			else if (sel.equals("3")) ocService.edit();
			else if (sel.equals("4")) ocService.del();
			else if (sel.equals("5")) ocService.subjectList();
			else if (sel.equals("6")) ocService.studentList();
			else if (sel.equals("0")) loop = false;
			else view.wrongSel();
			
			
			
		}
		
	}

}
