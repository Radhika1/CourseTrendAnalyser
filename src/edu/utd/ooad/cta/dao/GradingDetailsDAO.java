package edu.utd.ooad.cta.dao;

import java.util.ArrayList;

import edu.utd.ooad.cta.domain.GradingDetailsBean;

public interface GradingDetailsDAO {
	
	public ArrayList<Integer> getCid(int courseId);
	
	public ArrayList<GradingDetailsBean> checkValueExists(GradingDetailsBean g);
	
	public boolean deleteExamTypeValues(GradingDetailsBean g);
	
	public boolean addGradingValues(GradingDetailsBean g);
}