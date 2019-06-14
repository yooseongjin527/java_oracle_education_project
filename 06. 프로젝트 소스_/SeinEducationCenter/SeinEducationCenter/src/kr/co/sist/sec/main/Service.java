package kr.co.sist.sec.main;

import java.util.Scanner;

import kr.co.sist.sec.dto.DTOLogin;
import kr.co.sist.sec.teacher.TeacherController;

public class Service implements IService {
	
	private View view;
	private Scanner scan;
	
	public Service() {
		view = new View();
		scan = new Scanner(System.in);
	}
	
}
