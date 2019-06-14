package kr.co.sist.sec.administrator;

import java.util.Scanner;

import kr.co.sist.sec.main.View;

public class Service implements IService {

	private View view;
	private AdminView adminView;
	private Scanner scan;

	public Service() {
		view = new View();
		adminView = new AdminView();
		scan = new Scanner(System.in);
	}

	@Override
	public void login(int n) {
		boolean loop = true;
		boolean loop2 = true;
		while(loop && loop2) {
			view.flip();
			view.thickLine();
			System.out.println();
			view.loginTitle(n);
			System.out.println();
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\tID : ");
			String id = scan.nextLine();
			
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\tPW : ");
			String pw = scan.nextLine();
			System.out.println();
			view.thinLine();
			
			if (id.equals("secAdmin") && pw.equals("java1234")) {
				
				view.loginSuccess();
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				AdminController ac = new AdminController();
				loop2 = ac.admin();

			} else {
				view.noExistAccount();
				String sel = scan.nextLine();
				if (sel.equals("0")) {
					loop = false;
				}
			}
		}
		
	}

	@Override
	public void findId(int n) {
		// id찾기 > name, tel 입력받기 // 관리자 name : sec, 관리자 tel : 010-1111-1111
		System.out.println();
		view.findTitle(n);
		
		boolean loop = true;
		while (loop) {
			
			System.out.println();
			System.out.println();
			
			System.out.print("\t\t\t\t\t\t\t\t\t\t이름 : ");
			String name = scan.nextLine();
			System.out.println();

			System.out.print("\t\t\t\t\t\t\t\t\t\t전화번호((ex) 010-1234-1234) : ");
			String tel = scan.nextLine();
			System.out.println();

			if (name.equals("sec") && tel.equals("010-1111-1111")) {
				System.out.println();
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t%s님의 아이디는 '%s' 입니다.\n", name, "secAdmin");
				view.pause();
				loop = false;
			} else {
				view.findFail("아이디");
				String sel = scan.nextLine();
				if (sel.equals("0")) {
					loop = false;
				}
			}
		}
	}

	@Override
	public void findPw(int n) {
		// pw찾기 > id 입력받기
		System.out.println();
		System.out.println();
		view.findTitle(n);

		boolean loop = true;
		while (loop) {
			
			System.out.println();
			System.out.println();
			
			System.out.print("\t\t\t\t\t\t\t\t\t\t아이디 : ");
			String id = scan.nextLine();
		
			if (id.equals("secAdmin")) {
				System.out.println();
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t%s님의 비밀번호는 '%s' 입니다.\n", id, "java1234");
				loop = false;
				view.pause();
			} else {
				view.findFail("비밀번호");
				String sel = scan.nextLine();
				if (sel.equals("0")) {
					loop = false;
				}
			}
		}
	}

	
}
	
	




