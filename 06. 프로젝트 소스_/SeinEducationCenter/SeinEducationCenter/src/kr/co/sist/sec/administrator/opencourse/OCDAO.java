package kr.co.sist.sec.administrator.opencourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.sist.sec.dto.DTOClassroom;
import kr.co.sist.sec.dto.DTOCourse;
import kr.co.sist.sec.dto.DTOOpenCourse;
import kr.co.sist.sec.dto.DTOStudent;
import kr.co.sist.sec.dto.DTOSubject;
import kr.co.sist.sec.dto.DTOTeacher;
import kr.co.sist.sec.dto.DTOvwCourseSubject;
import kr.co.sist.sec.dto.DTOvwOCCSS;
import kr.co.sist.sec.dto.DTOvwOCOSCSS;
import kr.co.sist.sec.dto.DTOvwOpenCourseOpenSubject;
import kr.co.sist.sec.main.DBUtil;

public class OCDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	
	public OCDAO() {
		try {
			
			DBUtil util = new DBUtil();
			this.conn = util.connect();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void isConnedted() {
		try {
			System.out.println(!conn.isClosed() == true ? "연결" : "비연결");
		} catch (Exception e) {
			System.out.println("DAO.isConnected() : " + e.toString());
		}
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO.close()");
		}
	}
//==[Add]=========================================================
	
	public ArrayList<DTOOpenCourse> list() {
		try {
			
			String sql = "SELECT seq, course_seq, to_char(start_date, 'yyyy-mm-dd') AS start_date, to_char(end_date, 'yyyy-mm-dd') AS end_date, classroom_seq, teacher_seq, status FROM OpenCourse WHERE status = 1"; 
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<DTOOpenCourse> list = new ArrayList<DTOOpenCourse>();
			while (rs.next()) {
				DTOOpenCourse dto = new DTOOpenCourse();
				dto.setSeq(rs.getString("seq"));
				dto.setCourse_seq(rs.getString("course_seq"));
				dto.setStart_date(rs.getString("start_date"));
				dto.setEnd_date(rs.getString("end_date"));
				dto.setClassroom_seq(rs.getString("classroom_seq"));
				dto.setTeacher_seq(rs.getString("teacher_seq"));
				//dto.setStatus(rs.getString("status"));
				
				list.add(dto);
				
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public DTOCourse getCourse(String course_seq) {
		try {
			String sql = "SELECT * FROM Course WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, course_seq);
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				DTOCourse dto = new DTOCourse();
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setStatus(rs.getString("status"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public DTOClassroom getClassroom(String classroom_seq) {
		try {
			String sql = "SELECT * FROM Classroom WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, classroom_seq);
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				DTOClassroom dto = new DTOClassroom();
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setCapacity(rs.getString("capacity"));
				dto.setStatus(rs.getString("status"));
				
				return dto;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public DTOTeacher getTeacher(String teacher_seq) {
		try {
			String sql = "SELECT * FROM Teacher WHERE seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, teacher_seq);
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				DTOTeacher dto = new DTOTeacher();
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				dto.setStatus(rs.getString("status"));
				
				return dto;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public ArrayList<DTOvwOpenCourseOpenSubject> getOpenSubject(String seq) {
		try {
			String sql = "SELECT osseq, osopencourse_seq, oscoursesubject_seq, to_char(osstart_date, 'yyyy-mm-dd') AS osstart_date, to_char(osend_date, 'yyyy-mm-dd') AS osend_date, cscourse_seq, cssubject_seq, stext_seq, sname, sstatus FROM vwOpenCourseOpenSubject WHERE osopencourse_seq = ? ORDER BY cssubject_seq";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			ResultSet rs = pstat.executeQuery();
			ArrayList<DTOvwOpenCourseOpenSubject> list = new ArrayList<DTOvwOpenCourseOpenSubject>();
			while (rs.next()) {
				DTOvwOpenCourseOpenSubject dto = new DTOvwOpenCourseOpenSubject();
				dto.setOsseq(rs.getString("osseq"));
				dto.setOsopencourse_seq(rs.getString("osopencourse_seq"));
				dto.setOscoursesubject_seq(rs.getString("oscoursesubject_seq"));
				dto.setOsstart_date(rs.getString("osstart_date"));
				dto.setOsend_date(rs.getString("osend_date"));
				dto.setCscourse_seq(rs.getString("cscourse_seq"));
				dto.setCssubject_seq(rs.getString("cssubject_seq"));
				dto.setStext_seq(rs.getString("stext_seq"));
				dto.setSname(rs.getString("sname"));
				dto.setSstatus(rs.getString("sstatus"));
				
				list.add(dto);
			}
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public ArrayList<DTOStudent> getStudent(String opencourse_seq) {
		try {
			String sql = "SELECT * FROM vwOpenStudent WHERE ocsopencourse_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, opencourse_seq);
			ResultSet rs = pstat.executeQuery();
			ArrayList<DTOStudent> list = new ArrayList<DTOStudent>();
			while (rs.next()) {
				DTOStudent dto = new DTOStudent();
				dto.setSeq(rs.getString("sseq"));
				dto.setName(rs.getString("sname"));
				dto.setSsn(rs.getString("sssn"));
				dto.setTel(rs.getString("stel"));
				dto.setRegdate(rs.getString("sregdate"));
				dto.setMajor(rs.getString("smajor"));
				dto.setStatus(rs.getString("sstatus"));
				
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public ArrayList<DTOCourse> courseList() {
		try {
			String sql = "SELECT * FROM Course WHERE status = 1";
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<DTOCourse> list = new ArrayList<DTOCourse>();
			while (rs.next()) {
				DTOCourse dto = new DTOCourse();
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setStatus(rs.getString("status"));
				list.add(dto);
			}
			return list;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
		
	}

	public ArrayList<DTOSubject> slist(String cseq) {
		try {
			String sql = "SELECT * FROM vwCourseSubjectTextbook WHERE cscourse_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cseq);
			ResultSet rs = pstat.executeQuery();
			ArrayList<DTOSubject> list = new ArrayList<DTOSubject>();
			while (rs.next()) {
				DTOSubject dto = new DTOSubject();
				dto.setSeq(rs.getString("sseq"));
				dto.setName(rs.getString("sname"));
				
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public ArrayList<DTOClassroom> classroomList() {
		try {
			String sql = "SELECT * FROM Classroom WHERE status = 1";
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<DTOClassroom> list = new ArrayList<DTOClassroom>();
			while (rs.next()) {
				DTOClassroom dto = new DTOClassroom();
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setCapacity(rs.getString("capacity"));
				dto.setStatus(rs.getString("status"));
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public ArrayList<DTOTeacher> teacherList() {
		try {
			String sql = "SELECT * FROM Teacher WHERE status = 1";
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<DTOTeacher> list = new ArrayList<DTOTeacher>();
			while (rs.next()) {
				DTOTeacher dto = new DTOTeacher();
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				dto.setStatus(rs.getString("status"));
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public boolean capable(String teacher_seq, String cseq, String snum) {
		try {
			String sql = "SELECT count(*) AS cnt FROM vwCSLS WHERE cseq = ? AND tseq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cseq);
			pstat.setString(2, teacher_seq);
			ResultSet rs = pstat.executeQuery();
			String cnt = "";
			if (rs.next()) {
				cnt = rs.getString("cnt");
			}
			return snum.equals(cnt) ? true : false;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
	public String snum(String cseq) {
		try {
			String sql = "SELECT count(*) AS cnt FROM CourseSubject WHERE course_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cseq);
			ResultSet rs = pstat.executeQuery();
			String cnt = "";
			if (rs.next()) {
				cnt = rs.getString("cnt");
			}
			return cnt;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public boolean notInCourse(String tseq, String input, String input2) {
		try {
			String sql = "SELECT count(*) AS cnt FROM (SELECT * FROM OpenCourse WHERE teacher_seq = ? AND status = 1) WHERE start_date >= to_date(?, 'yyyy-mm-dd') AND start_date <= to_date(?, 'yyyy-mm-dd') OR end_date >= to_date(?, 'yyyy-mm-dd') AND end_date <= to_date(?, 'yyyy-mm-dd') OR start_date <= to_date(?, 'yyyy-mm-dd') AND end_date >= to_date(?, 'yyyy-mm-dd')";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, tseq);
			pstat.setString(2, input);
			pstat.setString(3, input2);
			pstat.setString(4, input);
			pstat.setString(5, input2);
			pstat.setString(6, input);
			pstat.setString(7, input2);
			ResultSet rs = pstat.executeQuery();
			String result = "";
			if (rs.next()) {
				result = rs.getString("cnt");
			}
			return result.equals("0") ? true : false;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public ArrayList<DTOvwCourseSubject> getSubject(String cseq) {
		try {
			String sql = "SELECT * FROM vwCourseSubject WHERE course_seq = ? ORDER BY subject_seq";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cseq);
			ResultSet rs = pstat.executeQuery();
			ArrayList<DTOvwCourseSubject> list = new ArrayList<DTOvwCourseSubject>();
			while (rs.next()) {
				DTOvwCourseSubject dto = new DTOvwCourseSubject();
				dto.setCsseq(rs.getString("csseq"));
				dto.setCourse_seq(rs.getString("course_seq"));
				dto.setSubject_seq(rs.getString("subject_seq"));
				dto.setStext_seq(rs.getString("stext_seq"));
				dto.setSname(rs.getString("sname"));
				dto.setSstatus(rs.getString("sstatus"));
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public boolean canUse(String crseq, String input, String input2) {
		try {
			String sql = "SELECT count(*) AS cnt FROM (SELECT * FROM OpenCourse WHERE classroom_seq = ? AND status = 1) WHERE start_date >= to_date(?, 'yyyy-mm-dd') AND start_date <= to_date(?, 'yyyy-mm-dd') OR end_date >= to_date(?, 'yyyy-mm-dd') AND end_date <= to_date(?, 'yyyy-mm-dd') OR start_date <= to_date(?, 'yyyy-mm-dd') AND end_date >= to_date(?, 'yyyy-mm-dd')";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, crseq);
			pstat.setString(2, input);
			pstat.setString(3, input2);
			pstat.setString(4, input);
			pstat.setString(5, input2);
			pstat.setString(6, input);
			pstat.setString(7, input2);
			ResultSet rs = pstat.executeQuery();
			String result = "";
			if (rs.next()) {
				result = rs.getString("cnt");
			}
			return result.equals("0") ? true : false;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public int regOpenCourse(String cseq, String input, String input2, String input3, String input4) {
		try {
			String sql = "INSERT INTO OpenCourse VALUES (opencourse_seq.nextVal, ?, ?, ?, ?, ?, DEFAULT)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cseq);
			pstat.setString(2, input);
			pstat.setString(3, input2);
			pstat.setString(4, input3);
			pstat.setString(5, input4);
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}
	
	//--[Edit]=======================================
	
		public boolean notStudentRegistered(String ocseq) {
			try {
				String sql = "SELECT count(student_seq) AS cnt FROM OpenCourseStudent WHERE opencourse_seq = ?) = 0";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, ocseq);
				ResultSet rs = pstat.executeQuery();
				String result = "";
				if (rs.next()) {
					result = rs.getString("cnt");
				}
				return result.equals("0") ? true : false;
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return false;
		}

		public boolean correct(String ocseq) {
			try {
				String sql = "SELECT seq FROM OpenCourse";
				ResultSet rs = stat.executeQuery(sql);
				int result = 0;
				while (rs.next()) {
					if (rs.getString("seq").equals(ocseq)) result = 1;
					else {
						result *= 0;
						break;
					}
				}
				return result == 1 ? true : false;
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return false;
		}

		public boolean existOS(String ocseq) {
			try {
				String sql = "SELECT * FROM OpenSubject WHERE opencourse_seq = ?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, ocseq);
				ResultSet rs = pstat.executeQuery();
				return rs.next();
			} catch (Exception e) {
				System.out.println(e.toString());
			} 
			return false;
		}

		public ArrayList<DTOvwOCOSCSS> oslist(String ocseq) {
			try {
				String sql = "SELECT * FROM vwOCOSCSS WHERE ocseq = ?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, ocseq);
				ResultSet rs = pstat.executeQuery();
				ArrayList<DTOvwOCOSCSS> list = new ArrayList<DTOvwOCOSCSS>();
				while (rs.next()) {
					DTOvwOCOSCSS dto = new DTOvwOCOSCSS();
					dto.setSname(rs.getString("sname"));
					dto.setOsstart_date(rs.getString("osstart_date"));
					dto.setOsend_date(rs.getString("osend_date"));
					list.add(dto);
				}
				return list;
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return null;
		}

		public String getCseq(String ocseq) {
			try {
				String sql = "SELECT course_seq FROM OpenCourse WHERE seq = ?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, ocseq);
				ResultSet rs = pstat.executeQuery();
				if (rs.next()) {
					String cseq = rs.getString("course_seq");
					return cseq;
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return null;
		}


		
		
		
		
		
		

}
