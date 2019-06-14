package kr.co.sist.sec.student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

import kr.co.sist.sec.dto.DTOAnswerInformation;
import kr.co.sist.sec.dto.DTOBoard;
import kr.co.sist.sec.dto.DTOCompany;
import kr.co.sist.sec.dto.DTOLogin;
import kr.co.sist.sec.dto.DTONoticeApplication;
import kr.co.sist.sec.dto.DTOQnA;
import kr.co.sist.sec.dto.DTOStudent;
import kr.co.sist.sec.dto.DTOStudentBoard;
import kr.co.sist.sec.dto.DTOStudentCheckAttendance;
import kr.co.sist.sec.dto.DTOStudentTestCheck;
import kr.co.sist.sec.dto.DTOTeacherEvaluation;
import kr.co.sist.sec.dto.DTOViewCompany;
import kr.co.sist.sec.main.View;

public class Service implements IService {

	private View view;
	private StudentView studentView;
	private Scanner scan;
	
	public static String student_seq;
	public static String student_name;

	public Service() {
		view = new View();
		studentView = new StudentView();
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
				
				
				// student_seq 얻기
				int index = 0;
				
				for (int i = 0; i < id.length(); i++) {
					
					if (id.charAt(i) >= (char)'1' && id.charAt(i) <= (char)'9') {
						
						index = i;
						break;						
					}
				}
				
				student_seq = id.substring(index);
				student_name = id.substring(0, index);
				
				
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
				
				StudentController sc = new StudentController();
				loop2 = sc.student(seq);
				
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
			
			DTOStudent dto = new DTOStudent();
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
				
				DTOStudent dto = new DTOStudent();
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

	//-----------------------------------
	
	@Override
	public void score() { // 성적조회

		studentView.title(StudentView.SCORE);

		System.out.println();
		System.out.println(
				"[교육생이름]\t[과목명]\t[과목시작일]\t[과목종료일]\t[교재명]" + "[교사명]\t[출결점수]\t[필기점수]\t[실기점수]\t[필기시험날짜]\t[실기시험날짜]");

		DAO dao = new DAO();

		ArrayList<DTOStudentTestCheck> list = dao.score();

		for (DTOStudentTestCheck dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", dto.getName(), dto.getSubject_name(),
					dto.getStart_date(), dto.getEnd_date(), dto.getTextbook_name(), dto.getTeacher_name(),
					dto.getScore_attendance(), dto.getScore_written_test(), dto.getScore_performance_test(),
					dto.getOpenSubject_writtentest_date(), dto.getOpenSubject_performancetest_date());

		}

		dao.close();
		
		studentView.thickLine();
		System.out.println();
		System.out.println();
		System.out.println();
		view.pause();
		view.flip();
		
	}

	@Override
	public void geuntae(String sel) { // 출석조회
		
		System.out.println();
		System.out.println();
		studentView.title(StudentView.GEUNTAE);
		
		String beginDate = "";
		String endDate = "";
		
		if (sel.equals("1")) {
			
			Calendar c = Calendar.getInstance();

			beginDate = String.format("%s-%02d-%02d"
											, c.get(Calendar.YEAR)
											, c.get(Calendar.MONTH) + 1
											, c.get(Calendar.DATE));
			endDate = beginDate;
			
		} else if (sel.equals("2")) {
			
			studentView.termInput();
			
			System.out.print("시작일 : ");
			beginDate = scan.nextLine();
			
			System.out.println();
			
			System.out.print("종료일 : ");
			endDate = scan.nextLine();
			
		}
			


		// DB 작업 > dao 위임 (select)
		DAO dao = new DAO();
		
		ArrayList<DTOStudentCheckAttendance> list = dao.geuntae(beginDate, endDate);

		System.out.println();
		System.out.println();
		
		if (sel.equals("1")) {
			System.out.printf("\t\t\t\t\t\t\t\t\t\t[%s]님의 %s 출결현황\n\n"
									, student_name
									, beginDate);
			
		} else if (sel.equals("2")) {
			
			System.out.printf("[%s]님의 (%s) ~ (%s) 출결현황\n\n"
					, student_name
					, beginDate
					, endDate);
		}
		
		view.thickLine();
		System.out.println();
		System.out.println();
		System.out.println("[날짜]\t\t[상태]\t[입실]\t[퇴실]");
		System.out.println();
		for (DTOStudentCheckAttendance dto : list) {
			
			
			System.out.printf("%s\t%s\t%s\t%s\n"
									, dto.getGeuntae_date()
									, dto.getStatus()
									, dto.getCheck_in()
									, dto.getCheck_out());

		}
		
		dao.close();
		
		System.out.println();
		view.thickLine();
		System.out.println();
		System.out.println();
		view.pause();

	}

