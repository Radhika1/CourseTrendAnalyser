package edu.utd.ooad.cta.arch;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * This class represents a Carrier which will carry all input parameters from
 * Servlet to Service layer.
 * 
 * 
 * 
 */
public class ServiceRequest {

	// A variable to use which Operation need to be performed at Service layer.
	private String operationType = null;

	private HttpServletRequest httpRequest;

	// A container which will contain all the Input Parameters required to serve
	// any service request
	private Map<String, String[]> serviceAttribute = null;

	/*
	 * Constructor : To initialize ServiceAttribute container.
	 */
	public ServiceRequest() {
		this.serviceAttribute = new HashMap<String, String[]>();
	}

	public Map<String, String[]> getServiceAttribute() {
		return serviceAttribute;
	}

	public void setServiceAttribute(Map<String, String[]> serviceAttribute) {
		this.serviceAttribute = serviceAttribute;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
}