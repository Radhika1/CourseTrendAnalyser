package edu.utd.ooad.cta.service.impl;


import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.testng.collections.Maps;

import edu.utd.ooad.cta.arch.Service;
import edu.utd.ooad.cta.arch.ServiceRequest;
import edu.utd.ooad.cta.arch.ServiceResponse;
import edu.utd.ooad.cta.dao.LoginDAO;
import edu.utd.ooad.cta.dao.impl.LoginDAOImpl;
import edu.utd.ooad.cta.domain.LoginBean;
import edu.utd.ooad.cta.utils.Utils;

public class LoginServiceImpl implements Service {

	private LoginDAO loginServiceDAO;

	public LoginServiceImpl() {
		loginServiceDAO = new LoginDAOImpl();
	}
	
	
	@Override
	public ServiceResponse execute(ServiceRequest serviceRequest) {
		ServiceResponse serviceResponse = new ServiceResponse();
		String operationType = serviceRequest.getOperationType();
		switch (operationType) {
		case "CHECK_LOGIN":
			serviceResponse = doLogin(serviceRequest);
			break;
		case "GET_DISTINCT_COURSE_LIST":
			serviceResponse = getCourseList(serviceRequest);
			break;
		default:
			break;
		}
		return serviceResponse;
	}

	private ServiceResponse doLogin(ServiceRequest serviceRequest) {
		Map<String, String[]> requestMap = serviceRequest.getServiceAttribute();
		LoginBean bean = new LoginBean();
		bean.setUsername(requestMap.get("uname")[0]);
		bean.setPassword(requestMap.get("passw")[0]);
		Boolean isValidLogin = loginServiceDAO.validateLogin(bean);
		if(isValidLogin){
			HttpSession session = serviceRequest.getHttpRequest().getSession();
			session.setAttribute("uname",requestMap.get("uname")[0]);
		}
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap.put("IS_AUTHENTIC_USER", isValidLogin);
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setResponse(responseMap);
		return serviceResponse;
	}
	
	private ServiceResponse getCourseList(ServiceRequest serviceRequest) {
		JSONArray courseData = loginServiceDAO.getCourse((Utils.getCookieValue("username",serviceRequest.getHttpRequest().getCookies())));
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap.put("dcdata", courseData);
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setResponse(responseMap);
		return serviceResponse;
	}

}
