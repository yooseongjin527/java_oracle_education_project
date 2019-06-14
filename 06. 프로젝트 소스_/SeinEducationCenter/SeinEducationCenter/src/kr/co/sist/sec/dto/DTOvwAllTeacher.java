package kr.co.sist.sec.dto;

// 교사 전체 정보
public class DTOvwAllTeacher {
	
	private String seq;			//번호
	private String name;		//이름
	private String ssn;			//주민번호뒷자리
	private String tel;			//전화번호
	private String status;		//상태
	
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}	

}
