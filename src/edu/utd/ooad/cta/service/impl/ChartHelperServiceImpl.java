package edu.utd.ooad.cta.service.impl;


import java.util.Map;

import javax.servlet.http.Cookie;

import net.sf.json.JSONArray;

import org.testng.collections.Maps;

import edu.utd.ooad.cta.arch.Service;
import edu.utd.ooad.cta.arch.ServiceRequest;
import edu.utd.ooad.cta.arch.ServiceResponse;
import edu.utd.ooad.cta.dao.ChartHelperDAO;
import edu.utd.ooad.cta.dao.impl.ChartHelperDAOImpl;
import edu.utd.ooad.cta.utils.Utils;

public class ChartHelperServiceImpl implements Service {

	private ChartHelperDAO chartHelperDAO;

	public ChartHelperServiceImpl() {
		chartHelperDAO = new ChartHelperDAOImpl();
	}
	
	
	@Override
	public ServiceResponse execute(ServiceRequest serviceRequest) {
		ServiceResponse serviceResponse = new ServiceResponse();
		String operationType = serviceRequest.getOperationType();
		switch (operationType) {
		case "getCourseData":
			serviceResponse = getCourseData(serviceRequest);
			break;
		case "getExamWrapData":
			serviceResponse = getExamWrapData(serviceRequest);
			break;
		default:
			break;
		}
		return serviceResponse;
	}

	private ServiceResponse getCourseData(ServiceRequest serviceRequest) {
		JSONArray courseData = chartHelperDAO.getCourseData((Utils.getCookieValue("username",serviceRequest.getHttpRequest().getCookies())));
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap.put("cdata", courseData);
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setResponse(responseMap);
		return serviceResponse;
	}
	
	private ServiceResponse getExamWrapData(ServiceRequest serviceRequest) {
		Map<String, String[]> requestMap = serviceRequest.getServiceAttribute();
		String c_id = requestMap.get("c_id")[0];
		JSONArray examWrapData = chartHelperDAO.getExamWrapData(Utils.getCookieValue("username",serviceRequest.getHttpRequest().getCookies()),c_id);
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap.put("edata", examWrapData);
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setResponse(responseMap);
		return serviceResponse;
	}

}
