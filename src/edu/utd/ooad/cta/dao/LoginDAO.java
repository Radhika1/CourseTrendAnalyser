package edu.utd.ooad.cta.dao;

import net.sf.json.JSONArray;
import edu.utd.ooad.cta.domain.LoginBean;

public interface LoginDAO {

	public boolean validateLogin(LoginBean b) ;
	public JSONArray getCourse(String profId);
}
