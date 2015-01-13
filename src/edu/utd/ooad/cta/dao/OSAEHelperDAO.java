package edu.utd.ooad.cta.dao;

import net.sf.json.JSONArray;

public interface OSAEHelperDAO {
	
	public JSONArray getStudentMetadata(String studentId, String examWrap, int cid);

}
