package kr.co.sist.sec.administrator.basicinfo.textbook;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.sec.administrator.AdminView;
import kr.co.sist.sec.dto.DTOCourse;
import kr.co.sist.sec.dto.DTOTextbook;
import kr.co.sist.sec.main.View;

public class TextbookService implements ITextbookService {

	private View view;
	private AdminView adminView;
	private Scanner scan;

	public TextbookService() {
		view = new View();
		adminView = new AdminView();
		scan = new Scanner(System.in);
	}
	
	@Override
	public void list() {
		System.out.println();
		System.out.println();
		adminView.textbookTitle();
		TextbookDAO dao = new TextbookDAO();
		ArrayList<DTOTextbook> list = dao.list();
		for (DTOTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t%s\t%s\n"
									, dto.getSeq()
									, dto.getName()
									, dto.getAuthor() == null ? "(미등록)" : dto.getAuthor()
									, dto.getPublisher() == null ? "(미등록)" : dto.getPublisher());
		}
		
		adminView.sline();
		dao.close();
		
	}
	
	public void tuple() {
		TextbookDAO dao = new TextbookDAO();
		ArrayList<DTOTextbook> list = dao.list();
		
		for (DTOTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t%s\t%s\n"
									, dto.getSeq()
									, dto.getName()
									, dto.getAuthor() == null ? "(미등록)" : dto.getAuthor()
									, dto.getPublisher() == null ? "(미등록)" : dto.getPublisher());
		}
		dao.close();
	}

	@Override
	public void search() {
		System.out.println();
		System.out.println();
		adminView.textbookTitle(AdminView.SEARCH);
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t검색어 입력 : ");
		String word = scan.nextLine();
		word = word.replace("'", "''");
		
		System.out.println();
		System.out.println();
		adminView.textbookTitle();
		
		TextbookDAO dao = new TextbookDAO();
		ArrayList<DTOTextbook> list = dao.list(word);
		
		for (DTOTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%-30s\t%s\t%s\n"
								, dto.getSeq()
								, dto.getName()
								, dto.getAuthor()
								, dto.getPublisher());
		}
		
		adminView.line();
		dao.close();
		
		view.pause();
		
	}

	@Override
	public void add() {
		System.out.println();
		System.out.println();
		adminView.textbookTitle(AdminView.ADD);
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t등록할 교재명 : ");
		String name = scan.nextLine();
		name = name.replace("'",  "''");
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t등록할 저자명 : ");
		String author = scan.nextLine();
		author = author.replace("'",  "''");
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t등록할 출판사명 : ");
		String publisher = scan.nextLine();
		publisher = publisher.replace("'",  "''");
		
		TextbookDAO dao = new TextbookDAO();
		DTOTextbook dto = new DTOTextbook();
		dto.setName(name);
		dto.setAuthor(author);
		
		dto.setPublisher(publisher);
		
		//등록할 교재명이 이미 있는 상태인지 확인!
		dto = dao.exist(name);
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
			int result = dao.add(name, author, publisher);
			
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
	public void edit() {
		System.out.println();
		System.out.println();
		adminView.textbookTitle(AdminView.EDIT);
		
		boolean loop = true;
		while (loop) {
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택(수정할 교재 번호) : ");
			String seq = scan.nextLine();
			
			TextbookDAO dao = new TextbookDAO();
			DTOTextbook dto = dao.get(seq);
			
			if (dto != null) {
				String name = "";
				String author = "";
				String publisher = "";
			
				name = dto.getName();
				if (dto.getAuthor() != null) {
					author = dto.getAuthor();
				}
				if (dto.getPublisher() != null) {
					publisher = dto.getPublisher();
				}
				
				System.out.println();
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t선택한 ‘%s’ 을 수정하시겠습니까? (y/n) ", name);
				String sel = scan.nextLine();
				if (sel.equalsIgnoreCase("y")) {
					
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t※ 수정을 원하지 않으면 '엔터'를 입력해주세요.");
					
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t기존 교재명 : " + name);
					System.out.print("\t\t\t\t\t\t\t\t\t\t수정할 교재명 : ");
					String temp = scan.nextLine();
					if (!temp.equals("")) {
						name = temp;
					}
					
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t기존 저자 : " + author);
					System.out.print("\t\t\t\t\t\t\t\t\t\t수정할 저자 : ");
					temp = scan.nextLine();
					if (!temp.equals("")) {
						author = temp;
					}
					
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t기존 출판사 : " + publisher);
					System.out.print("\t\t\t\t\t\t\t\t\t\t수정할 출판사 : ");
					temp = scan.nextLine();
					if (!temp.equals("")) {
						publisher = temp;
					}
					
					
					dto = new DTOTextbook();
					dto.setSeq(seq);//수정할 번호
					dto.setName(name);
					dto.setAuthor(author);
					dto.setPublisher(publisher);
					System.out.println();
					System.out.print("\t\t\t\t\t\t\t\t\t\t수정하시겠습니까? (y/n) ");
					sel = scan.nextLine();
					
					if (sel.equalsIgnoreCase("y")) {
						int result = dao.edit(dto);
						
						if (result == 1) {
							System.out.println();
							System.out.println("\t\t\t\t\t\t\t\t\t\t수정이 완료 되었습니다.");
						} else {
							System.out.println();
							System.out.println("\t\t\t\t\t\t\t\t\t\t수정이 실패 했습니다.");
						}
						
						dao.close();
						view.pause();
						loop = false;
					} else {
						System.out.println();
						System.out.print("\t\t\t\t\t\t\t\t\t\t수정을 취소합니다. 다시 입력하시려면 '엔터'를, 취소하시려면 '0'을 입력해주세요. ");
						sel = scan.nextLine();
						if (sel.equals("0")) {
							loop = false;
						}
					}
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
		adminView.textbookTitle(AdminView.DEL);
		
		boolean loop = true;
		while (loop) {
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t※ 취소하시려면 '0'을 입력해주세요.");
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택(삭제할 교재 번호) : ");
			String seq = scan.nextLine();
			
			TextbookDAO dao = new TextbookDAO();
			DTOTextbook dto = dao.get(seq);
			
			if (dto != null) {
				System.out.println();
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
				view.cancelSel();
				String sel = scan.nextLine();
				if (sel.equals("0")) {
					loop = false;
				}
			}
		}
		
	}

}
