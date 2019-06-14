package kr.co.sist.sec.administrator.student;

import java.util.Scanner;


public class MainControllerAdministorStudent {
	
	private static ViewAdministorStudent view;
	private static IServiceAdministorStudent adminStudentService;
	private static Scanner scan;
	
	static {
		
		view = new ViewAdministorStudent();
		adminStudentService = new ServiceAdministorStudent();
		scan = new Scanner(System.in);		
	}
	

	
	public void studentMain() {
		
		view.begin();
		view.pause();
		
		boolean loop = true;
		
		while (loop) {
			
			view.flip();
			view.menu();
			view.select();
			
			String sel = scan.nextLine();
			
			System.out.println();
			
			if (sel.equals("1")) adminStudentService.add();
			else if (sel.equals("2")) read();
			else if (sel.equals("3")) adminStudentService.edit();
			else if (sel.equals("4")) adminStudentService.del();
			else if (sel.equals("0")) loop = false;
			else view.wrongNumber();
			
		}
		
		view.end();
		
	}
	
	
	
	/**
	 * 관리자 - 교육생관리 - 교육생 정보 조회
	 * @throws InterruptedException 
	 */
	public static void read() {
	
		boolean loop = true;
		
		while (loop) {
						
			view.readMenu();
			view.select();
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) adminStudentService.list();
			else if (sel.equals("2")) adminStudentService.nameSearch();
			else if (sel.equals("0")) loop = false;
			else view.wrongNumber();
			
		}
		
	}
	
	
	/**
	 * 관리자 - 교육생관리 - 교육생 정보 수정
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
			else if (sel.equals("2")) adminStudentService.edit();
			else if (sel.equals("0")) loop = false;
			else view.wrongNumber();
			
		}
		
	}
	
	
	/**
	 * 관리자 - 교육생관리 - 교육생 정보 삭제 정보 삭제 확인
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
			else if (sel.equals("2")) adminStudentService.del();
			else if (sel.equals("0")) loop = false;
			else view.wrongNumber();
			
		}
		
	}
	
	
	/**
	 * 관리자 - 교육생관리 - 교육생 상세정보 조회 확인
	 * @throws InterruptedException 
	 */
	public static void studentSelectMenu() {
				
		boolean loop = true;
		
		while (loop) {
			
			view.askSelectStudent();
			view.select();

			String sel = scan.nextLine();
			
			if (sel.equals("1")) adminStudentService.selectedStudentInfo();
			else if (sel.equals("0")) loop = false;
			else view.wrongNumber();
			
		}
		
	}
	
	
}
