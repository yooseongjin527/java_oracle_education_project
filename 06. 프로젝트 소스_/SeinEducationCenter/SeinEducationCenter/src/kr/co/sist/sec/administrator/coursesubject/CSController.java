package kr.co.sist.sec.administrator.coursesubject;

import java.util.Scanner;

import kr.co.sist.sec.administrator.AdminView;
import kr.co.sist.sec.administrator.basicinfo.course.CourseService;
import kr.co.sist.sec.main.View;

public class CSController {
	
	private static View view;
	private static AdminView adminView;
	private static CSView csView;
	private static ICSService csService;
	private static Scanner scan;
	private static CourseService cs;
	
	
	static {
		view = new View();
		adminView = new AdminView();
		csView = new CSView();
		csService = new CSService();
		scan = new Scanner(System.in);
		cs = new CourseService();
	}
	
	public void coursesubject() {
		System.out.println();
		System.out.println();
		boolean loop = true;
		while (loop) {
			
			//과정 정보
			cs.list();
			csView.menu();
			String sel = scan.nextLine();
			
			if (sel.equals(csView.LIST)) csService.list();
			else if (sel.equals(csView.ADD)) csService.add();
			else if (sel.equals(csView.DEL)) csService.del();
			else if (sel.equals("0")) loop = false;
			else view.wrongSel();
			
		}

	}

}
