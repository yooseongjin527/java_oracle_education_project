package kr.co.sist.sec.administrator.basicinfo.course;

import kr.co.sist.sec.administrator.AdminController;

public class MainTestBC {
	public static void main(String[] args) {
		System.out.println("기초정보 과정부터 시작합니다.");
		
		AdminController ac = new AdminController();
		ac.course();
		
		System.out.println("끝!!");
		
		
	}
}
