package kr.co.sist.sec.administrator.subjecttextbook;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.sec.administrator.AdminView;
import kr.co.sist.sec.administrator.basicinfo.subject.SubjectDAO;
import kr.co.sist.sec.administrator.basicinfo.subject.SubjectService;
import kr.co.sist.sec.administrator.basicinfo.textbook.TextbookDAO;
import kr.co.sist.sec.administrator.basicinfo.textbook.TextbookService;
import kr.co.sist.sec.dto.DTOCourseSubjectTextbook;
import kr.co.sist.sec.dto.DTOSubject;
import kr.co.sist.sec.dto.DTOTextbook;
import kr.co.sist.sec.main.View;

public class STService implements ISTService {

	private View view;
	private AdminView adminView;
	private STView stView;
	private Scanner scan;
	private SubjectService ss;
	private TextbookService ts;
	
	public STService() {
		view = new View();
		adminView = new AdminView();
		stView = new STView();
		scan = new Scanner(System.in);
		ss = new SubjectService();
		ts = new TextbookService();
	}

	@Override
	public void list() {
		System.out.println();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(과목 번호) : ");
		String sseq = scan.nextLine();
		
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[해당 과목의 교재 정보]");
		adminView.textbookSchema();
		STDAO dao = new STDAO();
		//과목에 해당하는 교재 정보 가져오기
		ArrayList<DTOCourseSubjectTextbook> list = dao.tlistForS(sseq);
		for (DTOCourseSubjectTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t%s\t%s\n"
									, dto.getTbseq()
									, dto.getTbname()
									, dto.getTbauthor() == null ? "(미등록)" : dto.getTbauthor()
									, dto.getTbpublisher() == null ? "(미등록)" : dto.getTbpublisher());
		}
		adminView.line();
		
		view.pause();
		
	}
	
	@Override
	public void add() {
		System.out.println();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(과목 번호) : ");
		String sseq = scan.nextLine();
		
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[해당 과목의 교재 정보]");
		adminView.textbookSchema();
		STDAO dao = new STDAO();
		//과목에 해당하는 교재 정보 가져오기
		ArrayList<DTOCourseSubjectTextbook> list = dao.tlistForS(sseq);
		for (DTOCourseSubjectTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t%s\t%s\n"
									, dto.getTbseq()
									, dto.getTbname()
									, dto.getTbauthor() == null ? "(미등록)" : dto.getTbauthor()
									, dto.getTbpublisher() == null ? "(미등록)" : dto.getTbpublisher());
		}
		adminView.line();
		
		view.pause();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[전체 교재 정보]");
		adminView.textbookSchema();
		ts.tuple();
		adminView.line();
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t※ 뒤로 돌아가려면 '0'을 입력해주세요. ");
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(등록할 교재 번호) : ");
		String tbseq = scan.nextLine();
		
		if (!tbseq.equals("0")) {
			//해당 과목 이름, 교재 이름 담아오기
			DTOSubject dtos = new DTOSubject();
			DTOTextbook dtotb = new DTOTextbook();
			dtos.setSeq(sseq);
			dtotb.setSeq(tbseq);
			SubjectDAO sdao = new SubjectDAO();
			dtos = sdao.get(sseq);
			sdao.close();
			TextbookDAO tbdao = new TextbookDAO();
			dtotb = tbdao.get(tbseq);
			tbdao.close();
			
			System.out.println();
			System.out.println();
			System.out.printf("\t\t\t\t\t\t\t\t\t\t과목 '%s'에 교재 '%s'를 등록하시겠습니까? (y/n) ", dtos.getName(), dtotb.getName());
			String sel = scan.nextLine();
			
			if (sel.equalsIgnoreCase("y")) {
				DTOSubject dto = new DTOSubject();
				dto.setSeq(sseq);
				dto.setText_seq(tbseq);
				
				int result = dao.alreadyRegistCheck(dto);
				
				if (result == 0) {
					result = dao.add(dto);
					
					if (result == 1) {
						System.out.println();
						System.out.println("\t\t\t\t\t\t\t\t\t\t등록이 완료 되었습니다.");
					} else {
						System.out.println();
						System.out.println("\t\t\t\t\t\t\t\t\t\t등록이 실패 했습니다.");
					}
					view.pause();
					
				} else if (result == 1) {
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t이미 등록된 과목입니다.");
					view.pause();
				} else {
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t체크 실패");
				}
				
			} else {
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t\t등록을 취소합니다.");
				view.pause();
			}
		}
		dao.close();
		
	}

	@Override
	public void del() {
		System.out.println();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(과목 번호) : ");
		String sseq = scan.nextLine();
		
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[해당 과목의 교재 정보]");
		adminView.textbookSchema();
		STDAO dao = new STDAO();
		//과목에 해당하는 교재 정보 가져오기
		ArrayList<DTOCourseSubjectTextbook> list = dao.tlistForS(sseq);
		for (DTOCourseSubjectTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%-50s\t%s\t%s\n"
									, dto.getTbseq()
									, dto.getTbname()
									, dto.getTbauthor() == null ? "(미등록)" : dto.getTbauthor()
									, dto.getTbpublisher() == null ? "(미등록)" : dto.getTbpublisher());
		}
		adminView.line();
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t※ 뒤로 돌아가려면 '0'을 입력해주세요. ");
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(삭제할 교재 번호) : ");
		String tbseq = scan.nextLine();
		
		if (!tbseq.equals("0")) {
			//해당 과목 이름, 교재 이름 담아오기
			DTOSubject dtos = new DTOSubject();
			DTOTextbook dtotb = new DTOTextbook();
			dtos.setSeq(sseq);
			dtotb.setSeq(tbseq);
			SubjectDAO sdao = new SubjectDAO();
			dtos = sdao.get(sseq);
			sdao.close();
			TextbookDAO tbdao = new TextbookDAO();
			dtotb = tbdao.get(tbseq);
			tbdao.close();
			
			System.out.println();
			System.out.println();
			System.out.printf("\t\t\t\t\t\t\t\t\t\t과목 '%s'에 교재 '%s'를 삭제하시겠습니까? (y/n) ", dtos.getName(), dtotb.getName());
			String sel = scan.nextLine();
			
			if (sel.equalsIgnoreCase("y")) {
				DTOSubject dto = new DTOSubject();
				dto.setSeq(sseq);
				dto.setText_seq(tbseq);
				
				int result = dao.del(dto);
				
				if (result == 1) {
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t삭제가 완료 되었습니다.");
				} else {
					System.out.println();
					System.out.println("\t\t\t\t\t\t\t\t\t\t삭제가 실패 했습니다.");
				}
				view.pause();
				
			} else {
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t\t삭제를 취소합니다.");
				view.pause();
			}
		}
		dao.close();
		
	}

}
