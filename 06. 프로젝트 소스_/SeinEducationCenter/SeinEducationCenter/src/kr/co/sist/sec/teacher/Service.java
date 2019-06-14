package kr.co.sist.sec.teacher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

import kr.co.sist.sec.dto.DTOAllot;
import kr.co.sist.sec.dto.DTOAllotInformation;
import kr.co.sist.sec.dto.DTOAnswerInformation;
import kr.co.sist.sec.dto.DTOCourseInformation;
import kr.co.sist.sec.dto.DTOLogin;
import kr.co.sist.sec.dto.DTOOpenSubject;
import kr.co.sist.sec.dto.DTOPerformanceTest;
import kr.co.sist.sec.dto.DTOQnA;
import kr.co.sist.sec.dto.DTOQuestionInformation;
import kr.co.sist.sec.dto.DTOScore;
import kr.co.sist.sec.dto.DTOScoreInformation;
import kr.co.sist.sec.dto.DTOTeacher;
import kr.co.sist.sec.dto.DTOTeacherEvaluationInformation;
import kr.co.sist.sec.dto.DTOTeacherEvaluationRate;
import kr.co.sist.sec.dto.DTOTeacher_2;
import kr.co.sist.sec.dto.DTOTeacher_3;
import kr.co.sist.sec.dto.DTOTeacher_4;
import kr.co.sist.sec.dto.DTOWrittenTest;
import kr.co.sist.sec.main.View;

public class Service implements IService {

	private View view;
	private static Scanner scan;
	private static TeacherView tview;

	public Service() {
		view = new View();
		tview = new TeacherView();
		scan = new Scanner(System.in);
	}

	@Override
	public void login(int n) {
		System.out.println();
		System.out.println();
		view.loginTitle(n);
		
		boolean loop = true;
		boolean loop2 = true;
		while (loop && loop2) {
			
			System.out.println();
			
			System.out.print("\t\t\t\t\t\t\t\t\t\tID : ");
			String id = scan.nextLine();
			id = id.replace("'", "''");
			
			System.out.print("\t\t\t\t\t\t\t\t\t\tPW : ");
			String pw = scan.nextLine();
			pw = pw.replace("'", "''");
			
			DAO dao = new DAO();
			DTOLogin dto = dao.get(id);
			
			if (dto != null && pw.equals(dto.getPw())) {
				
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t\t로그인 성공!");
				
				//id로 부터 name, seq 얻기
				ArrayList<Integer> list = new ArrayList<Integer>();
				for (int i=0; i<10; i++) {
					int indexOf = id.indexOf(i+"");
					
					if (indexOf != -1) {
						list.add(indexOf);
					}
				}
				
				int min = Collections.min(list);
				
				int l = id.length();
				String seq = id.substring(min, l);
				//
				
				dao.close();
				
				TeacherController tc = new TeacherController();
				loop2 = tc.teacher(seq);
				
			} else {
				view.noExistAccount();
				dao.close();
				String sel = scan.nextLine();
				if (sel.equals("0")) {
					loop = false;
				}
			}
		}
		
	}

	@Override
	public void findId(int n) {
		// id찾기 > name, tel 입력받기
		System.out.println();
		view.findTitle(n);
		
		boolean loop = true;
		while (loop) {
			
			System.out.println();
			
			System.out.print("\t\t\t\t\t\t\t\t\t\t이름 : ");
			String name = scan.nextLine();
			name = name.replace("'", "''");
			
			System.out.print("\t\t\t\t\t\t\t\t\t\t전화번호((ex) 010-1234-1234) : ");
			String tel = scan.nextLine();
			tel = tel.replace("'", "''");
			
			DTOTeacher dto = new DTOTeacher();
			dto.setName(name);
			dto.setTel(tel);
			
			DAO dao = new DAO();
			
			dto = dao.getSeqFromNameAndTel(dto);
			
			if (dto != null) {
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t%s님의 아이디는 '%s' 입니다.\n", dto.getName(), dto.getName() + dto.getSeq());
				
				dao.close();
				loop = false;
				view.pause();
			} else {
				view.findFail("아이디");
				dao.close();
				String sel = scan.nextLine();
				if (sel.equals("0")) {
					loop = false;
				}
			}
		}
		
	}

