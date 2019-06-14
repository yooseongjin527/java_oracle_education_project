package kr.co.sist.sec.administrator.subjecttextbook;

import java.util.Scanner;

import kr.co.sist.sec.administrator.AdminView;
import kr.co.sist.sec.administrator.basicinfo.subject.SubjectService;
import kr.co.sist.sec.main.View;

public class STController {
	
	private static View view;
	private static AdminView adminView;
	private static STView stView;
	private static ISTService stService;
	private static Scanner scan;
	private static SubjectService ss;
	
	
	static {
		view = new View();
		adminView = new AdminView();
		stView = new STView();
		stService = new STService();
		scan = new Scanner(System.in);
		
		ss = new SubjectService();
	}
	
	public void subjecttextbook() {
		System.out.println();
		boolean loop = true;
		while (loop) {
			
			//과목 정보
			ss.list();
			stView.menu();
			String sel = scan.nextLine();
			
			if (sel.equals(stView.LIST)) stService.list();
			else if (sel.equals(stView.ADD)) stService.add();
			else if (sel.equals(stView.DEL)) stService.del();
			else if (sel.equals("0")) loop = false;
			else view.wrongSel();
			
		}
		
		
	}

}
