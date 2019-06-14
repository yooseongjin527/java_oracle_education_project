package kr.co.sist.sec.administrator.subjecttextbook;

public class MainTestSTController {
	
	
	public static void main(String[] args) {
		
		System.out.println("과목 교재 부터 시작합니다.");
		System.out.println();
		System.out.println();
		
		STController stcontroller = new STController();
		
		stcontroller.subjecttextbook();
		
		System.out.println("끝!!!");
	}
	
}

