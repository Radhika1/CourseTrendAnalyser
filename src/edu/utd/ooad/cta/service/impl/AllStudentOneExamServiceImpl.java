package edu.utd.ooad.cta.service.impl;


import java.util.Map;

import net.sf.json.JSONArray;

import org.testng.collections.Maps;

import edu.utd.ooad.cta.arch.ServiceRequest;
import edu.utd.ooad.cta.arch.ServiceResponse;
import edu.utd.ooad.cta.dao.ChartGeneratorDAO;

public class AllStudentOneExamServiceImpl {


	public AllStudentOneExamServiceImpl() {
	}
	
	public ServiceResponse getChartAllStudentOneExam(ServiceRequest serviceRequest, ChartGeneratorDAO chartGeneratorDAO) {
		Map<String, String[]> requestMap = serviceRequest.getServiceAttribute();
		String c_id = requestMap.get("c_id")[0];
		String exam_wrap = requestMap.get("exam_wrap")[0];
		JSONArray allStudentAllData = chartGeneratorDAO.generateAllStudentOneExamChart(c_id, exam_wrap);
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap.put("data", allStudentAllData);
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setResponse(responseMap);
		return serviceResponse;
	}
	
	
}
