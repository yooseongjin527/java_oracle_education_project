package kr.co.sist.sec.administrator.teacher;

import java.util.Scanner;


public class MainControllerAdministorTeacher {

	private static ViewAdministorTeacher view;
	private static IServiceAdministorTeacher adminTeacherService;
	private static Scanner scan;
	
	static {
		
		view = new ViewAdministorTeacher();
		adminTeacherService = new ServiceAdministorTeacher();
		scan = new Scanner(System.in);		
	}
	
	
	
	public void teacherMain() {
	
		
		view.begin();
		view.pause();
		
		boolean loop = true;
		
		while (loop) {
			
			view.flip();
			view.menu();
			view.select();
			
			String sel = scan.nextLine();
			
			System.out.println();
			
			if (sel.equals("1")) adminTeacherService.add();
			else if (sel.equals("2")) read();
			else if (sel.equals("3")) adminTeacherService.edit();
			else if (sel.equals("4")) adminTeacherService.del();
			else if (sel.equals("0")) loop = false;
			else view.wrongNumber();
			
		}
		
		view.end();
		
	}



	
	
	
	
	/**
	 * 관리자 - 교사관리 - 교사 정보 조회
	 * @throws InterruptedException 
	 */
	private static void read() {
		
		boolean loop = true;
		
		while (loop) {
						
			view.readMenu();
			view.select();
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) adminTeacherService.list();
			else if (sel.equals("2")) adminTeacherService.nameSearch();
			else if (sel.equals("0")) loop = false;
			else view.wrongNumber();
			
		}
		
	}


	
	/**
	 * 관리자 - 교사관리 - 교사 정보 수정
	 * @throws InterruptedException
	 */
	public static void editCheck() {
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println();
			view.editCheck();
			view.select();
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) loop = false;
			else if (sel.equals("2")) adminTeacherService.edit();
			else if (sel.equals("0")) loop = false;
			else view.wrongNumber();
			
		}
		
	}


	/**
	 * 삭제 확인 메소드
	 * @throws InterruptedException
	 */
	public static void delCheck() {

		boolean loop = true;
		
		while (loop) {
			
			System.out.println();
			view.delCheck();
			view.select();
			
			String sel = scan.nextLine();

			if (sel.equals("1")) loop = false;
			else if (sel.equals("2")) adminTeacherService.del();
			else if (sel.equals("0")) loop = false;
			else view.wrongNumber();
			
		}
	}


	/**
	 * 관리자 - 교사관리 - 교사 상세정보 조회 확인
	 * @throws InterruptedException 
	 */
	public static void teacherSelectMenu() {
		
		boolean loop = true;
		
		while (loop) {
			
			view.askSelectTeacher();
			view.select();

			String sel = scan.nextLine();
			
			if (sel.equals("1")) adminTeacherService.selectedTeacherInfo();
			else if (sel.equals("0")) loop = false;
			else view.wrongNumber();
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
