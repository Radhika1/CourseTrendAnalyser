package edu.utd.ooad.cta.service.impl;


import java.util.Map;

import net.sf.json.JSONArray;

import org.testng.collections.Maps;

import edu.utd.ooad.cta.arch.ServiceRequest;
import edu.utd.ooad.cta.arch.ServiceResponse;
import edu.utd.ooad.cta.dao.ChartGeneratorDAO;
import edu.utd.ooad.cta.utils.Utils;

public class AllStudenAllExamServiceImpl {


	public AllStudenAllExamServiceImpl() {
	}
	
	public ServiceResponse getChartAllStudentAllExam(ServiceRequest serviceRequest,ChartGeneratorDAO chartGeneratorDAO) {

		Map<String, String[]> requestMap = serviceRequest.getServiceAttribute();
		String prof_id = Utils.getCookieValue("username",serviceRequest.getHttpRequest().getCookies());
		String c_id = requestMap.get("c_id")[0];
		JSONArray allStudentAllData = chartGeneratorDAO.generateAllStudentAllExamChart(prof_id, c_id);
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap.put("data", allStudentAllData);
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setResponse(responseMap);
		return serviceResponse;
	}

}