	@Override
	public void boardlist() { // 게시판조회
		
		
		studentView.title(StudentView.BOARDLIST);

		System.out.println();
		view.thickLine();
		System.out.println();
		System.out.println("[글번호]\t[작성자]\t[날짜]\t\t[제목]\t\t\t\t\t\t[내용]");

		// DB 작업 > dao 위임 (select)
		DAO dao = new DAO();

		ArrayList<DTOStudentBoard> list = dao.boardlist();

		for (DTOStudentBoard dto : list) {
			System.out.printf("%s\t\t%s\t\t%s\t%s%s\n", dto.getBoard_seq(), dto.getStudent_name(), dto.getBoard_regdate(),
					dto.getBoard_title(), dto.getBoard_content());
		}

		dao.close();
		
		System.out.println();
		view.thickLine();
		System.out.println();
		System.out.println();
		view.pause();
		

	}

	@Override
	public void boardwrite() { //게시판 글쓰기
		studentView.title(StudentView.BOARDWRITE);
		
		System.out.print("제목 : ");
		String title = scan.nextLine();
		title = title.replace("'", "''");
		
		System.out.print("내용 : ");
		String content = scan.nextLine();
		content = content.replace("'", "''");

		DAO dao = new DAO();
		
		DTOBoard dto = new DTOBoard();
		dto.setStudent_seq(student_seq);
		dto.setTitle(title);
		dto.setContent(content);
		
		int result = dao.boardwrite(dto);
		
		dao.close();
		
		if (result == 1) {
			System.out.println("===============================");
			System.out.println("게시글 작성 완료");
			System.out.println("===============================");
		} else {
			System.out.println("===============================");
			System.out.println("게시글 작성 실패");
			System.out.println("===============================");
		}
		
		view.pause();
	}

	@Override
	public void boardedit() { // 게시판 수정
		studentView.title(StudentView.BOARDEDIT);

		System.out.println("==============================================================================");;
		System.out.println("[글번호]\t[날짜]\t\t[제목]\t\t\t\t[내용]");

		DAO dao = new DAO();

		ArrayList<DTOStudentBoard> list = dao.boardelist();// 목록불러오기

		for (DTOStudentBoard dto : list) {
			System.out.printf("%s\t%s\t%s\t\t\t\t%s\n"
						, dto.getBoard_seq()
						, dto.getBoard_regdate()
						, dto.getBoard_title()
						, dto.getBoard_content());
		}

		System.out.println("===============================");
		System.out.print("수정할 번호 : ");
	
		String seq = scan.nextLine();

		DTOStudentBoard dto = dao.choicenumber(seq);

		String board_seq = "";
		String board_regdate = "";
		String board_title = "";
		String board_content = "";

		if (dto != null) {
			board_seq = dto.getBoard_seq();
			board_regdate = dto.getBoard_regdate();
			board_title = dto.getBoard_title();
			board_content = dto.getBoard_content();

		}


///////////////제목 수정  
		System.out.println("===============================");
		System.out.println("기존 제목 : " + board_title);
		System.out.print("새로운 제목 : ");
	
		String temp = scan.nextLine();

		if (!temp.equals("")) {
			board_title = temp;
			
		}
		
///////////////내용 수정  
		System.out.println("===============================");
		System.out.println("기존 내용 : " + board_content);
		System.out.print("새로운 내용 : ");
	
		temp = scan.nextLine();

		if (!temp.equals("")) {
			board_content = temp;
		}
		
		dto = new DTOStudentBoard();
		dto.setBoard_seq(seq);
		dto.setBoard_title(board_title);
		dto.setBoard_content(board_content);
		
		int result = dao.edit(dto);
		
		if (result == 1) {
			System.out.println("===============================");
			System.out.println("게시판 수정 완료");
			System.out.println("===============================");
		} else {
			System.out.println("===============================");
			System.out.println("메모 수정 실패");
			System.out.println("===============================");
		}
		view.pause();
	}

