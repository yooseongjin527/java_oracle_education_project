package kr.co.sist.sec.administrator.coursesubject;

public class MainTestCSController {
	
	
	public static void main(String[] args) {
		
		System.out.println("과정 과목 부터 시작합니다.");
		System.out.println();
		System.out.println();
		
		
		CSController cscontroller = new CSController();
		
		
		cscontroller.coursesubject();
		
		System.out.println("끝!!!");
		
	}
	
}

