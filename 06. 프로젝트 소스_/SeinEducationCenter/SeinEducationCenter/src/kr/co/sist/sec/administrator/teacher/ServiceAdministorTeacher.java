package kr.co.sist.sec.administrator.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.sec.dto.DTOTeacher;
import kr.co.sist.sec.dto.DTOvwAllTeacher;
import kr.co.sist.sec.dto.DTOvwSelectedTeacher;


public class ServiceAdministorTeacher implements IServiceAdministorTeacher {

	
	private ViewAdministorTeacher view;
	private Scanner scan;
	
	public ServiceAdministorTeacher() {
		view = new ViewAdministorTeacher();
		scan = new Scanner(System.in);
	}
	
	
	
	/**
	 * [관리자-교사관리] 신규 교사 등록 Service 메소드
	 */
	@Override
	public void add() {
		
		view.title(ViewAdministorTeacher.ADD);
		
		
		System.out.println();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();
		
		
		// 이름 유효성 검사
		boolean loop = true;
		
		String name = "";

		while(loop) {
		
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t▶ 이름\n"
										+ "\t\t\t\t\t\t\t\t\t\t : ");
			name = scan.nextLine();
			name = name.replace("'", "''");
			
			System.out.println();
			
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
			System.out.print("\t\t\t\t\t\t\t\t\t\t▶ 주민번호 뒷자리 (숫자 7자리)\n"
										+ "\t\t\t\t\t\t\t\t\t\t : ");
			ssn = scan.nextLine();
			ssn = ssn.replace("'", "''");
			System.out.println();
			
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
			System.out.print("\t\t\t\t\t\t\t\t\t\t▶ 전화번호 (-까지 입력)\n"
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
		
			
		

		DAOAdministorTeacher dao = new DAOAdministorTeacher();
		
		DTOTeacher dto = new DTOTeacher();
		dto.setName(name);
		dto.setSsn(ssn);
		dto.setTel(tel);
		
		int result = dao.add(dto);

		
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t"); view.thinShortLine();
		System.out.println();
		System.out.println();
		

		System.out.println("\t\t\t\t\t\t\t\t\t\t[ 정보 등록 중... ]");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();
		
		view.flip();
		System.out.println();
		if (result == 1) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t교사 등록 완료");	
			System.out.println();
			System.out.println();
		} else {
			System.out.println("\t\t\t\t\t\t\t\t\t\t교사 등록 실패");	
			System.out.println();
			System.out.println();
		}
		
		dao.close();
		
		view.pause();
		view.flip();
		
		
	}

	
	/**
	 * [관리자-교사관리] 교사 조회 (전체) Service 메소드
	 * @throws InterruptedException 
	 */
	@Override
	public void list() {

		view.flip();
		view.title(ViewAdministorTeacher.LIST);
		
		view.columnName(ViewAdministorTeacher.TEACHER);
		view.thinLine();
		DAOAdministorTeacher dao = new DAOAdministorTeacher();
		
		ArrayList<DTOvwAllTeacher> list = dao.AllTeacherlist();
		
		for (DTOvwAllTeacher dto : list) {
			
			System.out.printf("\t\t\t\t\t\t\t%s\t\t%s\t\t%s\t\t%s\n"
								, dto.getSeq()
								, dto.getName()
								, dto.getTel()
								, dto.getSsn());
		}
		view.thickLine();
		dao.close();
		
		System.out.println();
		System.out.println();
		
		MainControllerAdministorTeacher.teacherSelectMenu();
		
	}

	
	/**
	 * [관리자-교사관리] 교사 정보 수정 Service 메소드
	 */
	@Override
	public void edit() {
		
		view.flip();
		view.title(ViewAdministorTeacher.EDIT);
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t정보를 수정할 교사의 고유번호를 입력해주세요.");
		view.input();
		String input = scan.nextLine();
		
		DAOAdministorTeacher dao = new DAOAdministorTeacher();
		
		DTOTeacher dto = dao.searchList(input);
		
		
		view.flip();
		
		view.thickLine();
		view.columnName(ViewAdministorTeacher.TEACHER);
		view.thinLine();
		
		System.out.printf("\t\t\t\t\t\t\t%s\t\t%s\t\t%s\t\t%s\n"
							, dto.getSeq()
							, dto.getName()
							, dto.getTel()
							, dto.getSsn());	
		view.thickLine();
		
		System.out.println();
		System.out.println();
		MainControllerAdministorTeacher.editCheck();
		

		String name = "";
		String tel = "";
		String ssn = "";
		
		if (dto != null) {
			
			name = dto.getName();
			tel = dto.getTel();
			ssn = dto.getSsn();		
		
		}
		
		//TODO 유효성검사 
		
//		System.out.println();
		view.flip();
		view.title(ViewAdministorTeacher.EDIT);
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
		
		
		dto = new DTOTeacher();
		dto.setSeq(input);
		dto.setName(name);
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
			System.out.println("\t\t\t\t\t\t\t\t\t\t교사 정보 수정 완료");
		} else {
			System.out.println("\t\t\t\t\t\t\t\t\t\t교사 정보 수정 실패");
		}
		
