package kr.co.sist.sec.administrator.opencourse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import kr.co.sist.sec.administrator.AdminView;
import kr.co.sist.sec.dto.DTOClassroom;
import kr.co.sist.sec.dto.DTOCourse;
import kr.co.sist.sec.dto.DTOOpenCourse;
import kr.co.sist.sec.dto.DTOSubject;
import kr.co.sist.sec.dto.DTOTeacher;
import kr.co.sist.sec.dto.DTOvwOCCSS;
import kr.co.sist.sec.dto.DTOvwOCOSCSS;
import kr.co.sist.sec.main.View;

public class OCService implements IOCService {
	
	private View view;
	private AdminView adminView;
	private OCView ocView;
	private Scanner scan;
	
	public OCService() {
		view = new View();
		adminView = new AdminView();
		ocView = new OCView();
		scan = new Scanner(System.in);
	}
	
	@Override
	public void list() {
		OCDAO dao = new OCDAO();
		ArrayList<DTOOpenCourse> list = dao.list();
		if (list != null) {
			for (DTOOpenCourse dto : list) {
				System.out.printf("%s\t%-50s\t%-50s\t\t%-10s\t%-50s\t%-6s\t%s\n"
						, dto.getSeq()
						, dao.getCourse(dto.getCourse_seq()).getName()
						, dto.getStart_date() + " ~ " + dto.getEnd_date()
						, dao.getClassroom(dto.getClassroom_seq()).getName()
						, !dao.getOpenSubject(dto.getSeq()).isEmpty() ? dao.getOpenSubject(dto.getSeq()).get(0).getSname() + " 외 " + (dao.getOpenSubject(dto.getCourse_seq()).size() - 1) + "과목" : "(미등록)"
						, dao.getTeacher(dto.getTeacher_seq()).getName()
						, dao.getStudent(dto.getSeq()).size() + "명");
			}
		}
		
		dao.close();
	}

