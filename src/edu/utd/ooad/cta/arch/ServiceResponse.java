package edu.utd.ooad.cta.arch;

import java.util.Map;

/**
 * This class will be used to carry all server side responses towards Servlet
 * 
 * 
 * 
 */
public class ServiceResponse {

	// Flag signifies error in Service Execution.
	private Boolean isError = Boolean.FALSE;

	// To contain Error Message if there is any error in execution of Service.
	private String errorMessage = null;

	// The responseMap which contains all the parameters/attributes will be
	// carried to Servlet.
	private Map<String, Object> response = null;

	/**
	 * To set Flag
	 * 
	 * @param isError
	 */
	public void setError(Boolean isError) {
		this.isError = isError;
	}

	/**
	 * To Get Flag of Error.
	 * 
	 * @return
	 */
	public Boolean isError() {
		return this.isError;
	}

	/**
	 * Get the Error message.
	 * 
	 * @return
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Set the error message.
	 * 
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Get Response
	 * 
	 * @return
	 */
	public Map<String, Object> getResponse() {
		return response;
	}

	/**
	 * Set Response.
	 * 
	 * @param response
	 */
	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}

	public ServiceResponse(Map<String, Object> response) {
		this(response, "", false);
	}

	public ServiceResponse(Map<String, Object> response, String errorMessage) {
		this(response, errorMessage, false);
	}

	public ServiceResponse(Map<String, Object> response, String errorMessage,
			boolean isError) {
		this.response = response;
		this.errorMessage = errorMessage;
		this.isError = isError;
	}

	public ServiceResponse() {
	}
}