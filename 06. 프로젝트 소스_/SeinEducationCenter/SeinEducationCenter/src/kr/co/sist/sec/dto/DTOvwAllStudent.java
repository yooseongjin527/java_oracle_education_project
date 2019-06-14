package kr.co.sist.sec.dto;

// 교육생 전체 정보
public class DTOvwAllStudent {
	
	private String seq;			//번호
	private String name;		//이름
	private String ssn;			//주민번호뒷자리
	private String tel;			//전화번호
	private String major;		//전공여부
	
	
	
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
	
	
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
}
