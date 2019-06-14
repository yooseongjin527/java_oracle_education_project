package kr.co.sist.sec.administrator;

import java.util.Scanner;

import kr.co.sist.sec.main.View;

public class AdminView extends View {

	public static final int SEARCH = 1;
	public static final int ADD = 2;
	public static final int EDIT = 3;
	public static final int DEL = 4;
	
	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}

	public void adminMenu() {
		flip();
		thickLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[관리자 메뉴]");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 기초 정보 관리");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 교사 계정 관리");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 교육생 계정 관리");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t4. 과정별 과목 관리");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t5. 과목별 교재 관리");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t6. 개설 과정 관리");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t7. 시험 관리 및 성적 조회");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t8. 출결 관리 및 출결 조회");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t9. 교사 평가 관리");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t10. 기업 공고 관리");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(번호): ");
	}

	public void basicInfoManageMenu() {
		flip();
		thickLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[기초 정보 관리]");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 과정");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 과목");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 강의실");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t4. 교재");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(번호) : ");
		
	}

	public void courseCRUDMenu() {
		
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 검색");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 등록");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 과정명 수정");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t4. 삭제");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println();
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(번호) : ");
		
	}

	public void courseTitle() {
		System.out.println("\t\t\t\t\t\t\t\t\t\t[과정 정보]");
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
		System.out.printf("\t\t\t\t\t\t\t%s\t%s\n", "[번호]", "[과정명]");
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
		
	}
	
	public void courseSchema() {
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
		System.out.printf("\t\t\t\t\t\t\t%s\t%s\n", "[번호]", "[과정명]");
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
		
	}

	public void line() {
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
	}
	
	public void sline() {
		System.out.println("\t\t\t\t\t\t\t==================================================================================================================================");
	}

	public void courseTitle(int n) {
		   if (n == AdminView.SEARCH) System.out.println("\t\t\t\t\t\t\t\t\t\t[과정 검색]");
		 else if (n == AdminView.ADD) System.out.println("\t\t\t\t\t\t\t\t\t\t[과정 등록]");
		else if (n == AdminView.EDIT) System.out.println("\t\t\t\t\t\t\t\t\t\t[과정명 수정]");
		 else if (n == AdminView.DEL) System.out.println("\t\t\t\t\t\t\t\t\t\t[과정 삭제]");
	}

	public void subjectCRUDMenu() {
		thickLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 과목 검색");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 과목 등록");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 과목 정보 수정");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t4. 과목 삭제");
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		thinLine();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");
		
	}

	public void subjectTitle() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t[과목 정보]");
		System.out.println("\t\t\t\t\t\t\t==================================================================================================================================");
		 System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t\t%s\n", "[번호]", "[과목명]", "[교재명]");
		System.out.println("\t\t\t\t\t\t\t==================================================================================================================================");
		
	}
	
	public void subjectSchema() {
		System.out.println("\t\t\t\t\t\t\t==================================================================================================================================");
		 System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t\t%s\n", "[번호]", "[과목명]", "[교재명]");
		System.out.println("\t\t\t\t\t\t\t==================================================================================================================================");
	}

	public void subjectTitle(int n) {
		if (n == AdminView.SEARCH) System.out.println("\t\t\t\t\t\t\t\t\t\t[과목 검색]");
		else if (n == AdminView.ADD) System.out.println("\t\t\t\t\t\t\t\t\t\t[과목 등록]");
		else if (n == AdminView.EDIT) System.out.println("\t\t\t\t\t\t\t\t\t\t[과목 정보 수정]");
		else if (n == AdminView.DEL) System.out.println("\t\t\t\t\t\t\t\t\t\t[과목 삭제]");
		
	}

	public void classroomCRUDMenu() {
		System.out.println("\t\t\t\t\t\t\t\t\t\t▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 강의실 검색");
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 강의실 등록");
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 강의실 삭제");
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println("\t\t\t\t\t\t\t\t\t\t▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		  System.out.print("\t\t\t\t\t\t\t\t\t\t선택(번호) : ");
		
	}

	public void classroomTitle() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t[강의실 정보]");
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
		 System.out.printf("\t\t\t\t\t\t\t%s\t%-10s\t%s\n", "[번호]", "[강의실명]", "[인원]");
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
		
	}
	
	public void classroomTitle(int n) {
		  if (n == AdminView.SEARCH) System.out.println("\t\t\t\t\t\t\t\t\t\t[강의실 검색]");
		else if (n == AdminView.ADD) System.out.println("\t\t\t\t\t\t\t\t\t\t[강의실 등록]");
		            else if (n == 3) System.out.println("\t\t\t\t\t\t\t\t\t\t[강의실 삭제]");
		
	}
	
	public void textbookCRUDMenu() {
		System.out.println("\t\t\t\t\t\t\t\t\t\t▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("\t\t\t\t\t\t\t\t\t\t1. 교재 검색");
		System.out.println("\t\t\t\t\t\t\t\t\t\t2. 교재 등록");
		System.out.println("\t\t\t\t\t\t\t\t\t\t3. 교재 정보 수정");
		System.out.println("\t\t\t\t\t\t\t\t\t\t4. 교재 삭제");
		System.out.println("\t\t\t\t\t\t\t\t\t\t0. 뒤로 돌아가기");
		System.out.println("\t\t\t\t\t\t\t\t\t\t▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		  System.out.print("\t\t\t\t\t\t\t\t\t\t선택(번호) : ");
	}
		
	public void textbookTitle() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t[교재 정보]");
		System.out.println("\t\t\t\t\t\t\t==================================================================================================================================");
		 System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t\t%s\t%s\n", "[번호]", "[교재명]", "[저자]", "[출판사]");
		System.out.println("\t\t\t\t\t\t\t==================================================================================================================================");
		
	}
	
	public void textbookSchema() {
		System.out.println("\t\t\t\t\t\t\t==================================================================================================================================");
		 System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t\t%s\t%s\n", "[번호]", "[교재명]", "[저자]", "[출판사]");
		 System.out.println("\t\t\t\t\t\t\t==================================================================================================================================");
	}

	public void textbookTitle(int n) {
		   if (n == AdminView.SEARCH) System.out.println("\t\t\t\t\t\t\t\t\t\t[교재 검색]");
		 else if (n == AdminView.ADD) System.out.println("\t\t\t\t\t\t\t\t\t\t[교재 등록]");
		else if (n == AdminView.EDIT) System.out.println("\t\t\t\t\t\t\t\t\t\t[교재 정보 수정]");
		 else if (n == AdminView.DEL) System.out.println("\t\t\t\t\t\t\t\t\t\t[교재 삭제]");
		
	}

	public void classroomSchema() {
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
		 System.out.printf("\t\t\t\t\t\t\t%s\t%-10s\t%s\n", "[번호]", "[강의실명]", "[정원]");
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
		
	}

	public void teacherSchema() {
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
		 System.out.printf("\t\t\t\t\t\t\t%s\t%-10s\t%-7s\t%s\n", "[번호]", "[교사명]", "[주민번호]", "[전화번호]");
		System.out.println("\t\t\t\t\t\t\t==========================================================================");
		
	}
	
	
	
	

}




