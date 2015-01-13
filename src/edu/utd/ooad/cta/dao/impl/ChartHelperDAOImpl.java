package edu.utd.ooad.cta.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.collections.Lists;

import edu.utd.ooad.cta.Constants.QueryConstants;
import edu.utd.ooad.cta.dao.ChartHelperDAO;
import edu.utd.ooad.cta.db.DBUtil;

public class ChartHelperDAOImpl implements ChartHelperDAO {

	@Override
	public JSONArray getCourseData(String profId) {
		JSONArray array = new JSONArray();
		ResultSet rs = DBUtil.getInstance().executeQuery(
				String.format(QueryConstants.GET_COURSE_DATA, profId));
		
		try {
			
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("course_id", rs.getString("course_id"));
				obj.put("Course_sect_no", rs.getString("Course_sect_no"));
				obj.put("c_id", rs.getString("c_id"));
				array.add(obj);
			}

			JSONObject finalObj = new JSONObject();
			finalObj.accumulate("data", array);

		} catch (Exception e) {
			System.err.println("failed to establish the connection with database." + e.getMessage());
		}
		return array;
	}

	@Override
	public JSONArray getExamWrapData(String profId, String cid) {
		JSONArray array = new JSONArray();
		List<Object> params = Lists.newArrayList();
		params.add(cid);
		
		ResultSet rs = DBUtil.getInstance().executeQuery(
				String.format(QueryConstants.GET_EXAM_WRAP_DATA, profId, cid));
		
		try {
			
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("exam_wrap", rs.getString("exam_wrap"));
				array.add(obj);
			}

			JSONObject finalObj = new JSONObject();
			finalObj.accumulate("data", array);

		} catch (Exception e) {
			System.err.println("failed to establish the connection with database." + e.getMessage());
		}
		return array;
	}

}
