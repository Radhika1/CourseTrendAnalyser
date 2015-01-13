package edu.utd.ooad.cta.service.impl;

import java.util.Map;

import net.sf.json.JSONArray;

import org.testng.collections.Maps;

import edu.utd.ooad.cta.arch.Service;
import edu.utd.ooad.cta.arch.ServiceRequest;
import edu.utd.ooad.cta.arch.ServiceResponse;
import edu.utd.ooad.cta.dao.OSAEHelperDAO;
import edu.utd.ooad.cta.dao.impl.OSAEHelperDAOImpl;

public class OneStudentAllExamHelperImpl implements Service {
	
	OSAEHelperDAO osaeHelper;
	
	public OneStudentAllExamHelperImpl(){
		osaeHelper = new OSAEHelperDAOImpl();
	}

	@Override
	public ServiceResponse execute(ServiceRequest serviceRequest) {
		ServiceResponse serviceResponse = new ServiceResponse();
		String operationType = serviceRequest.getOperationType();
		switch (operationType) {
		case "OSAE_HELP":
			serviceResponse = getDataForDisplay(serviceRequest);
			break;
		default:
			break;
		}
		return serviceResponse;
	}

	private ServiceResponse getDataForDisplay(ServiceRequest serviceRequest) {
		Map<String, String[]> requestMap = serviceRequest.getServiceAttribute();
		String studentId = requestMap.get("studId")[0];
		String examWrap = requestMap.get("examWrap")[0];
		int cid = Integer.parseInt(requestMap.get("cid")[0]);
		JSONArray studentMetadata = osaeHelper.getStudentMetadata(studentId, examWrap, cid);
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap.put("data", studentMetadata);
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setResponse(responseMap);
		return serviceResponse;
	}

}
