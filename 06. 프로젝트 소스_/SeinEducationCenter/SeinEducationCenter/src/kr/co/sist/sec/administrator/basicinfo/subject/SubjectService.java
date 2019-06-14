package kr.co.sist.sec.administrator.basicinfo.subject;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.sec.administrator.AdminView;
import kr.co.sist.sec.administrator.basicinfo.course.CourseDAO;
import kr.co.sist.sec.administrator.basicinfo.textbook.TextbookDAO;
import kr.co.sist.sec.administrator.basicinfo.textbook.TextbookService;
import kr.co.sist.sec.dto.DTOCourse;
import kr.co.sist.sec.dto.DTOSubject;
import kr.co.sist.sec.dto.DTOSubjectTextbook;
import kr.co.sist.sec.dto.DTOTextbook;
import kr.co.sist.sec.main.View;

public class SubjectService implements ISubjectService {

	private View view;
	private AdminView adminView;
	private Scanner scan;

	public SubjectService() {
		view = new View();
		adminView = new AdminView();
		scan = new Scanner(System.in);
	}

	@Override
	public void list() {
		System.out.println();
		System.out.println();
		adminView.subjectTitle();
		SubjectDAO dao = new SubjectDAO();
		ArrayList<DTOSubjectTextbook> list = dao.list();

		for (DTOSubjectTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t\t%s\n", dto.getSseq(), dto.getSname(),
					dto.getTname() == null ? "(미등록)" : dto.getTname());
		}

		adminView.sline();
		dao.close();
	}

	public void tuple() {
		SubjectDAO dao = new SubjectDAO();
		ArrayList<DTOSubjectTextbook> list = dao.list();

		for (DTOSubjectTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t\t%s\n", dto.getSseq(), dto.getSname(),
					dto.getTname() == null ? "(미등록)" : dto.getTname());
		}
		dao.close();
	}

	@Override
	public void search() {
		System.out.println();
		System.out.println();
		adminView.subjectTitle(AdminView.SEARCH);
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t검색어 입력 : ");
		String word = scan.nextLine();
		word = word.replace("'", "''");

		adminView.subjectTitle();

		SubjectDAO dao = new SubjectDAO();
		ArrayList<DTOSubjectTextbook> list = dao.list(word);

		for (DTOSubjectTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t\t%s\n", dto.getSseq(), dto.getSname(),
					dto.getTname() == null ? "(미등록)" : dto.getTname());
		}
		adminView.sline();
		dao.close();

		view.pause();

	}

	@Override
	public void add() {
		System.out.println();
		System.out.println();
		adminView.subjectTitle(AdminView.ADD);
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t등록할 과목명 : ");
		String name = scan.nextLine();
		name = name.replace("'", "''");

		SubjectDAO dao = new SubjectDAO();
		DTOSubjectTextbook dto = new DTOSubjectTextbook();
		dto.setSname(name);

		// 등록할 과정명이 이미 있는 상태인지 확인!
		ArrayList<DTOSubjectTextbook> list = dao.exist(name);
		System.out.println();
		System.out.println();
		System.out.printf("\t\t\t\t\t\t\t\t\t\t['%s'의 등록 이력]\n", name);
		adminView.subjectSchema();
		if (!list.isEmpty()) {
			for (DTOSubjectTextbook tempDto : list) {
				System.out.printf("%s\t%-50s\t%s\n", tempDto.getSseq(), tempDto.getSname(),
						tempDto.getTname() == null ? "(미등록)" : tempDto.getTname());
			}
		}
		adminView.sline();

		System.out.print("\t\t\t\t\t\t\t\t\t\t등록하시겠습니까? (y/n) ");
		String sel = scan.nextLine();

		if (sel.equalsIgnoreCase("y")) {

			// 전체 교재 정보
			TextbookService ts = new TextbookService();
			ts.list();

			System.out.println("\t\t\t\t\t\t\t\t\t\t※ 교재 등록을 원하지 않으면 '엔터'를 입력해주세요.");
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택(등록할 교재 번호) : ");
			String seq = scan.nextLine();
			if (!seq.equals("")) {

				dto.setTseq(seq);

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
			} else {
				dto.setTseq(seq);

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
		} else {
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t등록을 취소하셨습니다.");
			view.pause();
		}

	}

	@Override
	public void edit() {
		System.out.println();
		System.out.println();
		adminView.subjectTitle(AdminView.EDIT);

		boolean loop = true;
		while (loop) {

			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택(수정할 과목 번호) : ");
			String seq = scan.nextLine();

			SubjectDAO dao = new SubjectDAO();
			DTOSubject dto = dao.get(seq);

			if (dto != null) {
				//// 여기서부터

				String name = "";
				name = dto.getName();

				System.out.println();
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t선택한 ‘%s’ 을 수정하시겠습니까? (y/n) ", name);
				String sel = scan.nextLine();
				if (sel.equalsIgnoreCase("y")) {
					System.out.println();
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t※ 수정을 원하지 않으면 '엔터'를 입력해주세요.");

					System.out.println();
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t기존 과목명 : " + name);
					System.out.print("\t\t\t\t\t\t\t\t\t\t수정할 과목명 : ");
					String temp = scan.nextLine();
					if (!temp.equals("")) {
						name = temp;
					}
					System.out.println();
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t[선택 과정 관련 교재 정보]");
					adminView.textbookSchema();

					ArrayList<DTOSubjectTextbook> list = dao.canRegistList(seq);
					for (DTOSubjectTextbook dtoST : list) {
						System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t%s\t\t%s\n", dtoST.getTseq(), dtoST.getTname(),
								dtoST.getTauthor(), dtoST.getTpublisher());
					}
					adminView.sline();
					System.out.println();
					System.out.println();
					System.out.print("\t\t\t\t\t\t\t\t\t\t전체 교재정보를 보시려면 'a'를, 계속 진행하시려면 '엔터'를 입력해주세요. ");
					sel = scan.nextLine();
					if (sel.equalsIgnoreCase("a")) {
						// 전체 교재 정보
						adminView.textbookTitle();
						TextbookDAO tbDao = new TextbookDAO();
						ArrayList<DTOTextbook> tbList = tbDao.list();

						for (DTOTextbook tbDto : tbList) {
							System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t\t\t%s\t\t%s\n", tbDto.getSeq(), tbDto.getName(),
									tbDto.getAuthor(), tbDto.getPublisher());
						}
						adminView.sline();
						tbDao.close();

					}

					String text_seq = "";
					text_seq = dto.getText_seq();

					System.out.println("\t\t\t\t\t\t\t\t\t\t기존 교재 번호 : "
							+ (dto.getText_seq() == null ? "(미등록)" : dto.getText_seq()));
					System.out.print("\t\t\t\t\t\t\t\t\t\t선택(등록할 교재 번호) : ");
					temp = scan.nextLine();

					if (!temp.equals("")) {
						text_seq = temp;
					}

					dto = new DTOSubject();
					dto.setSeq(seq);// 수정할 번호
					dto.setName(name);
					dto.setText_seq(text_seq);

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
		adminView.subjectTitle(AdminView.DEL);

		boolean loop = true;
		while (loop) {
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택(삭제할 과목 번호) : ");
			String seq = scan.nextLine();

			SubjectDAO dao = new SubjectDAO();
			DTOSubject dto = dao.get(seq);

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
				view.wrongSelAndSel();
				String sel = scan.nextLine();
				if (sel.equals("0")) {
					loop = false;
				}
			}
		}

	}

}
