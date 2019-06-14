package kr.co.sist.sec.administrator.basicinfo.classroom;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.sec.administrator.AdminView;
import kr.co.sist.sec.dto.DTOClassroom;
import kr.co.sist.sec.main.View;

public class ClassroomService implements IClassroomService {

	private View view;
	private AdminView adminView;
	private Scanner scan;

	public ClassroomService() {
		view = new View();
		adminView = new AdminView();
		scan = new Scanner(System.in);
	}
	
	@Override
	public void list() {

		adminView.classroomTitle();
		ClassroomDAO dao = new ClassroomDAO();
		ArrayList<DTOClassroom> list = dao.list();
		
		for (DTOClassroom dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%s\t%s\n"
									, dto.getSeq()
									, dto.getName()
									, dto.getCapacity() == null ? "미등록" : dto.getCapacity());
		}
		
		adminView.line();
		dao.close();
		
	}

	@Override
	public void search() {
		
		System.out.println();
		System.out.println();
		adminView.classroomTitle(AdminView.SEARCH);
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t검색어 입력 : ");
		String word = scan.nextLine();
		word = word.replace("'", "''");
		
		System.out.println();
		System.out.println();
		adminView.classroomTitle();
		
		ClassroomDAO dao = new ClassroomDAO();
		ArrayList<DTOClassroom> list = dao.list(word);
		
		for (DTOClassroom dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%s\t%s\n"
									, dto.getSeq()
									, dto.getName()
									, dto.getCapacity() == null ? "미등록" : dto.getCapacity());
		}
		
		adminView.line();
		dao.close();
		
		view.pause();
		
	}

	@Override
	public void add() {
		System.out.println();
		System.out.println();
		adminView.classroomTitle(AdminView.ADD);
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t등록할 강의실명((ex) 제 7강의실) : ");
		String name = scan.nextLine();
		name = name.replace("'",  "''");
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t등록할 수용 인원((ex) 30) : ");
		String capacity = scan.nextLine();
		
		ClassroomDAO dao = new ClassroomDAO();
		DTOClassroom dto = new DTOClassroom();
		dto.setName(name);
		dto.setCapacity(capacity);
		
		//등록할 강의실명이 이미 있는 상태인지 확인!
		dto = dao.exist(name, capacity);
		if (dto != null) {
			System.out.println();
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t이전에 등록한 이력이 있습니다. 재등록하시겠습니까? (y/n) ");
			String sel = scan.nextLine();
			if (sel.equalsIgnoreCase("y")) {
				int result = dao.addAgain(dto);
				
				dao.close();
				
				if (result == 1) {
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t등록이 완료 되었습니다.");
				} else {
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t등록이 실패 했습니다.");
				}
				view.pause();
			} else {
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t\t새로 등록하시려면 'y', 취소하시려면 'n'를 입력해주세요. (y/n) ");
				if (sel.equalsIgnoreCase("y")) {
					
					int result = dao.add(dto);
					
					dao.close();
					
					if (result == 1) {
						System.out.println();
						System.out.println("\t\t\t\t\t\t\t\t\t\t등록이 완료 되었습니다.");
					} else {
						System.out.println();
						System.out.println("\t\t\t\t\t\t\t\t\t\t등록이 실패 했습니다.");
					}
					view.pause();
				}
				
			}
		} else {
			int result = dao.add(name, capacity);
			
			dao.close();
			
			if (result == 1) {
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t\t등록이 완료 되었습니다.");
			} else {
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t\t등록이 실패 했습니다.");
			}
			view.pause();
		}
		
	}


	@Override
	public void del() {
		System.out.println();
		System.out.println();
		adminView.classroomTitle(3);
		
		boolean loop = true;
		while (loop) {
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택(삭제할 강의실 번호) : ");
			String seq = scan.nextLine();
			
			ClassroomDAO dao = new ClassroomDAO();
			DTOClassroom dto = dao.get(seq);
			
			if (dto != null) {
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t선택한 ‘%s’ 을 삭제하시겠습니까? (y/n) ", dto.getName());
				String sel = scan.nextLine();
				if (sel.equalsIgnoreCase("y")) {
					int result = dao.del(seq);
					
					if (result == 1) {
						System.out.println();
						System.out.println("\t\t\t\t\t\t\t\t\t\t삭제가 완료 되었습니다.");
					} else {
						System.out.println();
						System.out.println("\t\t\t\t\t\t\t\t\t\t삭제가 실패 했습니다.");
					}
					
					dao.close();
					view.pause();
					loop = false;
				}
				
			} else {
				view.wrongSelAndSel();
				String sel = scan.nextLine();
				if (sel.equals("0")) {
					loop = false;
				}
			}
		}
		
	}

}
