package kr.co.sist.sec.administrator.student;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.sec.dto.DTOStudent;
import kr.co.sist.sec.dto.DTOvwAllStudent;
import kr.co.sist.sec.dto.DTOvwSelectedStudent;


/**
 * [관리자-교육생관리] Service 클래스
 * @author 정희진
 *
 */
public class ServiceAdministorStudent implements IServiceAdministorStudent {

	private ViewAdministorStudent view;
	private Scanner scan;
	
	public ServiceAdministorStudent() {
		view = new ViewAdministorStudent();
		scan = new Scanner(System.in);
	}
		
	
	/**
	 * [관리자-교육생관리] 교육생 정보 등록 Service 메소드  
	 * @throws InterruptedException 
	 */
	@Override
	public void add() {

		view.title(ViewAdministorStudent.ADD);
		
		
		System.out.println();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();
		
		
		// 이름 유효성 검사
		boolean loop = true;
		
		String name = "";

		while(loop) {
		
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t[1] 이름\n"
										+ "\t\t\t\t\t\t\t\t\t\t : ");
			name = scan.nextLine();
			name = name.replace("'", "''");
			
			int check = 0;
			
			for (int i = 0; i < name.length(); i++) {
				if (name.charAt(i) < (char)'가' || name.charAt(i) > '힣') {
					check = 1;
				}
			}
			
			if (name.length() > 1 && check == 0) {
				loop = false;
			} else {				
				System.out.println("\t\t\t\t\t\t\t\t\t\t[!] 한글로 두 글자 이상 입력해주세요.");
			}
		
		}
		
		
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();
		
		
		// 주민번호 뒷자리 유효성 검사
		loop = true;
		
		String ssn = "";
		
		while (loop) {
			
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t[2] 주민번호 뒷자리 (숫자 7자리)\n"
									+ "\t\t\t\t\t\t\t\t\t\t : ");
			ssn = scan.nextLine();
			ssn = ssn.replace("'", "''");
			
			int check = 0;
			
			for (int i = 0; i < ssn.length(); i++) {
				if (ssn.charAt(i) < (char)'0' || ssn.charAt(i) > (char)'9') {
					check = 1;
				}
			}
			
