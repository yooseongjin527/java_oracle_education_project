package kr.co.sist.sec.administrator.basicinfo.course;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.sec.administrator.AdminView;
import kr.co.sist.sec.dto.DTOCourse;
import kr.co.sist.sec.main.View;

public class CourseService implements ICourseService {

	private View view;
	private AdminView adminView;
	private Scanner scan;

	public CourseService() {
		view = new View();
		adminView = new AdminView();
		scan = new Scanner(System.in);
	}
	
	@Override
	public void list() {
		System.out.println();
		System.out.println();
		adminView.courseTitle();
		CourseDAO dao = new CourseDAO();
		ArrayList<DTOCourse> list = dao.list();
		
		for (DTOCourse dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%s\n"
									, dto.getSeq()
									, dto.getName());
		}
		
		adminView.line();
		dao.close();
	}

	@Override
	public void search() {
		System.out.println();
		System.out.println();
		adminView.courseTitle(AdminView.SEARCH);
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t검색어 입력 : ");
		String word = scan.nextLine();
		word = word.replace("'", "''");
		System.out.println();
		System.out.println();
		adminView.courseTitle();
		
		CourseDAO dao = new CourseDAO();
		ArrayList<DTOCourse> list = dao.list(word);
		
		for (DTOCourse dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%s\n"
									, dto.getSeq()
									, dto.getName());
		}
		
		adminView.line();
		dao.close();
		System.out.println();
		System.out.println();
		view.thinLine();
		view.pause();
	}
	
	@Override
	public void add() {
		
		System.out.println();
		System.out.println();
		adminView.courseTitle(AdminView.ADD);
		System.out.println();
		
		System.out.print("\t\t\t\t\t\t\t\t\t\t등록할 과정명 : ");
		String name = scan.nextLine();
		name = name.replace("'",  "''");
		
		CourseDAO dao = new CourseDAO();
		DTOCourse dto = new DTOCourse();
		dto.setName(name);
		if (!dto.getName().equals("")) {
			//등록할 과정명이 이미 있는 상태인지 확인!
			dto = dao.exist(name);
			if (dto != null) {
				System.out.println();
				System.out.println();
				System.out.print("\t\t\t\t\t\t\t\t\t\t이미 등록된 과정입니다.");
				view.pause();
			} else {
				int result = dao.add(name);
				
				dao.close();
				
				if (result == 1) {
					System.out.println("\t\t\t\t\t\t\t\t\t\t등록이 완료 되었습니다.");
				} else {
					System.out.println("\t\t\t\t\t\t\t\t\t\t등록이 실패 했습니다.");
				}
				view.pause();
			}
			
		} else {
			view.wrongSel();
		}
		
		
	}

	@Override
	public void edit() {
		
		System.out.println();
		System.out.println();
		adminView.courseTitle(AdminView.EDIT);
		System.out.println();
		
		boolean loop = true;
		while (loop) {
		
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택(수정할 과정 번호) : ");
			String seq = scan.nextLine();
			
			CourseDAO dao = new CourseDAO();
			DTOCourse dto = dao.get(seq);
			
			if (dto != null) {
				String name = "";
				name = dto.getName();
				
				System.out.println();
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t선택한 ‘%s’ 을 수정하시겠습니까? (y/n) ", name);
				String sel = scan.nextLine();
				if (sel.equalsIgnoreCase("y")) {
					System.out.print("\t\t\t\t\t\t\t\t\t\t수정할 과정명 : ");
					String temp = scan.nextLine();
					if (!temp.equals("")) {
						name = temp;
					}
					
					System.out.println();
					System.out.println();
					
					dto = new DTOCourse();
					dto.setSeq(seq);//수정할 번호
					dto.setName(name);
					
					int result = dao.edit(dto);
					
					if (result == 1) {
						System.out.println("\t\t\t\t\t\t\t\t\t\t수정이 완료 되었습니다.");
					} else {
						System.out.println("\t\t\t\t\t\t\t\t\t\t수정이 실패 했습니다.");
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

	@Override
	public void del() {

		System.out.println();
		System.out.println();
		adminView.courseTitle(AdminView.DEL);
		System.out.println();
		
		boolean loop = true;
		while (loop) {
			
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택(삭제할 과정 번호) : ");
			String seq = scan.nextLine();
			
			CourseDAO dao = new CourseDAO();
			DTOCourse dto = dao.get(seq);
			
			if (dto != null) {
				System.out.println();
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t선택한 ‘%s’ 을 삭제하시겠습니까? (y/n) ", dto.getName());
				String sel = scan.nextLine();
				if (sel.equalsIgnoreCase("y")) {
					int result = dao.del(seq);
					
					if (result == 1) {
						System.out.println("\t\t\t\t\t\t\t\t\t\t삭제가 완료 되었습니다.");
					} else {
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
