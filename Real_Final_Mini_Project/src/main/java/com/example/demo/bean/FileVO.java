package com.example.demo.bean;

public class FileVO {
	 private int fno;
	    private int no;
	    private String fileName;     //저장할 파일
	    private String fileOriName;  //실제 파일
	    private String fileUrl;
		
	    public FileVO() {}
	    
	    public FileVO(int fno, int no, String fileName, String fileOriName, String fileUrl) {
			super();
			this.fno = fno;
			this.no = no;
			this.fileName = fileName;
			this.fileOriName = fileOriName;
			this.fileUrl = fileUrl;
		}

		public int getFno() {
			return fno;
		}

		public void setFno(int fno) {
			this.fno = fno;
		}

		public int getNo() {
			return no;
		}

		public void setNo(int no) {
			this.no = no;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileOriName() {
			return fileOriName;
		}

		public void setFileOriName(String fileOriName) {
			this.fileOriName = fileOriName;
		}

		public String getFileUrl() {
			return fileUrl;
		}

		public void setFileUrl(String fileUrl) {
			this.fileUrl = fileUrl;
		}

		@Override
		public String toString() {
			return "FileVO [fno=" + fno + ", no=" + no + ", fileName=" + fileName + ", fileOriName=" + fileOriName
					+ ", fileUrl=" + fileUrl + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
			result = prime * result + ((fileOriName == null) ? 0 : fileOriName.hashCode());
			result = prime * result + ((fileUrl == null) ? 0 : fileUrl.hashCode());
			result = prime * result + fno;
			result = prime * result + no;
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
			FileVO other = (FileVO) obj;
			if (fileName == null) {
				if (other.fileName != null)
					return false;
			} else if (!fileName.equals(other.fileName))
				return false;
			if (fileOriName == null) {
				if (other.fileOriName != null)
					return false;
			} else if (!fileOriName.equals(other.fileOriName))
				return false;
			if (fileUrl == null) {
				if (other.fileUrl != null)
					return false;
			} else if (!fileUrl.equals(other.fileUrl))
				return false;
			if (fno != other.fno)
				return false;
			if (no != other.no)
				return false;
			return true;
		}
	    
	   
}
