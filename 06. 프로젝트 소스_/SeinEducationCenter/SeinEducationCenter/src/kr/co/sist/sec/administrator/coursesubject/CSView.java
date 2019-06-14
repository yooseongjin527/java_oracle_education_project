package kr.co.sist.sec.administrator.coursesubject;

public class CSView {
	public static final String LIST = "1";
	public static final String ADD = "2";
	public static final String DEL = "3";

	public void courseSchema() {
		System.out.println("\t\t\t\t\t\t\t[번호]\t[과정명]");
		
	}

	public void menu() {
		System.out.println("\t\t\t\t\t\t\t\t\t\t▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 해당 과정의 과목 정보 보기");
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 해당 과정의 과목 등록");
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 해당 과정의 과목 삭제");
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println("\t\t\t\t\t\t\t\t\t\t▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		  System.out.print("\t\t\t\t\t\t\t\t\t\t선택(번호) : ");
		
	}

	

}
