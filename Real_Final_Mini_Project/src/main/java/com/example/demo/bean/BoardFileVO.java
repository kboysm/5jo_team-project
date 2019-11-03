package com.example.demo.bean;

public class BoardFileVO {
	private int no;
	private String title;
	private String filename;
	private String fileoriname;
	private String fileurl;
	
	
	public BoardFileVO() {}
	
	public BoardFileVO(int no, String title, String filename, String fileoriname, String fileurl) {
		super();
		this.no = no;
		this.title = title;
		this.filename = filename;
		this.fileoriname = fileoriname;
		this.fileurl = fileurl;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileoriname() {
		return fileoriname;
	}
	public void setFileoriname(String fileoriname) {
		this.fileoriname = fileoriname;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	@Override
	public String toString() {
		return "BoardFileVO [no=" + no + ", title=" + title + ", filename=" + filename + ", fileoriname=" + fileoriname
				+ ", fileurl=" + fileurl + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((fileoriname == null) ? 0 : fileoriname.hashCode());
		result = prime * result + ((fileurl == null) ? 0 : fileurl.hashCode());
		result = prime * result + no;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardFileVO other = (BoardFileVO) obj;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (fileoriname == null) {
			if (other.fileoriname != null)
				return false;
		} else if (!fileoriname.equals(other.fileoriname))
			return false;
		if (fileurl == null) {
			if (other.fileurl != null)
				return false;
		} else if (!fileurl.equals(other.fileurl))
			return false;
		if (no != other.no)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
	
}
