package kr.co.sist.sec.administrator.basicinfo.classroom;

import kr.co.sist.sec.administrator.AdminController;

public class MainTestBCR {
	public static void main(String[] args) {
		System.out.println("기초정보 강의실부터 시작합니다.");
		
		AdminController ac = new AdminController();
		ac.classroom();
		
		System.out.println("끝!!");
		
		
	}
}