	@Override
	public void boarddelete() { //게시판 삭제
		studentView.title(StudentView.BOARDDELETE);

		System.out.println("[글번호]\t[날짜]\t\t[제목]\t\t\t\t[내용]");
		System.out.println("==============================================================================");;

		DAO dao = new DAO();
		
		ArrayList<DTOStudentBoard> list = dao.boardelist();
		//삭제 할 목록 
		for (DTOStudentBoard dto : list) {
			System.out.printf("%s\t%s\t%s\t\t\t\t%s\n"
					, dto.getBoard_seq()
					, dto.getBoard_regdate()
					, dto.getBoard_title()
					, dto.getBoard_content());
		}
		System.out.println("===============================");
		System.out.print("삭제할 번호 : ");
;
		String seq = scan.nextLine();
		
		int result = dao.boarddelete(seq);
	
		if (result == 1) {
			System.out.println("===============================");
			System.out.println("게시글 삭제 성공");
			System.out.println("===============================");
		} else {
			System.out.println("===============================");
			System.out.println("게시글 삭제 실패");
			System.out.println("===============================");
		}
	}

	@Override
	public void notice() { //기업공고 

		studentView.title(StudentView.NOTICE);
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t1.기업 조회 ");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t2.기업 검색 ");
		System.out.println();
		view.thickLine();
		System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");
		String course = scan.nextLine();
		
		System.out.println();
		view.thickLine();
		System.out.println();
		System.out.println();
		
		if (course.equals("1")) {

			System.out.println("[기업번호]\t[기업명]\t\t\t\t\t[규모]\t\t\t[사원수]\t[설립년도]\t[지역]");

			DAO dao = new DAO();

			ArrayList<DTOCompany> list = dao.list_1();

			for (DTOCompany dto : list) {

				System.out.printf("%s\t\t%s%s\t\t%s\t\t%s\t\t%s\n", dto.getSeq(), dto.getName(), dto.getScale(),
						dto.getEmployee_num(), dto.getEstablished_year(), dto.getArea());

			}

			dao.close();
			
			view.thickLine();
			System.out.println();
			System.out.println();
			view.pause();

		} else if (course.equals("2")) {

			System.out.print("1. 최소연봉 : ");
			String minsalary = scan.nextLine();

			System.out.println("2 . 지역 [1. 수도권 , 2. 지방 , 3. 해외]");
			System.out.print("선택  : ");
			String area = scan.nextLine();

			if (area.equals("1")) {
				area = "'전국', '서울', '경기'";

			} else if (area.equals("2")) {
				area = "'전국', '광주', '강원', '충북'";

			} else if (area.equals("3")) {
				area = "'홍콩', '일본' , '중국' , '말레이시아', '호주', '브라질', '러시아', '미국', '인도네시아', '스페인'";

			} else {
				System.out.println("다시 입력주세요");
			}

			System.out.println("3 . 규모 : [1. 대기업 , 2. 중견기업,  3.공기업, 4. 중소기업, 5. 외국계]");
			System.out.print("선택 : ");
			String scale = scan.nextLine();

			if (scale.equals("1")) {
				scale = "대기업";
			} else if (scale.equals("2")) {
				scale = "중견기업";
			} else if (scale.equals("3")) {
				scale = "공기업";
			} else if (scale.equals("4")) {
				scale = "중소기업";
			} else if (scale.equals("5")) {
				scale = "외국계";
			} else {
				System.out.println("다시 입력주세요.");
			}

			System.out.println("[번호]\t[기업명]\t\t\t\t\t[계열_규모]\t[사원수]\t[설립년도]\t[지역]\t[연봉]\t\t[마감일]\t\t[요구전공여부]\t\t[요구과정]");

			DAO dao = new DAO();

			ArrayList<DTOViewCompany> list = dao.list(minsalary, area, scale);

			for (DTOViewCompany dto : list) {

				System.out.printf("%s\t%s%s\t%s\t\t%s\t\t%s\t%s\t%s\t%s\t\t\t%s\n", dto.getSeq(), dto.getName(),
						dto.getScale(), dto.getEmployee_num(), dto.getEstablished_year(), dto.getArea(),
						dto.getSalary(), dto.getDeadline(), dto.getMajor(), dto.getCourse());

			}
					

			view.thinLine();
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t1. 공고 신청 ");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t2. 뒤로 돌아가기");
			System.out.println();
			System.out.print("\t\t\t\t\t\t\t\t\t\t선택 : ");
			String sinchung = scan.nextLine();

