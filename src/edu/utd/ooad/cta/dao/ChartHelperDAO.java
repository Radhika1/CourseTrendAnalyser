package edu.utd.ooad.cta.dao;

import net.sf.json.JSONArray;

public interface ChartHelperDAO {
	
	public JSONArray getCourseData(String userId);
	public JSONArray getExamWrapData(String userId, String cid);

}
