package kr.co.sist.sec.administrator.coursesubject;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.sec.administrator.AdminView;
import kr.co.sist.sec.administrator.basicinfo.course.CourseDAO;
import kr.co.sist.sec.administrator.basicinfo.course.CourseService;
import kr.co.sist.sec.administrator.basicinfo.subject.SubjectDAO;
import kr.co.sist.sec.administrator.basicinfo.subject.SubjectService;
import kr.co.sist.sec.dto.DTOCourse;
import kr.co.sist.sec.dto.DTOCourseSubject;
import kr.co.sist.sec.dto.DTOCourseSubjectTextbook;
import kr.co.sist.sec.dto.DTOSubject;
import kr.co.sist.sec.main.View;

public class CSService implements ICSService {

	private View view;
	private AdminView adminView;
	private CSView csView;
	private Scanner scan;
	private CourseService cs;
	private SubjectService ss;
	
	public CSService() {
		view = new View();
		adminView = new AdminView();
		csView = new CSView();
		scan = new Scanner(System.in);
		cs = new CourseService();
		ss = new SubjectService();
	}

	@Override
	public void list() {
		System.out.println();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(과정) : ");
		String cseq = scan.nextLine();
		
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[해당 과정의 과목 정보]");
		adminView.subjectSchema();
		CSDAO dao = new CSDAO();
		//과정에 해당하는 과목 가져오기
		ArrayList<DTOCourseSubjectTextbook> list = dao.slistForC(cseq);
		for (DTOCourseSubjectTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%-5s\t%-20s\t%s\n"
									, dto.getSseq()
									, dto.getSname()
									, dto.getTbname());
		}
		adminView.line();
		
		view.pause();
		
	}
	
	@Override
	public void add() {
		System.out.println();
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(과정) : ");
		String cseq = scan.nextLine();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[해당 과정의 과목 정보]");
		adminView.subjectSchema();
		CSDAO dao = new CSDAO();
		//과정에 해당하는 과목 가져오기
		ArrayList<DTOCourseSubjectTextbook> list = dao.slistForC(cseq);
		for (DTOCourseSubjectTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%-5s\t%-20s\t%s\n"
									, dto.getSseq()
									, dto.getSname()
									, dto.getTbname());
		}
		adminView.line();
		
		view.pause();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[과목 정보]");
		adminView.subjectSchema();
		ss.tuple();
		adminView.line();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t※ 뒤로 돌아가려면 '0'을 입력해주세요. ");
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(등록할 과목 번호) : ");
		String sseq = scan.nextLine();
		
		if (!sseq.equals("0")) {
			//해당 과정이름, 과목 이름 담아오기
			DTOCourse dtoc = new DTOCourse();
			DTOSubject dtos = new DTOSubject();
			dtoc.setSeq(cseq);
			dtos.setSeq(sseq);
			CourseDAO cdao = new CourseDAO();
			dtoc = cdao.get(cseq);
			cdao.close();
			SubjectDAO sdao = new SubjectDAO();
			dtos = sdao.get(sseq);
			sdao.close();
			System.out.println();
			System.out.println();
			System.out.printf("\t\t\t\t\t\t\t\t\t\t과정 '%s'에 과목 '%s'를 등록하시겠습니까? (y/n) ", dtoc.getName(), dtos.getName());
			String sel = scan.nextLine();
			
			if (sel.equalsIgnoreCase("y")) {
				DTOCourseSubject dto = new DTOCourseSubject();
				dto.setCourse_seq(cseq);
				dto.setSubject_seq(sseq);
				
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
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(과정 번호) : ");
		String cseq = scan.nextLine();
		
		System.out.println(); 
		System.out.println(); 
		System.out.println("\t\t\t\t\t\t\t\t\t\t[해당 과정의 과목 정보]");
		adminView.subjectSchema();
		CSDAO dao = new CSDAO();
		//과정에 해당하는 과목 가져오기
		ArrayList<DTOCourseSubjectTextbook> list = dao.slistForC(cseq);
		for (DTOCourseSubjectTextbook dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%-5s\t%-20s\t%s\n"
									, dto.getSseq()
									, dto.getSname()
									, dto.getTbname());
		}
		adminView.line();
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t※ 뒤로 돌아가려면 '0'을 입력해주세요. ");
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택(삭제할 과목 번호) : ");
		String sseq = scan.nextLine();
		
		if (!sseq.equals("0")) {
			//해당 과정이름, 과목 이름 담아오기
			DTOCourse dtoc = new DTOCourse();
			DTOSubject dtos = new DTOSubject();
			dtoc.setSeq(cseq);
			dtos.setSeq(sseq);
			CourseDAO cdao = new CourseDAO();
			dtoc = cdao.get(cseq);
			cdao.close();
			SubjectDAO sdao = new SubjectDAO();
			dtos = sdao.get(sseq);
			sdao.close();
			
			System.out.println();
			System.out.println();
			System.out.printf("\t\t\t\t\t\t\t\t\t\t과정 '%s'에 과목 '%s'를 삭제하시겠습니까? (y/n) ", dtoc.getName(), dtos.getName());
			String sel = scan.nextLine();
			
			if (sel.equalsIgnoreCase("y")) {
				DTOCourseSubject dto = new DTOCourseSubject();
				dto.setCourse_seq(cseq);
				dto.setSubject_seq(sseq);
				
				ArrayList<DTOCourseSubject> cslist = dao.findseq(dto);
				
				int result = dao.del(cslist);
				
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
