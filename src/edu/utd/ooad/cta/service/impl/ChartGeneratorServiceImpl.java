package edu.utd.ooad.cta.service.impl;


import java.util.Map;

import net.sf.json.JSONArray;

import org.testng.collections.Maps;

import edu.utd.ooad.cta.arch.Service;
import edu.utd.ooad.cta.arch.ServiceRequest;
import edu.utd.ooad.cta.arch.ServiceResponse;
import edu.utd.ooad.cta.dao.ChartGeneratorDAO;
import edu.utd.ooad.cta.dao.impl.ChartGeneratorDAOImpl;

public class ChartGeneratorServiceImpl implements Service {

	private ChartGeneratorDAO chartGeneratorDAO;
	AllStudentOneExamServiceImpl allStudentOneExamService ;
	OneStudenAllExamServiceImpl oneStudentAllExamService;
	AllStudenAllExamServiceImpl allStudentAllExamService;
	
	public ChartGeneratorServiceImpl() {
		chartGeneratorDAO = new ChartGeneratorDAOImpl();
	}
	
	
	@Override
	public ServiceResponse execute(ServiceRequest serviceRequest) {
		ServiceResponse serviceResponse = new ServiceResponse();
		String operationType = serviceRequest.getOperationType();
		switch (operationType) {
		case "AllStudentOneExam":
			allStudentOneExamService = new AllStudentOneExamServiceImpl();
			serviceResponse = allStudentOneExamService.getChartAllStudentOneExam(serviceRequest,chartGeneratorDAO);
			break;
		case "OneStudentAllExam":
			oneStudentAllExamService = new OneStudenAllExamServiceImpl();
			serviceResponse = oneStudentAllExamService.getChartOneStudentAllExam(serviceRequest,chartGeneratorDAO);
			break;
			
		case "AllStudentAllExam":
			allStudentAllExamService = new AllStudenAllExamServiceImpl();
			serviceResponse = allStudentAllExamService.getChartAllStudentAllExam(serviceRequest, chartGeneratorDAO);
			break;
		default:
			break;
		}
		return serviceResponse;
	}
//
//	private ServiceResponse getChartAllStudentAllExam(ServiceRequest serviceRequest) {
//		Map<String, String[]> requestMap = serviceRequest.getServiceAttribute();
//		String c_id = requestMap.get("c_id")[0];
//		String exam_wrap = requestMap.get("exam_wrap")[0];
//		JSONArray allStudentAllData = chartGeneratorDAO.generateAllStudentAllExamChart(c_id, exam_wrap);
//		Map<String, Object> responseMap = Maps.newHashMap();
//		responseMap.put("data", allStudentAllData);
//		ServiceResponse serviceResponse = new ServiceResponse();
//		serviceResponse.setResponse(responseMap);
//		return serviceResponse;
//	}
//	
//	private ServiceResponse getChartOneStudentAllExam(ServiceRequest serviceRequest) {
//		Map<String, String[]> requestMap = serviceRequest.getServiceAttribute();
//		String studID = requestMap.get("studID")[0];
//		String c_id = requestMap.get("c_id")[0];
//		JSONArray oneStudentAllData = chartGeneratorDAO.generateOneStudentAllExamChart(studID, c_id);
//		Map<String, Object> responseMap = Maps.newHashMap();
//		responseMap.put("data", oneStudentAllData);
//		ServiceResponse serviceResponse = new ServiceResponse();
//		serviceResponse.setResponse(responseMap);
//		return serviceResponse;
//	}

}