		dao.close();
		view.pause();
		view.flip();
		
	}

	
	/**
	 * [관리자-교사관리] 교사 정보 삭제 Service 메소드
	 */
	@Override
	public void del() {
		
		view.flip();
		view.title(ViewAdministorTeacher.DEL);
		System.out.println();
		
		System.out.println("\t\t\t\t\t\t\t\t\t\t정보를 삭제할 교사의 고유번호를 입력해주세요.");
		System.out.print("\t\t\t\t\t\t\t\t\t\t입력 : ");
		String input = scan.nextLine();
		
		DAOAdministorTeacher dao = new DAOAdministorTeacher();
		
		DTOTeacher dto = dao.searchList(input);

		
		view.flip();
		
		view.thickLine();
		view.columnName(ViewAdministorTeacher.TEACHER);
		view.thinLine();
		
		System.out.printf("\t\t\t\t\t\t\t%s\t\t%s\t\t%s\t\t%s\n"
							, dto.getSeq()
							, dto.getName()
							, dto.getTel()
							, dto.getSsn());	
		view.thickLine();
		
		System.out.println();
		System.out.println();
		MainControllerAdministorTeacher.delCheck();
				
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
			System.out.println("\t\t\t\t\t\t\t\t\t\t교사 정보 삭제 완료");			
		} else {
			System.out.println("\t\t\t\t\t\t\t\t\t\t교사 정보 삭제 실패");		
		}
		
		dao.close();
		view.pause();
		view.flip();
		
	}

	
	
	@Override
	public void nameSearch() {

		view.flip();
		view.title(ViewAdministorTeacher.SEARCH);
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t검색할 교사의 이름을 입력해주세요.");
		view.input();
		String input = scan.nextLine();
		
		
		DAOAdministorTeacher dao = new DAOAdministorTeacher();
		
		ArrayList<DTOTeacher> list = dao.nameSearch(input);
		
		System.out.println();
		view.thickLine();
		view.columnName(ViewAdministorTeacher.TEACHER);
		view.thinLine();
		
		
		for (DTOTeacher dto : list) {
			
			System.out.printf("\t\t\t\t\t\t\t%s\t\t%s\t\t%s\t\t%s\n"
								, dto.getSeq()
								, dto.getName()
								, dto.getTel()
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
	 * [관리자-교사관리] 특정 교사 선택 시 세부 정보 조회 Service 메소드
	 */
	@Override
	public void selectedTeacherInfo() {
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t【교사 세부 정보 조회 】");
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t교사 번호 입력 : ");
		String TeacherNum = scan.nextLine();
		
		view.flip();
		
		DAOAdministorTeacher dao = new DAOAdministorTeacher();
		ArrayList<DTOvwSelectedTeacher> selectedList = dao.SelectedTeacherList(TeacherNum);
		
		String basicInfo = "";
		String basicDate = "";
		
		for (DTOvwSelectedTeacher dto : selectedList) {

			
			basicInfo = String.format("\t\t\t\t\t%s\t%s\t\t\t%s"
												, dto.getSeq()
												, dto.getName()
												, dto.getAllocated_course());
			basicDate = String.format("\t\t\t\t\t%s\t\t%s\t\t%s"
												, dto.getAllocated_start()
												, dto.getAllocated_end()
												, dto.getClassroom());
							
		}
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		view.thickLine();
		
		view.columnName(ViewAdministorTeacher.TEACHERALLOCATED);
		view.thinLine();

		System.out.println(basicInfo);
		System.out.println();
		System.out.println();
		
		view.thickLine();
		view.columnName(ViewAdministorTeacher.COURSEDATE);
		view.thinLine();
		System.out.println(basicDate);
		System.out.println();
		System.out.println();
		
		view.thickLine();
		view.columnName(ViewAdministorTeacher.SUBJECTINFO);
		view.thinLine();
		
		for (DTOvwSelectedTeacher dto : selectedList) {
			
			System.out.printf("\t\t\t\t\t%s\t%s\t%-25s\t\t%s\n"
									, dto.getSubject_start()
									, dto.getSubject_end()
									, dto.getSubject_name()
									, dto.getTextbook());
									
			
			
		}

		System.out.println();
		view.thickLine();
		System.out.println();
		
		dao.close();
		
	}

}