			if (ssn.length() == 7 && check == 0) {
				loop = false;
			} else {				
				System.out.println("\t\t\t\t\t\t\t\t\t\t[!] 숫자 7자리로만 입력해주세요.");
			}
			
		}
		
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();
		
		
		// 전화번호 유효성 검사
		loop = true;
		
		String tel = "";
		
		while (loop) {
		
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t[3] 전화번호 (-까지 입력)\n"
								+ "\t\t\t\t\t\t\t\t\t\t : ");
			tel = scan.nextLine();
			tel = tel.replace("'", "''");
			
			int check = 0;
			
			String checkTel = tel.replace("-", "");
			
			if (checkTel.length() > 11 && checkTel.length() < 10) {
				check = 1;
			}
			
			for (int i = 0; i < checkTel.length(); i++) {
				if (checkTel.charAt(i) < (char)'0' || checkTel.charAt(i) > (char)'9') {
					check = 1;
				}
			}
			
			if (check == 0) {
				loop = false;
			} else {
				System.out.println("\t\t\t\t\t\t\t\t\t\t[!] 전화번호를 바르게 입력해주세요.");
			}
		
		}
		
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();

		
		loop = true;
		String major = "";

		while(loop) {
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t[4] 전공 여부 선택 (1. 전공, 2. 비전공, 3. 고졸)\n"
								+ "\t\t\t\t\t\t\t\t\t\t : ");
			major = scan.nextLine();

			int check = 0;
			
			if (major.equals("1") || major.equals("2") || major.equals("3")) {
				check = 0;
			} else {
				check = 1;
			}
			
			
			if (check == 0) {
				loop = false;
			} else {
				System.out.println("\t\t\t\t\t\t\t\t\t\t[!] 보기에 있는 숫자만 입력해주세요.");
			}
			
		}
		
		
		if (major.equals("1")) {
			major = "전공";
		} else if (major.equals("2")) {
			major = "비전공";
		} else if (major.equals("3")) {
			major = "고졸";
		}
	
		major = major.replace("'", "''");
		
		

		
		
		

		DAOAdministorStudent dao = new DAOAdministorStudent();
		
		DTOStudent dto = new DTOStudent();
		dto.setName(name);
		dto.setSsn(ssn);
		dto.setTel(tel);
		dto.setMajor(major);
		
		int result = dao.add(dto);

		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();
		System.out.println();
		System.out.println();
		

		System.out.println("\t\t\t\t\t\t\t\t\t\t[ 정보 등록 중... ]");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println();
		System.out.println();
		
		view.flip();
		System.out.println();
		if (result == 1) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t교육생 등록 완료");	
			System.out.println();
			System.out.println();
		} else {
			System.out.println("\t\t\t\t\t\t\t\t\t\t교육생 등록 실패");	
			System.out.println();
			System.out.println();
		}
		
		dao.close();
		
		view.pause();
		view.flip();
		
	}

	
	/**
	 * [관리자-교육생관리] 교육생 조회 (전체) Service 메소드
	 * @throws InterruptedException 
	 */
	@Override
	public void list() {
		
		view.flip();
		view.title(ViewAdministorStudent.LIST);
		
		System.out.println();
		
		
		
		
		
		
		
		view.columnName(ViewAdministorStudent.ALLSTUDENT);
		view.thinLine();
		DAOAdministorStudent dao = new DAOAdministorStudent();
		
		ArrayList<DTOvwAllStudent> list = dao.AllStudentlist();
		
		for (DTOvwAllStudent dto : list) {
			
			System.out.printf("\t\t\t\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t\t%s\n"
								, dto.getSeq()
								, dto.getName()
								, dto.getMajor()
								, dto.getTel()
								, dto.getSsn());
		}
		view.thickLine();
		dao.close();
		
		System.out.println();
		System.out.println();
		
		MainControllerAdministorStudent.studentSelectMenu();		
				
	}
	
	
	@Override
	public void selectedStudentInfo() {

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t【교육생 세부 정보 조회 】");
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t교육생 번호 입력 : ");
		String StudentNum = scan.nextLine();
		
		view.flip();
		
		DAOAdministorStudent dao = new DAOAdministorStudent();
		ArrayList<DTOvwSelectedStudent> selectedList = dao.SelectedStudentList(StudentNum);
		
		for (DTOvwSelectedStudent dto : selectedList) {
			
			System.out.println();
			
			view.thickLine();
			
			view.columnName(ViewAdministorStudent.ALLSTUDENT);
			view.thinLine();
			
			System.out.printf("\t\t\t\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t\t%s\n"
								, dto.getSeq()
								, dto.getName()
								, dto.getMajor()
								, dto.getTel()
								, dto.getSsn());
			System.out.println();
			System.out.println();
			System.out.println();
			view.thickLine();
			System.out.println();
			view.columnName(ViewAdministorStudent.SELECTEDSTUDENT);
			view.thinLine();
			System.out.printf("\t\t\t\t\t%s\t\t%s\t%s\t%s\t\t%s\n"
								, dto.getCourse_status() == null ? "없음  " : dto.getCourse_status() 
								, dto.getCourse_start() == null ? "없음          " : dto.getCourse_start()
								, dto.getCourse_end() == null ? "없음          " : dto.getCourse_end()
								, dto.getClassroom() == null ? "없음          " : dto.getClassroom()
								, dto.getCourse_name() == null ? "없음" : dto.getCourse_name());
			
			System.out.println();
			System.out.println();
			view.thickLine();
		}
		
		
		dao.close();
				
		
	}
	
	
	
	@Override
	public void nameSearch() {
		
		view.flip();
		view.title(ViewAdministorStudent.SEARCH);
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t검색할 교육생의 이름을 입력해주세요.");
		view.input();
		String input = scan.nextLine();
		
		
		DAOAdministorStudent dao = new DAOAdministorStudent();
		
		ArrayList<DTOStudent> list = dao.nameSearch(input);
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		view.thickLine();
		view.columnName(ViewAdministorStudent.STUDENT);
		view.thinLine();
		
		
		for (DTOStudent dto : list) {
			
			System.out.printf("\t\t\t\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\n"
								, dto.getSeq()
								, dto.getName()
								, dto.getMajor()
								, dto.getTel()
								, dto.getRegdate()
								, dto.getSsn());
					
		}			
		
		System.out.println();
		view.thickLine();
		System.out.println();
		System.out.println();
		
		dao.close();
		view.pause();
		view.flip();
		
	}
	
	
	/**
	 * [관리자-교육생관리] 교육생 정보 수정 Service 메소드
	 * @throws InterruptedException 
	 */
	@Override
	public void edit() {
		
		view.flip();
		view.title(ViewAdministorStudent.EDIT);
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t정보를 수정할 교육생의 고유번호를 입력해주세요.");
		view.input();
		String input = scan.nextLine();
		
		DAOAdministorStudent dao = new DAOAdministorStudent();
		
		DTOStudent dto = dao.searchList(input);
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		view.thickLine();
		view.columnName(ViewAdministorStudent.STUDENT);
		view.thinLine();	
	
		System.out.printf("\t\t\t\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\n"
				, dto.getSeq()
				, dto.getName()
				, dto.getMajor()
				, dto.getTel()
				, dto.getRegdate()
				, dto.getSsn());
		
		System.out.println();
		view.thickLine();
		
		System.out.println();
		System.out.println();
		MainControllerAdministorStudent.editCheck();
		

		String name = "";
		String major = "";
		String tel = "";
		String ssn = "";
		
		if (dto != null) {
			
			name = dto.getName();
			major = dto.getMajor();
			tel = dto.getTel();
			ssn = dto.getSsn();		
		
		}
		
		//TODO 유효성검사 
		
//		System.out.println();
		view.flip();
		view.title(ViewAdministorStudent.EDIT);
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t※ 해당 사항에 변경이 없으면 ENTER를 입력해 넘어가세요.");
		
		
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t기존 이름 : " + name);
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t수정 이름 : ");
		String temp = scan.nextLine();
		
		if (!temp.equals("")) {
			name = temp;
		}
		
		
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t기존 전공여부 : " + major);
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t수정 전공여부 (1. 전공, 2. 비전공, 3. 고졸)");
		System.out.print("\t\t\t\t\t\t\t\t\t\t입력 : ");
		temp = scan.nextLine();
		
		if (!temp.equals("")) {
			
			if (temp.equals("1")) {
				major = "전공";
			} else if (temp.equals("2")) {
				major = "비전공";
			} else if (temp.equals("3")) {
				major = "고졸";
			}
		}
		
		
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t기존 전화번호 : " + tel);
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t수정 전화번호 : ");
		temp = scan.nextLine();
		
		if (!temp.equals("")) {
			tel = temp;
		}
		
		
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t기존 주민번호 뒷자리 : " + ssn);
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t수정 주민번호 뒷자리 : ");
		temp = scan.nextLine();
		
		if (!temp.equals("")) {
			ssn = temp;
		}
		
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();
		System.out.println();
		
		
		dto = new DTOStudent();
		dto.setSeq(input);
		dto.setName(name);
		dto.setMajor(major);
		dto.setTel(tel);
		dto.setSsn(ssn);
		
		
		int result = dao.edit(dto);
		
		System.out.println("\t\t\t\t\t\t\t\t\t\t[ 정보 수정 중... ]");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println();
		System.out.println();
		
		view.flip();
		System.out.println();
		if (result == 1 ) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t교육생 정보 수정 완료");
		} else {
			System.out.println("\t\t\t\t\t\t\t\t\t\t교육생 정보 수정 실패");
		}
		
		dao.close();
		view.pause();
		view.flip();
		
	}

	
	/**
	 * [관리자-교육생관리] 교육생 정보 삭제 Service 메소드
	 */
	@Override
	public void del() {
		
		view.flip();
		view.title(ViewAdministorStudent.DEL);
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("\t\t\t\t\t\t\t\t\t\t정보를 삭제할 교육생의 고유번호를 입력해주세요.");
		System.out.print("\t\t\t\t\t\t\t\t\t\t입력 : ");
		String input = scan.nextLine();
		
		DAOAdministorStudent dao = new DAOAdministorStudent();
		
		DTOStudent dto = dao.searchList(input);

		System.out.println();
		System.out.println();
		System.out.println();
		view.thickLine();
		view.columnName(ViewAdministorStudent.STUDENT);
		view.thinLine();
		
		System.out.printf("\t\t\t\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\n"
							, dto.getSeq()
							, dto.getName()
							, dto.getMajor()
							, dto.getTel()
							, dto.getRegdate()
							, dto.getSsn());	
		System.out.println();
		view.thickLine();
		System.out.println();
		System.out.println();
		
		
		MainControllerAdministorStudent.delCheck();
				
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[ 정보 삭제 중... ]");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println();
		System.out.println();
		
		view.flip();
		
		int result = dao.del(input);
		
		if (result == 1) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t교육생 정보 삭제 완료");			
		} else {
			System.out.println("\t\t\t\t\t\t\t\t\t\t교육생 정보 삭제 실패");		
		}
		
		dao.close();
		view.pause();
		view.flip();
		
	}


	


}
