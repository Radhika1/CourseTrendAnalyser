package edu.utd.ooad.cta.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import org.testng.collections.Lists;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.utd.ooad.cta.Constants.QueryConstants;
import edu.utd.ooad.cta.dao.ChartGeneratorDAO;
import edu.utd.ooad.cta.db.DBUtil;

public class ChartGeneratorDAOImpl implements ChartGeneratorDAO {

	@Override
	public JSONArray generateAllStudentOneExamChart(String cid, String examTypeWrapper) {

		JSONArray array = new JSONArray();
		ResultSet rs = DBUtil.getInstance().executeQuery(
				String.format(QueryConstants.GET_NORMALIZED_DATA_FOR_ALL_STUDENT_ONE_EXAM, cid,examTypeWrapper));
		
		try {
			
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("exam_wrap", rs.getString("exam_wrap"));
				obj.put("per", rs.getDouble("per"));
				obj.put("count", rs.getInt("count"));
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
	public JSONArray generateOneStudentAllExamChart(String studId, String cid) {
		JSONArray array = new JSONArray();
		ResultSet rs = DBUtil.getInstance().executeQuery(
				String.format(QueryConstants.GET_NORMALIZED_DATA_FOR_ONE_STUDENT_ALL_EXAM,cid,studId));
		
		try {
			
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("total", rs.getInt("total"));
				obj.put("per", rs.getDouble("per"));
				obj.put("exam_type", rs.getString("exam_type"));
				array.add(obj);
			}

			JSONObject finalObj = new JSONObject();
			finalObj.accumulate("data", array);

		} catch (Exception e) {
			System.err.println("failed to establish the connection with database." + e.getMessage());
			e.printStackTrace();
		}
		return array;
	}

	@Override
	public JSONArray generateAllStudentAllExamChart(String profId, String cid) {
		JSONArray array = new JSONArray();
		ResultSet rs = DBUtil.getInstance().executeQuery(
				String.format(QueryConstants.GET_NORMALIZED_DATA_FOR_ALL_STUDENT_ALL_EXAM, profId, cid));
		
		try {
			
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("average", rs.getDouble("average"));
				obj.put("exam_wrap", rs.getString("exam_wrap"));
				array.add(obj);
			}

			JSONObject finalObj = new JSONObject();
			finalObj.accumulate("data", array);

		} catch (Exception e) {
			System.err.println("failed to establish the connection with database." + e.getMessage());
			e.printStackTrace();
		}
		return array;
	}

}
