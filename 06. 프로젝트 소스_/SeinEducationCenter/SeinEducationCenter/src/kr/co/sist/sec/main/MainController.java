package kr.co.sist.sec.main;

import java.util.Scanner;

import kr.co.sist.sec.administrator.AdminController;
import kr.co.sist.sec.teacher.TeacherController;
import kr.co.sist.sec.student.StudentController;

public class MainController {
	
	private static View view;
	private static IService service;
	private static Scanner scan;
	
	static {
		view = new View();
		service = new Service();
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		view.begin();
		
		boolean loop = true;
		while (loop) {
			view.menu();
			String sel = scan.nextLine();
			
			AdminController ac = new AdminController();
			TeacherController tc = new TeacherController();
			StudentController sc = new StudentController();
			
			if (sel.equals("1")) ac.main(1);
			else if (sel.equals("2")) tc.main(2);
			else if (sel.equals("3")) sc.main(3);
			else if (sel.equals("0")) loop = false;
			else view.wrongSel();
			
		}
		view.end();
	}//main
	
}
