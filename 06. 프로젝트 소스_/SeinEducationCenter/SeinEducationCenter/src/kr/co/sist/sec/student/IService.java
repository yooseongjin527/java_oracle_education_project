package kr.co.sist.sec.student;

public interface IService {

	void login(int n);

	void findId(int n);

	void findPw(int n);
	
	//----------------------------
	
	void score(); //성적조회 

	void geuntae(String sel); //출결조회 


	void notice(); //기업공고 

	void qna(); //QnA

	void teacherevaluation(); //교사평가 

	void boardlist(); //게시판목록

	void boardwrite(); //게시글작성

	void boardedit(); //게시글수정

	void boarddelete();//게시글삭제
	
	//----------------------------
	
	void studentQnA();


}
