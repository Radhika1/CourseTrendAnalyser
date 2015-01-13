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
import edu.utd.ooad.cta.service.impl.LoginServiceImpl;



/**
 * Servlet implementation class LoginController
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ServletContext servletContext = null;
	private Service loginService = null;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
		super.init();
		servletContext = config.getServletContext();
		loginService = new LoginServiceImpl();
	}
    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setServiceAttribute(request.getParameterMap());
		serviceRequest.setHttpRequest(request);
		serviceRequest.setOperationType(request.getParameter("OPERATION_TYPE"));
		ServiceResponse serviceResponse = loginService.execute(serviceRequest);

		if (!serviceResponse.isError()) {
			JSONObject json = new JSONObject();
			json.accumulate("LOGIN_RESPONSE", serviceResponse);
			try {
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
			} catch (IOException e) {
				System.err.println(
						"LoginServlet:Error occurred while sending response back to UI from LoginServlet" + e.getMessage());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