			if (sinchung.equals("1")) {

				
				System.out.println();
				System.out.print("신청 번호: ");
				String sinchung1 = scan.nextLine();

				System.out.println();
				System.out.print("공고 번호: ");
				String sinchung2 = scan.nextLine();

				System.out.println();
				System.out.print("교육생 번호 : ");
				String studuntNumber = scan.nextLine();

				DAO dao1 = new DAO();

				DTONoticeApplication dto = new DTONoticeApplication();
				
				dto.setSeq(sinchung1);
				dto.setNotice_seq(sinchung2);
				dto.setOpencoursestudent_seq(studuntNumber);

				int result = dao1.add(dto);

				dao.close();

				if (result == 1) {
					System.out.println();
					System.out.println("공고 신청 완료");

				} else {
					System.out.println();
					System.out.println("공고 신청 실패");
				}

				view.pause();

			} else if (sinchung.equals("2")) {
				notice();
			} else {
				System.out.println();
				System.out.println("다시 입력해 주세요.");
			}

		} else {
			System.out.println();
			System.out.println("다시 입력해 주세요. ");
			notice();

		}
	}

	@Override
	public void qna() {
		// TODO Auto-generated method stub

	}

	@Override
	public void teacherevaluation() { //교사평가 	
		studentView.title(StudentView.TEACHEREVALUATION);
		
		studentView.teacherevaluationmenu();
		
		String ans = scan.nextLine();
		boolean loop = true;
		while (loop) {
		
		if (ans.equals("y")) {
			evaluation();
			loop = false;
		} else 
			loop = false;
		 	
		}	

	}
	
	public void evaluation(){
		System.out.println();
		boolean loop = true;
		
		String answer1 = "";
		String answer2 = "";
		String answer3 = "";
		String answer4 = "";
		String answer5 = "";
		String answer6 = "";
		String seq = "";
		
		while (loop) {
			
			System.out.println("===============================");
			System.out.print("1. 강의계획서 이행 점수(0~10) : ");
			answer1 = scan.nextLine();
			answer1 = answer1.replace("'", "''");
			
			System.out.print("2. 강의내용 전달 및 이해 점수 평가(0~10) : ");
			answer2 = scan.nextLine();
			answer2 = answer2.replace("'", "''");
			
			System.out.print("3. 교육생과의 소통 점수(0~10) : ");
			answer3 = scan.nextLine();
			answer3 = answer3.replace("'", "''");
			
			System.out.print("4. 강의 유익성 점수(0~10) : ");
			answer4 = scan.nextLine();
			answer4 = answer4.replace("'", "''");
			
			System.out.print("5. 과정 전체 만족도 점수(0~10) : ");
			answer5 = scan.nextLine();
			answer5 = answer5.replace("'", "''");
			
			System.out.print("6. 건의사항 : ");
			answer6 = scan.nextLine();
			answer6 = answer6.replace("'", "''");
	
			System.out.print("학생 번호 : ");
			seq = scan.nextLine();
			seq = seq.replace("'", "''");
			System.out.println("===============================");
			
			
			if (answer1.equals("") 
					|| answer1.equals("") 
					|| answer2.equals("") 
					|| answer3.equals("") 
					|| answer4.equals("") 
					|| answer5.equals("") 
					|| answer6.equals("") 
					|| seq.equals("")) {
				loop = true;
				System.out.println();
				System.out.println("모든 항목에 응답해주세요.");
				System.out.println();
			} else {
				loop = false;
			}
			
		}
		
		
		DAO dao = new DAO();
		
		DTOTeacherEvaluation dto = new DTOTeacherEvaluation();
		
		dto.setPlan_score(answer1);
		dto.setContent_score(answer2);
		dto.setCommunication_score(answer3);
		dto.setBenefit_score(answer4);
		dto.setSatisfaction_score(answer5);
		dto.setSuggestion(answer6);
		dto.setOpencoursestudent_seq(seq);
		
		int result = dao.teacherTest(dto);
		
		dao.close();
		
		if (result == 1) {
			System.out.println("===============================");
			System.out.println("교사평가 성공");
			System.out.println("===============================");
		} else {
			System.out.println("===============================");
			System.out.println("교사평가 실패");
			System.out.println("===============================");
		}
		
		view.pause();
	}
	
	//------------------------
	@Override
	public void studentQnA() {
		
		System.out.println();
		
		
		boolean loop = true;
		while(loop) {
			
			studentView.QnA();
		
		String sel = scan.nextLine();
		
		if(sel.equals("1")) add_question();
		else if(sel.equals("2")) edit_question();
		else if(sel.equals("3")) del_question();
		else if(sel.equals("4")) select_answer();
		else if(sel.equals("0")) loop = false;
	
		
		else { 
			//System.out.println();
			//view.wrongSel();
		}
	
	}
}
		
		private void add_question() {
			
			System.out.println();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");

	        Calendar c1 = Calendar.getInstance();

		    String strToday = sdf.format(c1.getTime());
			
		    DAO dao = new DAO();
		    
		    DTOQnA dto = dao.getQuestion();
		    
		    		
			 String question_title = "";
			 String question_content = "";
			 String question_date = "";
			 
				if(dto != null) {
					
					question_title = dto.getQuestion_title();
					question_content = dto.getQuestion_content();
					question_date = dto.getQuestion_date();
				
				}
			
				
				System.out.print("질문 제목 입력 : ");
				String temp = scan.nextLine();
				
				if (!temp.equals("")) {
					question_title = temp; 
				}
				
				System.out.println();
				
				System.out.print("질문 내용 입력 : ");
				temp = scan.nextLine();
				
				if (!temp.equals("")) {
					question_content = temp; 
				}
				
				
				temp = strToday;
				
				if (!temp.equals("")) {
					question_date = temp; 
				}
				
			
					dto = new DTOQnA();
					
		//			dto.setSeq(sel3);//입력할 번호(where)
					dto.setQuestion_title(question_title);
					dto.setQuestion_content(question_content);
					dto.setQuestion_date(question_date);
					
				
				
		   		 int result = dao.addQuestion(dto);
				
		    		
		    


		   		 if (result == 1) {
						System.out.println("[질문 등록 완료]");
						System.out.println();
						System.out.println();
					} else {
						System.out.println("[질문 등록 실패]");
						System.out.println();
					}
				
						dao.close();
		
						
						
		
		}
		



		private void edit_question() {
			
			System.out.println();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");

	        Calendar c1 = Calendar.getInstance();

		    String strToday = sdf.format(c1.getTime());
			
		    DAO dao = new DAO();
		    
		    
		    System.out.println("엔터를 누르시면 작성한 질문 목록을 보여줍니다.");
		    scan.nextLine();
		    
		    System.out.println();
		    
		    DTOQnA dto = dao.getQuestion();
		    
		    ArrayList<DTOQnA> qnalist = dao.getQuestion2();
		    
			studentView.thickLine();
			System.out.println("[번호]\t[질문날짜]\t[질문제목]\t\t\t\t[질문내용]");
			studentView.thickLine();
		    
		    for(DTOQnA dto2: qnalist) {
		    	System.out.printf("%s/t%s/t%s\t%s\n"
		    			, dto2.getSeq()
		    			, dto2.getQuestion_date()
		    			, dto2.getQuestion_title()
		    			, dto2.getQuestion_content()
		    			);
		    }
		    
		    
			 String question_title = "";
			 String question_content = "";
			 String question_date = "";
			 
				if(dto != null) {
					
					question_title = dto.getQuestion_title();
					question_content = dto.getQuestion_content();
					question_date = dto.getQuestion_date();
				
				}
			
				
				System.out.print("질문 제목(수정) 입력 : ");
				String temp = scan.nextLine();
				
				if (!temp.equals("")) {
					question_title = temp; 
				}
				
				System.out.println();
				
				System.out.print("질문 내용(수정) 입력 : ");
				temp = scan.nextLine();
				
				if (!temp.equals("")) {
					question_content = temp; 
				}
				
				
				temp = strToday;
				
				if (!temp.equals("")) {
					question_date = temp; 
				}
				
			
					dto = new DTOQnA();
					
		//			dto.setSeq(sel3);//입력할 번호(where)
					dto.setQuestion_title(question_title);
					dto.setQuestion_content(question_content);
					dto.setQuestion_date(question_date);
					
				
				
		   		 int result = dao.addQuestion(dto);
				
		    		
		    


		   		 if (result == 1) {
						System.out.println("[질문 수정 완료]");
						System.out.println();
						System.out.println();
					} else {
						System.out.println("[질문 수정 실패]");
						System.out.println();
					}
				
						dao.close();
		
		}
		
		private void del_question() {
			
			DAO dao = new DAO();
			
			System.out.println();
			System.out.print("삭제할 질문 선택(번호): ");
			String sel = scan.nextLine();
			
			
			DTOQnA dto = new DTOQnA();
			
			int result = dao.delQuestion(sel);
			
			if (result == 1) {
				System.out.println("[답변 삭제 성공]");
				System.out.println();
				System.out.println();
				
			} else {
				System.out.println("[답변 삭제 실패]");
				System.out.println();
			}
			
			
			dao.close();
		}

		private void select_answer() {
			
			System.out.println();
			
			DAO dao = new DAO();
			
			System.out.print("답변 조회할 질문 선택(번호): ");
			String sel = scan.nextLine();
			
			ArrayList<DTOAnswerInformation> Answerlist = dao.AnswerList(sel);
			
			studentView.thickLine();
			System.out.println("[번호]\t[담당교사명]\t[답변날짜]\t[답변제목]\t\t\t\t[답변내용]");
			studentView.thickLine();
			for(DTOAnswerInformation dto2 : Answerlist) {
				System.out.printf("  %s\t   %s\t %s\t%s\t\t\t\t\t%s\n"
								, dto2.getNum()
								, dto2.getTeacher_name()
								, dto2.getAnswer_date()
								, dto2.getAnswer_title()
								, dto2.getAnswer_content());
			}
			
			studentView.thickLine();
			System.out.println();
			dao.close();
		}

	
	
}
	
	




