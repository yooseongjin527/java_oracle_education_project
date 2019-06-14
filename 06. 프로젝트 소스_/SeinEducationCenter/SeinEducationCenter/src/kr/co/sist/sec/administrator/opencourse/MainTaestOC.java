package kr.co.sist.sec.administrator.opencourse;

public class MainTaestOC {
	public static void main(String[] args) {
		System.out.println("개설 과정부터 시작합니다.");
		
		OCController occontroller = new OCController();
		occontroller.opencourse();
		
		System.out.println("끝!!");
		
	}
}
