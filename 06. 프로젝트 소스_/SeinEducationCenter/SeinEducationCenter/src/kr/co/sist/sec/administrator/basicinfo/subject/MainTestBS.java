package kr.co.sist.sec.administrator.basicinfo.subject;

import kr.co.sist.sec.administrator.AdminController;

public class MainTestBS {
	public static void main(String[] args) {
		System.out.println("기초정보 과목부터 시작합니다.");
		
		AdminController ac = new AdminController();
		ac.subject();
		
		System.out.println("끝!!");
		
		
	}
}
