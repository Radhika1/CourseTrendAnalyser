package edu.utd.ooad.cta.dao.impl;

import java.sql.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.utd.ooad.cta.Constants.QueryConstants;
import edu.utd.ooad.cta.dao.OSAEHelperDAO;
import edu.utd.ooad.cta.db.DBUtil;

public class OSAEHelperDAOImpl implements OSAEHelperDAO {

	@Override
	public JSONArray getStudentMetadata(String studentId, String examWrap,
			int cid) {
		JSONArray array = new JSONArray();
		ResultSet rs = DBUtil.getInstance().executeQuery(
				String.format(QueryConstants.GET_OSAE_METADATA, studentId, cid,
						examWrap+"%"));

		try {

			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("individual_weightage", rs.getDouble("Exam_individual_weightage"));
				obj.put("total_weightage", rs.getInt("Exam_total_weightage"));
				obj.put("exam_marks", rs.getInt("Exam_marks"));
				obj.put("exam_total", rs.getInt("Exam_total"));
				obj.put("exam_wrap", rs.getString("Exam_type"));
				array.add(obj);
			}

			JSONObject finalObj = new JSONObject();
			finalObj.accumulate("data", array);

		} catch (Exception e) {
			System.err
					.println("failed to establish the connection with database."
							+ e.getMessage());
		}
		return array;
	}

}