	@Override
	public void listByCondition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add() {
		OCDAO dao = new OCDAO();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t[등록 가능한 과정 목록]");
		adminView.courseSchema();
		ArrayList<DTOCourse> list = dao.courseList();
		for (DTOCourse dto : list) {
			System.out.printf("\t\t\t\t\t\t\t%s\t%s\n", dto.getSeq(), dto.getName());
		}
		view.line();
		
		System.out.print("\t\t\t\t\t\t\t선택(과정 번호) : ");
		String cseq = scan.nextLine();
		
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t[등록된 과목 목록]");
		view.line();
		System.out.printf("\t\t\t\t\t\t\t%s\t%s\n", "", "[과목명]");
		view.line();
		ArrayList<DTOSubject> slist = dao.slist(cseq);
		int i = 0;
		for (DTOSubject dto : slist) {
			i++;
			System.out.printf("\t\t\t\t\t\t\t%d\t%s\n", i, dto.getName());
		}
		view.line();
		System.out.println("\t\t\t\t\t\t\t=====================");
		System.out.println("\t\t\t\t\t\t\t1. 계속 진행");
		System.out.println("\t\t\t\t\t\t\t0. 취소");
		System.out.println("\t\t\t\t\t\t\t=====================");
		System.out.print("\t\t\t\t\t\t\t선택(번호) : ");
		String sel = scan.nextLine();
		
		if (sel.equals("1")) {
			boolean loop = true;
			while (loop) {
				System.out.println();
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t※ 취소하시려면 '0'을 입력해주세요.");
				System.out.print("\t\t\t\t\t\t\t과정 시작 년월일 입력((ex) 2019-04-03) : ");
				String input = scan.nextLine(); // 과정 시작 년월일
				String input2 = ""; // 과정 종료 년월일
				String input3 = ""; // 강의실 번호
				String input4 = ""; // 교사 번호
				if (input.equals("0")) {
					System.out.println("\t\t\t\t\t\t\t취소합니다.");
					view.pause();
					loop = false;
				} else {
					//유효 입력인지 확인
					String checkNum = input.substring(0, 4) + input.substring(5, 7) + input.substring(8, 10);
					boolean flag = true;
					for (int j=0; j<checkNum.length(); j++) {
						int dgit = Integer.parseInt(checkNum.substring(j, j+1));
						if (dgit < 0 && dgit > 9) {
							flag = false;
							break;	
						}
					}
					if (flag) {
						boolean loop2 = true;
						while (loop2) {
							System.out.println();
							System.out.println();
							System.out.println("\t\t\t\t\t\t\t[과정 기간]");
							System.out.println("\t\t\t\t\t\t\t==================");
							System.out.println("\t\t\t\t\t\t\t1. N개월");
							System.out.println("\t\t\t\t\t\t\t2. 직접 입력");
							System.out.println("\t\t\t\t\t\t\t0. 뒤로 돌아가기");
							System.out.println("\t\t\t\t\t\t\t==================");
							System.out.print("\t\t\t\t\t\t\t선택(번호) : ");
							sel = scan.nextLine();
							
							if (sel.equals("1")) {
								System.out.println();
								System.out.println();
								System.out.print("\t\t\t\t\t\t\tN개월(숫자) : ");
								int month = scan.nextInt();
								scan.skip("\r\n");
								Calendar c = Calendar.getInstance();
								int y = Integer.parseInt(input.substring(0, 4));
								int m = Integer.parseInt(input.substring(5, 7));
								int d = Integer.parseInt(input.substring(8, 10));
								c.set(y, m - 1, d);
								c.add(Calendar.MONTH, month);
								input2 = String.format("%tF", c);
								
							} else if (sel.equals("2")) {
								System.out.println();
								System.out.println();
								System.out.print("\t\t\t\t\t\t\t과정 종료 년월일 입력((ex) 2019-10-03) : ");
								input2 = scan.nextLine();
								
							} else if (sel.equals("0")) {
								loop = false;
								break;
							} else {
								view.wrongSel();
								continue;
							}
							System.out.println();
							System.out.println();
							System.out.println("\t\t\t\t\t\t\t[등록 가능한 강의실 목록]");
							adminView.classroomSchema();
							ArrayList<DTOClassroom> crlist = dao.classroomList();
							for (DTOClassroom dto : crlist) {
								if (dao.canUse(dto.getSeq(), input, input2)) {
									System.out.printf("\t\t\t\t\t\t\t%s\t%-10s\t%s\n", dto.getSeq(), dto.getName(), dto.getCapacity());
								}
							}
							adminView.line();
							
							System.out.println("\t\t\t\t\t\t\t※ 취소하시려면 '0'을 입력하세요.");
							System.out.print("\t\t\t\t\t\t\t선택(강의실 번호) : ");
							input3 = scan.nextLine();
							
							if (input3.equals("0")) {
								loop = false;
								break;
							}
							
							System.out.println();
							System.out.println();
							System.out.println("\t\t\t\t\t\t\t[등록 가능한 교사 목록]");
							adminView.teacherSchema();
							ArrayList<DTOTeacher> tlist = dao.teacherList();
							for (DTOTeacher dto : tlist) {
								if (dao.capable(dto.getSeq(), cseq, dao.snum(cseq)) && dao.notInCourse(dto.getSeq(), input, input2)) { //강의 가능 과목인지 && 현재 강의중이지 않는지
									System.out.printf("\t\t\t\t\t\t\t%s\t%-10s\t%-7s\t\t%s\n", dto.getSeq(), dto.getName(), dto.getSsn(), dto.getTel());
								}
							}
							adminView.line();
							
							System.out.println("\t\t\t\t\t\t\t※ 취소하시려면 '0'을 입력하세요.");
							System.out.print("\t\t\t\t\t\t\t선택(교사 번호) : ");
							input4 = scan.nextLine();
							
							if (input4.equals("0")) {
								loop = false;
								break;
							}
							
							System.out.println();
							System.out.println();
							System.out.println("\t\t\t\t\t\t\t[입력 정보 확인]");
							System.out.println("\t\t\t\t\t\t\t============================================================================================================================================================================================");
							System.out.printf("\t\t\t\t\t\t\t%-50s\t%-25s\t%-10s\t%-50s\t%s\n"
									, "[과정명]", "[과정기간]", "[강의실]", "[개설 과목]", "[교사명]");
							System.out.println("\t\t\t\t\t\t\t============================================================================================================================================================================================");
							System.out.printf("\t\t\t\t\t\t\t%-50s\t%-50s\t%-10s\t%-50s\t%s\n"
														, dao.getCourse(cseq).getName()
														, (input + "") + " ~ " + (input2 + "")
														, dao.getClassroom(input3).getName()
														, !dao.getSubject(cseq).isEmpty() ? dao.getSubject(cseq).get(0).getSname() + " 외 " + (dao.getSubject(cseq).size() - 1) + "과목" : "(미등록)"
														, dao.getTeacher(input4).getName());
							System.out.println("\t\t\t\t\t\t\t============================================================================================================================================================================================");
							
							System.out.println("\t\t\t\t\t\t\t==========================");
							System.out.println("\t\t\t\t\t\t\t1. 개설 과정 등록");
							System.out.println("\t\t\t\t\t\t\t0. 취소");
							System.out.println("\t\t\t\t\t\t\t==========================");
							System.out.print("\t\t\t\t\t\t\t선택(번호) : ");
							sel = scan.nextLine();
							
							if (sel.equals("1")) {
								System.out.println();
								System.out.println();
								int result = dao.regOpenCourse(cseq, input, input2, input3, input4);
								if (result == 1) {
									System.out.printf("\t\t\t\t\t\t\t'%s'이 개설 과정으로 등록되었습니다.\n", dao.getCourse(cseq).getName());
								} else {
									System.out.println("\t\t\t\t\t\t\t등록 실패");
								}
							}
						}
					} else {
						view.wrongSel();
					}
					
				}
			}

		} else {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t개설과정 등록 진행을 취소하셨습니다.");
			view.pause();
		}
		
		dao.close();
	}

