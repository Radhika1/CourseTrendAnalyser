package edu.utd.ooad.cta.dao;

import net.sf.json.JSONArray;

public interface ChartGeneratorDAO {
	
	public JSONArray generateAllStudentOneExamChart(String cid, String examTypeWrapper);
	public JSONArray generateOneStudentAllExamChart(String studId, String cid);
	public JSONArray generateAllStudentAllExamChart(String profId, String cid);

}
