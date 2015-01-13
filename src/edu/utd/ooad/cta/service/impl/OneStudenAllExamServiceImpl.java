package edu.utd.ooad.cta.service.impl;


import java.util.Map;

import net.sf.json.JSONArray;

import org.testng.collections.Maps;

import edu.utd.ooad.cta.arch.Service;
import edu.utd.ooad.cta.arch.ServiceRequest;
import edu.utd.ooad.cta.arch.ServiceResponse;
import edu.utd.ooad.cta.dao.ChartGeneratorDAO;
import edu.utd.ooad.cta.dao.impl.ChartGeneratorDAOImpl;

public class OneStudenAllExamServiceImpl {


	public OneStudenAllExamServiceImpl() {
	}
	
	public ServiceResponse getChartOneStudentAllExam(ServiceRequest serviceRequest,ChartGeneratorDAO chartGeneratorDAO) {
		Map<String, String[]> requestMap = serviceRequest.getServiceAttribute();
		String studID = requestMap.get("studID")[0];
		String c_id = requestMap.get("c_id")[0];
		JSONArray oneStudentAllData = chartGeneratorDAO.generateOneStudentAllExamChart(studID, c_id);
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap.put("data", oneStudentAllData);
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setResponse(responseMap);
		return serviceResponse;
	}

}
