package kr.co.sist.sec.teacher;

public interface IService {

	void login(int n);

	void findId(int n);

	void findPw(int n);

	void Allot(String seq);

	void Score(String seq);

	void QnA(String seq);

	void TeacherEvaluation(String seq);

	void studySearch();

	void attendanceManagement();

}
