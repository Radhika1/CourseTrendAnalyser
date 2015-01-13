package edu.utd.ooad.cta.arch;

/**
 * An interface to perform all the DB operations.
 * 
 */
public interface Service {
	public ServiceResponse execute(ServiceRequest serviceRequest);
}