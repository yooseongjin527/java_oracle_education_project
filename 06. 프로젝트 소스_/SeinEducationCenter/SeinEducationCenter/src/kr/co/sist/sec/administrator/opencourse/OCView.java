package kr.co.sist.sec.administrator.opencourse;

public class OCView {

	public void schema() {
		System.out.println("====================================================================================================================================================================================================================================");
		 System.out.printf("%s\t%-50s\t%-50s\t%-10s\t%-50s\t%-6s\t%s\n", "[번호]", "[과정명]", "[과정기간]", "[강의실]", "[개설 과목]", "[교사명]", "[교육생 등록 인원]");
		System.out.println("====================================================================================================================================================================================================================================");
		
	}

	public void menu() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 개설 과정 조건별 보기");
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 개설 과정 등록");
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 개설 과정 정보 수정");
		System.out.println("\t\t\t\t\t\t\t\t\t\t4. 개설 과정 삭제");
		System.out.println("\t\t\t\t\t\t\t\t\t\t5. 개설 과정별 과목 정보");
		System.out.println("\t\t\t\t\t\t\t\t\t\t6. 개설 과정별 교육생 정보");
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println("\t\t\t\t\t\t\t\t\t\t▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		  System.out.print("\t\t\t\t\t\t\t\t\t\t선택(번호) : ");
		
	}

}
