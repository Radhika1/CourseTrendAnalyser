package edu.utd.ooad.cta.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import edu.utd.ooad.cta.arch.Service;
import edu.utd.ooad.cta.arch.ServiceRequest;
import edu.utd.ooad.cta.arch.ServiceResponse;
import edu.utd.ooad.cta.service.impl.ChartHelperServiceImpl;

/**
 * Servlet implementation class ChartHelperServlet
 */
public class ChartHelperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	private ServletContext servletContext = null;
	private Service chartHelperService = null;
	
	public ChartHelperServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init();
		servletContext = config.getServletContext();
		chartHelperService = new ChartHelperServiceImpl();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setServiceAttribute(request.getParameterMap());
		serviceRequest.setHttpRequest(request);
		serviceRequest.setOperationType(request.getParameter("OPERATION_TYPE"));
		ServiceResponse serviceResponse = chartHelperService.execute(serviceRequest);

		if (!serviceResponse.isError()) {

			JSONObject finalObj = new JSONObject();
			finalObj.accumulate("data", serviceResponse);

			try {
				PrintWriter pw = response.getWriter();
				pw.write(finalObj.toString());
			} catch (IOException e) {
				System.err.println("Error occurred while sending response back to UI from EmployeeDetailsServlet");
			}

	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
	}

}
