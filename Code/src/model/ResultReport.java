package model;

public class ResultReport {
	private String Sid;
	private String Fid;
	private String SafetyLevel;
	private String ReportContent;
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
	}
	public String getFid() {
		return Fid;
	}
	public void setFid(String fid) {
		Fid = fid;
	}
	public String getSafetyLevel() {
		return SafetyLevel;
	}
	public void setSafetyLevel(String safetyLevel) {
		SafetyLevel = safetyLevel;
	}
	public String getReportContent() {
		return ReportContent;
	}
	public void setReportContent(String reportContent) {
		ReportContent = reportContent;
	}
}