	@Override
	public void findPw(int n) {
		// pw찾기 > id 입력받기
		System.out.println();
		view.findTitle(n);
		
		boolean loop = true;
		while (loop) {
			
			System.out.println();
			
			System.out.print("\t\t\t\t\t\t\t\t\t\t아이디 : ");
			String id = scan.nextLine();
			id = id.replace("'", "''");
			
			//id로 부터 name, seq 얻기
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i=0; i<10; i++) {
				int indexOf = id.indexOf(i+"");
				
				if (indexOf != -1) {
					list.add(indexOf);
				}
			}
			
			if (!list.isEmpty()) {
				int min = Collections.min(list);
				
				int l = id.length();
				String name = id.substring(0, min);
				String seq = id.substring(min, l);
				
				DTOTeacher dto = new DTOTeacher();
				dto.setName(name);
				dto.setSeq(seq);
				
				DAO dao = new DAO();
				dto = dao.getPwFromId(dto);
				
				if (dto != null) {
					System.out.println();
					System.out.printf("\t\t\t\t\t\t\t\t\t\t%s님의 비밀번호는 '%s' 입니다.\n", dto.getName(), dto.getSsn());
					
					dao.close();
					loop = false;
					view.pause();
				} else {
					view.findFail("비밀번호");
					dao.close();
					String sel = scan.nextLine();
					if (sel.equals("0")) {
						loop = false;
					}
				}
			} else {
				view.findFail("비밀번호");
				String sel = scan.nextLine();
				if (sel.equals("0")) {
					loop = false;
				}
			}
		}
			
		
	}
	
	public void Allot(String seq) {
		
		boolean loop = true;
		while(loop) {
			
		System.out.println("[배점 관리]");
		tview.course();
		
		DAO dao = new DAO();
		
		ArrayList<DTOCourseInformation> list = dao.courseList(seq);
		
		
		for (DTOCourseInformation dto : list) {
			System.out.printf("     %s\t\t%s\t%s\n"
								, dto.getSeq()
								, dto.getCourse_name()
								, dto.getCourse_period());
		}
		tview.thickLine();
		view.pause();
		
		System.out.print("배점 관리할 과정 선택(과정번호): ");
		String sel = scan.nextLine();
		
		if (sel.equals("1") || sel.equals("2") || sel.equals("3") || sel.equals("4") || sel.equals("5"))  {
			
			
			ArrayList<DTOAllotInformation> subjectlist = dao.subjectList();
			tview.thickLine();			
			System.out.println("[번호]\t[과정명]\t\t\t\t\t[과정기간]\t\t[강의실]\t[과목명]\t\t[과목기간]\t\t[교재명]\t\t\t\t\t [강의종료여부]");
			tview.thickLine();
			for (DTOAllotInformation dto : subjectlist) {
				System.out.printf("   %s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n"
								, dto.getNum()
								, dto.getCourse_name()
								, dto.getCourse_period()
								, dto.getClassroom()
								, dto.getSubject_name()
								, dto.getSubject_period()
								, dto.getTextbook_name()
								, dto.getSubject_end());
			}
			tview.thickLine();
			System.out.println();
			dao.close();
		}
			else {
				System.out.println();
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				System.out.println();
			}
			
			
				boolean loop2 = true;
				while (loop2) {
				tview.allot();
				
				sel = scan.nextLine();
				
				if(sel.equals("1")) allot_select();
				else if(sel.equals("2")) allot_input();
				else if(sel.equals("3")) allot_edit();
				else if(sel.equals("4")) test_add();
				else if(sel.equals("5")) loop2 = false;
				
				else { 
					//System.out.println();
					//view.wrongSel();
				}
			}
				
		loop = false;
		 
		}
		
	}

	private void test_add() {
		
		System.out.println();
		
		DAO dao = new DAO();
		
		System.out.print("시험 등록할 과목 선택(번호): ");
		int sel = scan.nextInt();
		scan.nextLine();
		
		tview.test_add();
		
		
		int sel2 = scan.nextInt();
		scan.nextLine();
		
		if(sel2 == 1) {
		System.out.println();
		System.out.print("필기시험 등록할 문제 번호 입력(1~10)  : ");
		String sel3 = scan.nextLine();
		
		
		DTOWrittenTest dto = dao.getWrittenTest(sel3);
			
		 String question = "";
		 String answer = "";
		 String writtentest_date = "";
		 
			if(dto != null) {
				
				question = dto.getQuestion();
				answer = dto.getAnswer();
			
			}
		
			
			System.out.print("문제 : ");
			String temp = scan.nextLine();
			
			if (!temp.equals("")) {
				question = temp; 
			}
			
			System.out.print("답 : ");
			temp = scan.nextLine();
			
			if (!temp.equals("")) {
				answer = temp; 
			}
			
			DTOOpenSubject dto2 = dao.getWrittenTest_date(sel3);	
			
			
			System.out.print("등록 날짜 ex)19/04/18 : ");
			temp = scan.nextLine();
			
			
			if (!temp.equals("")) {
				writtentest_date = temp; 
			}
			
		
				dto = new DTOWrittenTest();
				
				dto.setSeq(sel3);//입력할 번호(where)
				dto.setQuestion(question);
				dto.setAnswer(answer);
				
			
				dto2 = new DTOOpenSubject();
				
				dto2.setSeq(sel3);
				dto2.setWrittentest_date(writtentest_date);
				
				int result = dao.WTInput(dto);
				int result2 = dao.WTDInput(dto2);
				
				
				if (result == 1 && result2 == 1) {
					System.out.println("[필기시험 등록 완료]");
					System.out.println();
				} else {
					System.out.println("[필기시험 등록 실패]");
				}
				
					dao.close();
				
				
		 
		 
		} 
		
		else if(sel2 == 2) {
			
			
			System.out.println();
			System.out.print("실기시험 등록할 문제 번호 입력(1~10)  : ");
			String sel3 = scan.nextLine();
			
			DTOPerformanceTest dto = dao.getPerformanceTest(sel3);
				
			 String question = "";
			 String answer = "";
			 String performancetest_date = "";
			 
				if(dto != null) {
					
					question = dto.getQuestion();
					answer = dto.getAnswer();
				
				}
			
				
				System.out.print("문제 : ");
				String temp = scan.nextLine();
				
				if (!temp.equals("")) {
					question = temp; 
				}
				
				System.out.print("답 : ");
				temp = scan.nextLine();
				
				if (!temp.equals("")) {
					answer = temp; 
				}
				
				DTOOpenSubject dto2 = dao.PerformanceTest_date(sel3);	
				
				
				System.out.print("등록 날짜 ex)19/04/18 : ");
				temp = scan.nextLine();
				
				
				if (!temp.equals("")) {
					performancetest_date = temp; 
				}
				
			
					dto = new DTOPerformanceTest();
					
					dto.setSeq(sel3);//입력할 번호(where)
					dto.setQuestion(question);
					dto.setAnswer(answer);
					
				
					dto2 = new DTOOpenSubject();
					
					dto2.setSeq(sel3);
					dto2.setPerformancetest_date(performancetest_date);
					
					int result = dao.PTInput(dto);
					int result2 = dao.PTDInput(dto2);
					
					
					if (result == 1 && result2 == 1) {
						System.out.println("[실기시험 등록 완료]");
						System.out.println();
					} else {
						System.out.println("[실기시험 등록 실패]");
					}
					
						dao.close();
					
		} 
		
		else {
			System.out.println();
		}
		
		
	}

	private void allot_edit() {
		
		System.out.println();
		
		DAO dao = new DAO();
		
		System.out.print("배점 수정할 과목 선택(번호): ");
		int sel = scan.nextInt();
		
		DTOAllot dto = dao.getAllot(sel);
		
		int attendance = 0;
		int writtentest = 0;
		int performancetest = 0;
		
		if(dto != null) {
			
			attendance = Integer.parseInt(dto.getAttendance_allot());
			writtentest = Integer.parseInt(dto.getWrittentest_allot());
			performancetest = Integer.parseInt(dto.getPerformancetest_allot());
			
		}
		
		System.out.print("출석 : ");
		int temp = scan.nextInt();
		
		if (temp >= 20) {
			attendance = temp; 
		}
		
		System.out.print("필기시험 : ");
		temp = scan.nextInt();
		
		if (temp >= 0) {
			writtentest = temp; 
		}
		
		System.out.print("실기시험 : ");
		temp = scan.nextInt();
		scan.nextLine();
		
		if (temp >= 0) {
			performancetest = temp; 
		}
		
		if(attendance+writtentest+performancetest == 100) {
			dto = new DTOAllot();
			
			dto.setSeq(sel + "");//입력할 번호(where)
			dto.setAttendance_allot(attendance + "");
			dto.setWrittentest_allot(writtentest + "");
			dto.setPerformancetest_allot(performancetest + "");
			
			int result = dao.edit(dto);
			
			if (result == 1) {
				System.out.println("[배점 수정 완료]");
				System.out.println();
			} else {
				System.out.println("[배점 수정 실패]");
			}
			
				dao.close();
			
			} else {
				System.out.println();
				System.out.println("잘못 입력하셨습니다. 총 배점 합은 100점이 되어야 합니다.");
				
			}
	
	}

	private void allot_input() {
		
		System.out.println();
		
		DAO dao = new DAO();
		
		System.out.print("배점 입력할 과목 선택(번호): ");
		int sel = scan.nextInt();
	
		
		DTOAllot dto = dao.getAllot(sel);
		
		int attendance = 0;
		int writtentest = 0;
		int performancetest = 0;
		
		if(dto != null) {
			
			attendance = Integer.parseInt(dto.getAttendance_allot());
			writtentest = Integer.parseInt(dto.getWrittentest_allot());
			performancetest = Integer.parseInt(dto.getPerformancetest_allot());
			
		}
		
		System.out.print("출석 : ");
		int temp = scan.nextInt();
		
		
		if (temp >= 20) {
			attendance = temp; 
		}
		
		System.out.print("필기시험 : ");
		temp = scan.nextInt();
		
		
		if (temp >= 0) {
			writtentest = temp; 
		}
		
		System.out.print("실기시험 : ");
		temp = scan.nextInt();
		scan.nextLine();
		
		if (temp >= 0) {
			performancetest = temp; 
		}
		
		if(attendance+writtentest+performancetest == 100) {
		dto = new DTOAllot();
		
		dto.setSeq(sel + "");//입력할 번호(where)
		dto.setAttendance_allot(attendance + "");
		dto.setWrittentest_allot(writtentest + "");
		dto.setPerformancetest_allot(performancetest + "");
		
		int result = dao.edit(dto);
		
		if (result == 1) {
			System.out.println("[배점 입력 완료]");
			System.out.println();
		} else {
			System.out.println("[배점 입력 실패]");
		}
		
			dao.close();
		
		} else {
			System.out.println();
			System.out.println("잘못 입력하셨습니다. 총 배점 합은 100점이 되어야 합니다.");
			
		}
		
		
	}

	private void allot_select() {
		
		System.out.println();
		
		
		DAO dao = new DAO();
		
	
		System.out.print("배점 확인할 과목 선택(번호): ");
		String sel = scan.nextLine();
		
		
		ArrayList<DTOAllotInformation> allotlist = dao.subjectList(sel);
		
		
		ArrayList<DTOAllotInformation> allotlist2 = dao.subjectList();

		boolean isValid;
			
		for(DTOAllotInformation dto : allotlist2) {
			
			if (sel.equals(dto.getNum()) && dto.getSubject_end().equals("Y")) {
				
				isValid = true;
				tview.thickLine();
				System.out.println("[과목명]\t[출석]\t[필기]\t[실기]");
				
				for(DTOAllotInformation dto2 : allotlist) {
					System.out.printf("%s  %d\t  %d\t  %d\n"
							,dto2.getSubject_name()
							,dto2.getAttendance_allot()
							,dto2.getWrittentest_allot()
							,dto2.getPerformancetest_allot());
				}
				tview.thickLine();
				System.out.println();
				
			
				
			} else if(sel.equals(dto.getNum()) && dto.getSubject_end().equals("N")) {
				System.out.println();
				System.out.println("선택한 과목은 강의가 종료되지 않아 배점을 확인 할 수 없습니다.");
				System.out.println();
				
			}
			else {
				

				
			}
			
		}
		
		
		dao.close();
	}


	@Override
	public void Score(String seq) {
		
		boolean loop = true;
		while(loop) {
		System.out.println("[성적 관리]");
		tview.course();
		
		DAO dao = new DAO();
		
		ArrayList<DTOCourseInformation> list = dao.courseList(seq);
		
		
		for (DTOCourseInformation dto : list) {
			System.out.printf("     %s\t\t%s\t%s\n"
								, dto.getSeq()
								, dto.getCourse_name()
								, dto.getCourse_period());
		}
		tview.thickLine();
		view.pause();
		
		System.out.print("성적 관리할 과정 선택(과정번호): ");
		String sel = scan.nextLine();
		
		if (sel.equals("1") || sel.equals("2") || sel.equals("3") || sel.equals("4") || sel.equals("5"))  {
			
			
			ArrayList<DTOAllotInformation> subjectlist = dao.subjectList();
			tview.thickLine();
			System.out.println("[번호]\t[과목명]\t\t[과목기간]\t\t[교재명]\t\t\t\t          [강의종료여부]\t[출석배점]\t[필기배점]\t[실기배점]");
			tview.thickLine();
			for (DTOAllotInformation dto : subjectlist) {
				System.out.printf("   %s\t%s\t%s\t%s\t%s\t\t    %s\t\t    %s\t\t    %s\n"
								, dto.getNum()
								, dto.getSubject_name()
								, dto.getSubject_period()
								, dto.getTextbook_name()
								, dto.getSubject_end()
								, dto.getAttendance_allot()
								, dto.getWrittentest_allot()
								, dto.getPerformancetest_allot());
			}
			tview.thickLine();
			System.out.println();
			dao.close();
		}
			else {
				System.out.println();
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				System.out.println();
			}
			
		
		
		
		boolean loop2 = true;
		while (loop2) {
		tview.score();
		
		sel = scan.nextLine();
		
		if(sel.equals("1")) score_select();
		else if(sel.equals("2")) score_input();
		else if(sel.equals("3")) score_edit();
		else if(sel.equals("4")) loop2 = false;
		
		else { 
			//System.out.println();
			//view.wrongSel();
			}
		}
		
			loop = false;
		}

	}

	private void score_edit() {
		
		
		//tview.score();
		
		System.out.println();
		
		DAO dao = new DAO();
	
		System.out.print("성적 수정할 과목 선택(번호): ");
		String sel = scan.nextLine();
		
		
		ArrayList<DTOScoreInformation> scorelist = dao.scoreList(sel);
		
		ArrayList<DTOAllotInformation> allotlist2 = dao.subjectList();
		
		//ArrayList<DTOAllotInformation> scoretlist2 = dao.scoreList();
		for(DTOAllotInformation dto2 : allotlist2) {
			
			if (sel.equals(dto2.getNum()) && dto2.getSubject_end().equals("Y")) {
		
		tview.thickLine();
		System.out.println("[번호]  [이름] [중도탈락여부]   [과목명]    [출석점수][필기점수][실기점수][합계]");
		tview.thickLine();
		for(DTOScoreInformation dto : scorelist) { 
			System.out.printf("  %d\t%s\t  %s\t%s\t%d\t  %d\t    %d\t    %d\n"
					,dto.getNum()
					,dto.getName()
					,dto.getQuit_status()
					,dto.getSubject_name()
					,dto.getAttendance_score()
					,dto.getWrittentest_score()
					,dto.getPerformance_score()
					,dto.getTotal());
		}
		tview.thickLine();
		System.out.println();
		} else if(sel.equals(dto2.getNum()) && dto2.getSubject_end().equals("N")) {
			System.out.println();
			System.out.println("선택한 과목은 강의가 종료되지 않아 성적을 확인 할 수 없습니다.");
			System.out.println();
			break;
		}
		
	
		
		
		System.out.print("성적 수정할 교육생 선택(번호): ");
		String sel2 = scan.nextLine();
	
		
		DTOScore dto = dao.getScore(sel2);
		
		int attendance = 0;
		int writtentest = 0;
		int performancetest = 0;
		
		if(dto != null) {
			
			attendance = dto.getAttendance();
			writtentest = dto.getWritten_test();
			performancetest = dto.getPerformance_test();
			
		}
		
		System.out.print("출석점수(수정) : ");
		int temp = scan.nextInt();
		
		
		if (temp >= 0) {
			attendance = temp; 
		}
		
		System.out.print("필기점수(수정) : ");
		temp = scan.nextInt();
		
		
		if (temp >= 0) {
			writtentest = temp; 
		}
		
		System.out.print("실기점수(수정) : ");
		temp = scan.nextInt();
		scan.nextLine();
		
		if (temp >= 0) {
			performancetest = temp; 
		}
		

		dto = new DTOScore();
		
		dto.setSeq(sel2);//입력할 번호(where)
		dto.setAttendance(attendance);
		dto.setWritten_test(writtentest);
		dto.setPerformance_test(performancetest);
		
		int result = dao.addScore(dto);
		
		dao.close();
		
		if (result == 1) {
			System.out.println("[성적 수정 완료]");
			System.out.println();
			break;
			
		} else {
			System.out.println("[성적 수정 실패]");
			break;
		}
		
		
		}
		
	} 
	
	private void score_input() {
		
		
		
		DAO dao = new DAO();
		
		
		System.out.print("성적 입력할 교육생 선택(번호): ");
		String sel2 = scan.nextLine();
	
		
		DTOScore dto = dao.getScore(sel2);
		
		int attendance = 0;
		int writtentest = 0;
		int performancetest = 0;
		
		if(dto != null) {
			
			attendance = dto.getAttendance();
			writtentest = dto.getWritten_test();
			performancetest = dto.getPerformance_test();
			
		}
		
		System.out.print("출석점수 : ");
		int temp = scan.nextInt();
		
		
		if (temp >= 0) {
			attendance = temp; 
		}
		
		System.out.print("필기점수 : ");
		temp = scan.nextInt();
		
		
		if (temp >= 0) {
			writtentest = temp; 
		}
		
		System.out.print("실기점수 : ");
		temp = scan.nextInt();
		scan.nextLine();
		
		if (temp >= 0) {
			performancetest = temp; 
		}
		

		dto = new DTOScore();
		
		dto.setSeq(sel2);//입력할 번호(where)
		dto.setAttendance(attendance);
		dto.setWritten_test(writtentest);
		dto.setPerformance_test(performancetest);
		
		int result = dao.addScore(dto);
		
		if (result == 1) {
			System.out.println("[성적 입력 완료]");
			System.out.println();
		} else {
			System.out.println("[성적 입력 실패]");
		}
		
			dao.close();
		
		} 
		

	private void score_select() {
		
		System.out.println();
		
		DAO dao = new DAO();
	
		System.out.print("성적 확인할 과목 선택(번호): ");
		String sel = scan.nextLine();
		
		
		ArrayList<DTOScoreInformation> scorelist = dao.scoreList(sel);
		
		ArrayList<DTOAllotInformation> allotlist2 = dao.subjectList();
		
		//ArrayList<DTOAllotInformation> scoretlist2 = dao.scoreList();
		for(DTOAllotInformation dto2 : allotlist2) {
			
			if (sel.equals(dto2.getNum()) && dto2.getSubject_end().equals("Y")) {
		
		tview.thickLine();
		System.out.println("[번호]  [이름] [중도탈락여부]   [과목명]    [출석점수][필기점수][실기점수][합계]");
		tview.thickLine();
		for(DTOScoreInformation dto : scorelist) { 
			System.out.printf("  %d\t%s\t  %s\t%s\t%d\t  %d\t    %d\t    %d\n"
					,dto.getNum()
					,dto.getName()
					,dto.getQuit_status()
					,dto.getSubject_name()
					,dto.getAttendance_score()
					,dto.getWrittentest_score()
					,dto.getPerformance_score()
					,dto.getTotal());
		}
		tview.thickLine();
		System.out.println();
		} else if(sel.equals(dto2.getNum()) && dto2.getSubject_end().equals("N")) {
			System.out.println();
			System.out.println("선택한 과목은 강의가 종료되지 않아 성적을 확인 할 수 없습니다.");
			System.out.println();
			break;
		}
	}
}

	


	@Override
	public void QnA(String seq) {
	
//		boolean loop = true;
//		while(loop) {
		
		System.out.println("[QnA 관리]");
		tview.course();
		
		DAO dao = new DAO();
		
		ArrayList<DTOCourseInformation> list = dao.courseList(seq);
		
		
		for (DTOCourseInformation dto : list) {
			System.out.printf("     %s\t\t%s\t%s\n"
								, dto.getSeq()
								, dto.getCourse_name()
								, dto.getCourse_period());
		}
		tview.thickLine();
		view.pause();
		
		System.out.print("QnA 관리할 과정 선택(과정번호): ");
		String sel = scan.nextLine();
		
		if (sel.equals("1") || sel.equals("2") || sel.equals("3") || sel.equals("4") || sel.equals("5"))  {
			
			
			ArrayList<DTOQuestionInformation> qnalist = dao.qnaList();
			tview.thickLine();
			System.out.println("[번호]\t[교육생명]\t[질문날짜]\t[질문제목]\t\t\t\t   [답변여부]");
			tview.thickLine();
			for(DTOQuestionInformation dto2 : qnalist) {
				System.out.printf("  %s\t  %s\t%s\t%s\t%s\n"
								, dto2.getNum()
								, dto2.getStudent_name()
								, dto2.getQuestion_date()
								, dto2.getQuestion_title()
								, dto2.getAnswer_status());
			}

			tview.thickLine();
			System.out.println();
			dao.close();
			
		}
			
		   else {
				System.out.println();
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				System.out.println();
			}
			
		
		
		
		boolean loop2 = true;
		while (loop2) {
		tview.qna();
		
		sel = scan.nextLine();
		
		if(sel.equals("1")) qna_list();
		else if(sel.equals("2")) qna_answer();
		else if(sel.equals("3")) answer_select();
		else if(sel.equals("4")) answer_edit();
		else if(sel.equals("5")) answer_del();
		else if(sel.equals("6")) loop2 = false;
		
		else { 
			//System.out.println();
			//view.wrongSel();
			}
		}
		
		
		}

	private void answer_select() {
		
		System.out.println();
		
		DAO dao = new DAO();
		
		System.out.print("QnA 답변 조회할 교육생 선택(번호): ");
		String sel = scan.nextLine();
		
		ArrayList<DTOAnswerInformation> Answerlist = dao.AnswerList(sel);
		
		tview.thickLine();
		System.out.println("[번호]\t[교육생명]\t[교사명]\t[답변날짜]\t[답변제목]\t\t\t\t[답변내용]");
		tview.thickLine();
		for(DTOAnswerInformation dto2 : Answerlist) {
			System.out.printf("  %s\t  %s\t %s\t\t%s\t%s\t\t%s\n"
							, dto2.getNum()
							, dto2.getStudent_name()
							, dto2.getTeacher_name()
							, dto2.getAnswer_date()
							, dto2.getAnswer_title()
							, dto2.getAnswer_content());
		}
		
		tview.thickLine();
		System.out.println();
		dao.close();
	}

	private void qna_list() {
		
		System.out.println();
		
		DAO dao = new DAO();
		
		System.out.print("QnA 조회할 교육생 선택(번호): ");
		String sel = scan.nextLine();
		
		ArrayList<DTOQuestionInformation> qnalist2 = dao.qnaList(sel);
		tview.thickLine();
		System.out.println("[번호]\t[교육생명]\t[질문날짜]\t[질문제목]\t\t\t\t\t[질문내용]\t\t\t   [답변여부]");
		tview.thickLine();
		for(DTOQuestionInformation dto2 : qnalist2) {
			System.out.printf("  %s\t  %s\t%s\t%s\t%s\t\t%s\n"
							, dto2.getNum()
							, dto2.getStudent_name()
							, dto2.getQuestion_date()
							, dto2.getQuestion_title()
							, dto2.getQuestion_content()
							, dto2.getAnswer_status());
		}
		
		tview.thickLine();
		System.out.println();
		dao.close();
		
	}

	private void answer_del() {
		
		DAO dao = new DAO();
		
		System.out.println();
		System.out.print("QnA 답변 삭제할 교육생 선택(번호): ");
		String sel = scan.nextLine();
		
		
		int result = dao.delAnswer(sel);
		
		if (result == 1) {
			System.out.println("[답변 삭제 성공]");
		} else {
			System.out.println("[답변 삭제 실패]");
		}
		
		dao.close();
	}

	private void answer_edit() {
		
		System.out.println();
		
		DAO dao = new DAO();
		
		System.out.print("QnA답변 수정할 교육생 선택(번호): ");
		String sel = scan.nextLine();
	
		
		DTOQnA dto = dao.getQnA(sel);
		
		String answer_title = "";
		String answer_content = "";
		String answer_date = "";
		
		if(dto != null) {
			
			answer_title = dto.getAnswer_title();
			answer_content = dto.getAnswer_content();
			answer_date = dto.getAnswer_date();
		
	
		}
		
		System.out.print("답변 제목(수정) : ");
		String temp = scan.nextLine();
		
		
		if (!temp.equals("")) {
			answer_title = temp; 
		}
		
		System.out.print("답변 내용(수정) : ");
			temp = scan.nextLine();
		
		
		if (!temp.equals("")) {
			answer_content = temp; 
		}
		
		System.out.print("답변 날짜(수정) : ");
		temp = scan.nextLine();
	
		
		if (!temp.equals("")) {
			answer_date = temp; 
		}
		

		dto = new DTOQnA();
		
		dto.setSeq(sel);//입력할 번호(where)
		dto.setAnswer_title(answer_title);
		dto.setAnswer_content(answer_content);
		dto.setAnswer_date(answer_date);
		
		int result = dao.editAnswer(dto);
		
		if (result == 1) {
			System.out.println("[답변 수정 완료]");
			System.out.println();
		} else {
			System.out.println("[답변 수정 실패]");
		}
		
			dao.close();
		
		
	}

	private void qna_answer() {
		
		System.out.println();
		
		DAO dao = new DAO();
		
		System.out.print("QnA답변할 교육생 선택(번호): ");
		String sel = scan.nextLine();
	
		
		DTOQnA dto = dao.getQnA(sel);
		
		String answer_title = "";
		String answer_content = "";
		String answer_date = "";
		
		if(dto != null) {
			
			answer_title = dto.getAnswer_title();
			answer_content = dto.getAnswer_content();
			answer_date = dto.getAnswer_date();
			
		}
		
		System.out.print("답변 제목: ");
		String temp = scan.nextLine();
		
		
		if (!temp.equals("")) {
			answer_title = temp; 
		}
		
		System.out.print("답변 내용: ");
			temp = scan.nextLine();
		
		
		if (!temp.equals("")) {
			answer_content = temp; 
		}
		
		System.out.print("답변 날짜: ");
		temp = scan.nextLine();
	
		
		if (!temp.equals("")) {
			answer_date = temp; 
		}
		

		dto = new DTOQnA();
		
		dto.setSeq(sel);//입력할 번호(where)
		dto.setAnswer_title(answer_title);
		dto.setAnswer_content(answer_content);
		dto.setAnswer_date(answer_date);
		
		int result = dao.editAnswer(dto);
		
		if (result == 1) {
			System.out.println("[답변 완료]");
			System.out.println();
		} else {
			System.out.println("[답변 실패]");
		}
		
			dao.close();
		
		
	}

	@Override
	public void TeacherEvaluation(String seq) {
		
		boolean loop = true;
		
		while(loop) {
		System.out.println("[교사 평가 조회]");	
		tview.course();
		
		DAO dao = new DAO();
		
		ArrayList<DTOCourseInformation> list = dao.courseList(seq);
		
		
		for (DTOCourseInformation dto : list) {
			System.out.printf("     %s\t\t%s\t%s\n"
								, dto.getSeq()
								, dto.getCourse_name()
								, dto.getCourse_period());
		}
		tview.thickLine();
		view.pause();
		
		System.out.print("교사 평가 조회할 과정 선택(과정번호): ");
		String sel = scan.nextLine();
		
		if (sel.equals("1") || sel.equals("2") || sel.equals("3") || sel.equals("4") || sel.equals("5"))  {
			
			
			ArrayList<DTOTeacherEvaluationRate> TERlist = dao.TERList();
			tview.thickLine();
			System.out.println("[수강인원]\t[평가인원]\t[평가참여율]");
			tview.thickLine();
			for (DTOTeacherEvaluationRate dto : TERlist) {
				System.out.printf("    %s\t\t    %s\t\t     %s\n"
								, dto.getCourse_people()
								, dto.getEvaluation_people()
								, dto.getEvaluation_participation_rate());
			}
			tview.thickLine();
			System.out.println();
			
			
			ArrayList<DTOTeacherEvaluationInformation> TElist = dao.TEList();
			System.out.println("===========================================================================================================================================================================================================================");
			System.out.println("[번호]\t[교육생(익명)]\t[과정수료여부]\t[강의계획서 이행 점수]\t[강의내용전달 및 이해 점수]\t[교사 소통 점수]\t[강의 유익성 점수]\t[전반적인 만족도]\t[건의사항]");
			System.out.println("===========================================================================================================================================================================================================================");
			for (DTOTeacherEvaluationInformation dto2 : TElist) {
				System.out.printf("  %d\t    %s\t   %s\t\t   %d\t\t\t      %d\t\t\t\t%d\t\t\t%d\t\t\t%d\t\t%s\n"
								, dto2.getNum()
								, dto2.getAnonymous()
								, dto2.getStatus()
								, dto2.getPlan_score()
								, dto2.getContent_score()
								, dto2.getCommunication_score()
								, dto2.getBenefit_score()
								, dto2.getSatisfaction_score()
								, dto2.getSuggestion());
			}
			System.out.println("===========================================================================================================================================================================================================================");

			dao.close();
		}
			else {
				System.out.println();
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				System.out.println();
			}
			
			tview.back();
			
//				boolean loop2 = true;
//				while (loop2) {
//				tview.teacherEvaluation();;
//				
//				sel = scan.nextLine();
//				
//				if(sel.equals("1")) select_teacherEvaluation();
//				else if(sel.equals("2")) loop2 = false;
//				
//				else { 
//					//System.out.println();
//					//view.wrongSel();
//				}
//			}
				
		loop = false;
		 
		}
		
	}

	@Override
	public void studySearch() {
		
		tview.studysearch();
		
		String word = scan.nextLine();

		if (word.equals("1")) {

			System.out.println();
			System.out.print("[이름 입력] : ");
			String name = scan.nextLine();
			System.out.println("=================================================================================================================================================================================================================================================================");
			System.out.println("[교사 번호]\t[교사 이름]\t[강의여부]\t[배정된 과정명]\t\t\t\t\t\t[과정 시작일]\t[과정 종료일]\t[배정 개설과목명]\t[과목 시작일]\t[과목 종료일]\t[강의실명]\t[교재명]\t\t\t\t[인원수]");
			System.out.println("=================================================================================================================================================================================================================================================================");

			
			DAO dao = new DAO();

			ArrayList<DTOTeacher_2> list = dao.list(name);

			for (DTOTeacher_2 dto : list) {

				Calendar now = Calendar.getInstance();

				Calendar startCal = Calendar.getInstance();
				String[] startDate = dto.getOpenCourse_start_date().split("/");

				startCal.set(Calendar.YEAR, Integer.parseInt(startDate[0]));
				startCal.set(Calendar.MONTH, Integer.parseInt(startDate[1]));
				startCal.set(Calendar.DATE, Integer.parseInt(startDate[2]));

				Calendar endCal = Calendar.getInstance();
				String[] endDate = dto.getOpenCourse_start_date().split("/");

				startCal.set(Calendar.YEAR, Integer.parseInt(endDate[0]));
				startCal.set(Calendar.MONTH, Integer.parseInt(endDate[1]));
				startCal.set(Calendar.DATE, Integer.parseInt(endDate[2]));

				long nowTick = now.getTime().getTime();
				long startTick = startCal.getTime().getTime();
				long endTick = endCal.getTime().getTime();

				String result = "";

				if (startTick <= nowTick && endTick >= nowTick) {
					result = "강의 중";
				} else if (startTick >= nowTick) {
					result = "강의 예정";
				} else {
					result = "강의 종료";
				}

			//	System.out.println(result);

				System.out.printf("   [%s]\t\t[%s]\t[%s]\t[%s]\t\t[%s]\t[%s]\t%s\t[%s]\t[%s]\t[%s]\t%s\t  [%s]\n",
						dto.getTeacher_seq(), dto.getTeacher_name(), result, dto.getCourse_name(),
						dto.getOpenCourse_start_date(), dto.getOpenCourse_end_date(), dto.getSubject_name(),
						dto.getOpenSubject_start_date(), dto.getOpenSubject_end_date(), dto.getClassroom_name(),
						dto.getTextbook_name(), dto.getOpenCourse_seq());
			
			}
			System.out.println("=================================================================================================================================================================================================================================================================");
			System.out.println();
			dao.close();

			tview.back();
//------------------------------------------------------------------------------------------------------------------
		} else if (word.equals("2")) {
			
			
			System.out.println();
			System.out.println();
			System.out.print("과정번호 입력 : ");
			String course = scan.nextLine();
			
			System.out.println();
			System.out.println("[교육생 정보]");
			tview.thickLine();
			System.out.println("[과정번호]\t[교육생 이름]\t[전화번호]\t  [등록일]\t[상태]");
			tview.thickLine();
			DAO dao = new DAO();

			ArrayList<DTOTeacher_4> list = dao.list1(course);

			for (DTOTeacher_4 dto : list) {
				System.out.printf("    %s\t\t%s\t\t%s\t  %s\t%s\n", dto.getOpenSubject_seq(), dto.getStudent_name(),
						dto.getStudent_tel(), dto.getStudent_regdate(), dto.getOpenCourseStudent_status());

			}
			tview.thickLine();
			dao.close();

			tview.back();

		} else {

			System.out.println("다시 입력해 주세요");
		}
	}

	@Override
	public void attendanceManagement() {
		
		tview.attendanceManagement();		
		String word = scan.nextLine();

//------------------------------------------------------------------------------------------------------------------

		if (word.equals("1")) {

			
			System.out.println();
			System.out.println();
			
			System.out.print("날짜 입력 ex)2019-01-09 : ");
			String date = scan.nextLine();
			tview.thickLine();
			System.out.println("[과정번호]\t[교육생 번호]\t[교육생 이름]\t[상태]\t  [날짜]");
			tview.thickLine();
			DAO dao = new DAO();

			ArrayList<DTOTeacher_3> list = dao.list_1(date);

			for (DTOTeacher_3 dto : list) {
				System.out.printf("    %s\t\t     %3s\t    %s\t %s  \t  %s\n", dto.getCourse_seq(), dto.getStudent_seq(),
						dto.getStudent_name(), dto.getStatus_status(), dto.getGeuntae_geuntae_date());

			}

			dao.close();
			tview.thickLine();			
			tview.back();
//------------------------------------------------------------------------------------------------------------------
		} else if (word.equals("2")) {

			System.out.println();
			System.out.println();
			System.out.print("과정번호 입력 : ");
			String course = scan.nextLine();
			
			tview.thickLine();
			System.out.println("[과정번호]\t[교육생 번호]\t[교육생 이름]\t[상태]\t  [날짜]");
			tview.thickLine();
			
			DAO dao = new DAO();

			ArrayList<DTOTeacher_3> list = dao.list_2(course);

			for (DTOTeacher_3 dto : list) {
				System.out.printf("    %s\t\t     %3s\t    %s\t %s  \t  %s\n", dto.getCourse_seq(), dto.getStudent_seq(),
						dto.getStudent_name(), dto.getStatus_status(), dto.getGeuntae_geuntae_date());

			}

			dao.close();
			tview.thickLine();			
			tview.back();
//------------------------------------------------------------------------------------------------------------------
		} else if (word.equals("3")) {

	
			System.out.println();
			System.out.println();
			System.out.print("이름 입력 ex) 이현영 : ");
			String name = scan.nextLine();
			
			tview.thickLine();
			System.out.println("[과정번호]\t[교육생 번호]\t[교육생 이름]\t[상태]\t  [날짜]");
			tview.thickLine();
			DAO dao = new DAO();

			ArrayList<DTOTeacher_3> list = dao.list_3(name);

			for (DTOTeacher_3 dto : list) {
				System.out.printf("    %s\t\t     %3s\t    %s\t %s  \t  %s\n", dto.getCourse_seq(), dto.getStudent_seq(),
						dto.getStudent_name(), dto.getStatus_status(), dto.getGeuntae_geuntae_date());
			}

			dao.close();
			tview.thickLine();			
			tview.back();

		} else {
			System.out.println("잘못된 번호를 입력하였습니다.\n 다시 입력해 주세요");
	}

	}

}


	




