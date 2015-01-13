package edu.utd.ooad.cta.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.json.JSONArray;
import edu.utd.ooad.cta.Constants.QueryConstants;
import edu.utd.ooad.cta.dao.LoginDAO;
import edu.utd.ooad.cta.db.DBUtil;
import edu.utd.ooad.cta.domain.LoginBean;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public boolean validateLogin(LoginBean bean) {
		boolean isValidLogin = false;
		ResultSet rs = DBUtil.getInstance().executeQuery(
				QueryConstants.DO_LOGIN);

		try {
			while (rs.next()) {
				if (rs.getString("Prof_id").equals(bean.getUsername())
						&& rs.getString("Prof_pwd")
								.equals(bean.getPassword())) {
					isValidLogin = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValidLogin;
	}
	
	
	public JSONArray getCourse(String ProfId){
		ResultSet rs = DBUtil.getInstance().executeQuery(
				String.format(QueryConstants.SELECT_DISTICT_COURSE,ProfId)) ;
		JSONArray coursel = new JSONArray();
 		try{
			while(rs.next()){
				coursel.add(rs.getInt(1));
			}
			return coursel;
		}
		catch(Exception e){
			e.printStackTrace();
			return coursel;
		}
	}
}
