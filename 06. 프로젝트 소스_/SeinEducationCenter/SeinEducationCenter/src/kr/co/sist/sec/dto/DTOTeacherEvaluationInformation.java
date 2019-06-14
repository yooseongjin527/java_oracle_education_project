package kr.co.sist.sec.dto;

public class DTOTeacherEvaluationInformation {
	
	private int num;
	private String anonymous;
	private String status;
	private int plan_score;
	private int content_score;
	private int communication_score;
	private int benefit_score;
	private int satisfaction_score;
	private String suggestion;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPlan_score() {
		return plan_score;
	}
	public void setPlan_score(int plan_score) {
		this.plan_score = plan_score;
	}
	public int getContent_score() {
		return content_score;
	}
	public void setContent_score(int content_score) {
		this.content_score = content_score;
	}
	public int getCommunication_score() {
		return communication_score;
	}
	public void setCommunication_score(int communication_score) {
		this.communication_score = communication_score;
	}
	public int getBenefit_score() {
		return benefit_score;
	}
	public void setBenefit_score(int benefit_score) {
		this.benefit_score = benefit_score;
	}
	public int getSatisfaction_score() {
		return satisfaction_score;
	}
	public void setSatisfaction_score(int satisfaction_score) {
		this.satisfaction_score = satisfaction_score;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
	
	
}
