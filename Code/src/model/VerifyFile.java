package model;

public class VerifyFile {
	private String FileMD5;
	private Double Size;
	private String FilePath;
	private String Level;
	public String getFileMD5() {
		return FileMD5;
	}
	public void setFileMD5(String fileMD5) {
		FileMD5 = fileMD5;
	}
	public Double getSize() {
		return Size;
	}
	public void setSize(Double size) {
		Size = size;
	}
	public String getFilePath() {
		return FilePath;
	}
	public void setFilePath(String filePath) {
		FilePath = filePath;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
}