	@Override
	public void edit() {
		System.out.println("\t\t\t\t\t\t\t[개설 과정 정보 수정]");
		
		System.out.println("\t\t\t\t\t\t\t[메뉴]");
		System.out.println("\t\t\t\t\t\t\t=============================");
		System.out.println("\t\t\t\t\t\t\t1. 교육생이 미등록된 개설 과정만 보기");
		System.out.println("\t\t\t\t\t\t\t2. 전체 개설 과정 정보 보기");
		System.out.println("\t\t\t\t\t\t\t=============================");
		String sel = scan.nextLine();
		
		OCDAO dao = new OCDAO();
		if (sel.equals("1")) {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t[교육생이 미등록된 개설 과정 정보]");
			ocView.schema();
			ArrayList<DTOOpenCourse> list = dao.list();
			if (list != null) {
				for (DTOOpenCourse dto : list) {
					if (dao.notStudentRegistered(dto.getSeq())) {
						System.out.printf("%s\t%-50s\t%-30s\t\t%-10s\t%-50s\t%-6s\t%s\n"
								, dto.getSeq()
								, dao.getCourse(dto.getCourse_seq()).getName()
								, dto.getStart_date() + " ~ " + dto.getEnd_date()
								, dao.getClassroom(dto.getClassroom_seq()).getName()
								, !dao.getOpenSubject(dto.getSeq()).isEmpty() ? dao.getOpenSubject(dto.getSeq()).get(0).getSname() + " 외 " + (dao.getOpenSubject(dto.getCourse_seq()).size() - 1) + "과목" : "(미등록)"
								, dao.getTeacher(dto.getTeacher_seq()).getName()
								, dao.getStudent(dto.getSeq()).size() + "명");
					}
				}
			}
			System.out.println("================================================================================================================================================");
		} else {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t[전체 개설 과정 정보]");
			ocView.schema();
			ArrayList<DTOOpenCourse> list = dao.list();
			if (list != null) {
				for (DTOOpenCourse dto : list) {
					System.out.printf("%s\t%-50s\t%-30s\t\t%-10s\t%-50s\t%-6s\t%s\n"
							, dto.getSeq()
							, dao.getCourse(dto.getCourse_seq()).getName()
							, dto.getStart_date() + " ~ " + dto.getEnd_date()
							, dao.getClassroom(dto.getClassroom_seq()).getName()
							, !dao.getOpenSubject(dto.getSeq()).isEmpty() ? dao.getOpenSubject(dto.getSeq()).get(0).getSname() + " 외 " + (dao.getOpenSubject(dto.getCourse_seq()).size() - 1) + "과목" : "(미등록)"
							, dao.getTeacher(dto.getTeacher_seq()).getName()
							, dao.getStudent(dto.getSeq()).size() + "명");
				}
			} else System.out.println("\t\t\t\t\t\t\t등록된 개설 과정 정보 없음");
			System.out.println("================================================================================================================================================");
		}
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t※ 뒤로 돌아가려면 '0'을 입력해주세요.");
		System.out.print("\t\t\t\t\t\t\t선택(수정할 개설 과정 번호) : ");
		String ocseq = scan.nextLine();
		
		if (dao.correct(ocseq)) {
			
			if (dao.existOS(ocseq)) {
				System.out.println("\t\t\t\t\t\t\t[등록된 개설 과목 목록]");
				view.line();
				System.out.printf("%s\t%s\t%s\n", "", "[과목명]", "[과목기간]");
				view.line();
				ArrayList<DTOvwOCOSCSS> list = dao.oslist(ocseq);
				int i = 1;
				if (list != null) {
					for (DTOvwOCOSCSS dto : list) {
						System.out.printf("%d\t%s\t%s\n", i, dto.getSname(), dto.getOsstart_date() + " ~ " + dto.getOsend_date());
						i++;
					}
				} else System.out.println("\t\t\t\t\t\t\t등록된 개설 과목 정보 없음");
				view.line();
			} else {
				System.out.println("[등록된 과목 목록]");
				view.line();
				System.out.printf("\t\t\t\t\t\t\t%s\t%s\n", "", "[과목명]");
				view.line();
				ArrayList<DTOSubject> slist = dao.slist(dao.getCseq(ocseq));
				int i = 0;
				if (slist != null) {
					for (DTOSubject dto : slist) {
						i++;
						System.out.printf("\t\t\t\t\t\t\t%d\t%s\n", i, dto.getName());
					}
				} else System.out.println("\t\t\t\t\t\t\t등록된 과목 정보 없음");
				view.line();
			}
			
			System.out.print("\t\t\t\t\t\t\t계속 진행하시겠습니까? (y/n) ");
			sel = scan.nextLine();
			
			if (sel.equals("y")) {
				
			} else {
				System.out.println("\t\t\t\t\t\t\t개설 과정 정보 수정 진행이 취소됩니다.");
				adminView.pause();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
		} else if (!ocseq.equals("0")) {
			adminView.wrongSel();
		}
		
		dao.close();
	}

	@Override
	public void del() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subjectList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void studentList() {
		// TODO Auto-generated method stub
		
	}

}
