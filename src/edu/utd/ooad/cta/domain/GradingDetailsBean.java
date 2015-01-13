package edu.utd.ooad.cta.domain;

import java.util.ArrayList;

public class GradingDetailsBean {

	public GradingDetailsBean() {
		// TODO Auto-generated constructor stub
	}
	private String ExamType;
	private double IndividualWeightage;
	private int TotalWeightage;
	private ArrayList<Integer> Cid = new ArrayList<Integer>();
	
	public String getExamType() {
		return ExamType;
	}
	public void setExamType(String examType) {
		ExamType = examType;
	}
	public double getIndividualWeightage() {
		return IndividualWeightage;
	}
	public void setIndividualWeightage(double individualWeightage) {
		IndividualWeightage = individualWeightage;
	}
	public int getTotalWeightage() {
		return TotalWeightage;
	}
	public void setTotalWeightage(int totalWeightage) {
		TotalWeightage = totalWeightage;
	}
	public ArrayList<Integer> getCid() {
		return Cid;
	}
	public void setCid(ArrayList<Integer> cid) {
		Cid = cid;
	}
	
}
