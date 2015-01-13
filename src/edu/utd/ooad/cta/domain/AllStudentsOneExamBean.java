package edu.utd.ooad.cta.domain;

public class AllStudentsOneExamBean {

	private String StudentID;
	private int Exam_Marks;
	private int Exam_Total;
	private int Cid;
	private int Secid;
	private String Exam_Type;
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	public int getExam_Marks() {
		return Exam_Marks;
	}
	public void setExam_Marks(int exam_Marks) {
		Exam_Marks = exam_Marks;
	}
	public int getExam_Total() {
		return Exam_Total;
	}
	public void setExam_Total(int exam_Total) {
		Exam_Total = exam_Total;
	}
	public int getCid() {
		return Cid;
	}
	public void setCid(int cid) {
		Cid = cid;
	}
	public int getSecid() {
		return Secid;
	}
	public void setSecid(int secid) {
		Secid = secid;
	}
	public String getExam_Type() {
		return Exam_Type;
	}
	public void setExam_Type(String exam_Type) {
		Exam_Type = exam_Type;
	}
	
	
}
