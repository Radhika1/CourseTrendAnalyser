package edu.utd.ooad.cta.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.utd.ooad.cta.dao.GradingDetailsDAO;
import edu.utd.ooad.cta.dao.impl.GradingDetailsDAOImpl;
import edu.utd.ooad.cta.domain.GradingDetailsBean;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesssion = request.getSession();
		GradingDetailsBean dgb = (GradingDetailsBean)sesssion.getAttribute("gradingbean");
		GradingDetailsDAO gdd = new GradingDetailsDAOImpl();
		String check = request.getParameter("submit");
		if(check.equalsIgnoreCase("yes")){
			gdd.deleteExamTypeValues(dgb);
			rd = request.getRequestDispatcher("pages/jsp/gradingDetails.jsp");
			rd.forward(request,response);
		}
		else{
			rd = request.getRequestDispatcher("pages/jsp/gradingDetails.jsp");
			rd.forward(request,response);
		}
	}

}
