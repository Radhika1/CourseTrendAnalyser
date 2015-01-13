package edu.utd.ooad.cta.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

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
import edu.utd.ooad.cta.utils.Conversion;

/**
 * Servlet implementation class GradingDetailsController
 */
@WebServlet("/GradingDetailsController")
public class GradingDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd;
	
	Statement statement = null;;
	Connection connection;
	String result = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradingDetailsController() {
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
		HttpSession session = request.getSession(true);
		boolean flag[] = new boolean [4];
		int courseNo = Integer.parseInt(request.getParameter("COURSENO"));
		GradingDetailsBean gdb = new GradingDetailsBean();
		GradingDetailsDAO gdd = new GradingDetailsDAOImpl();
		int quizCount = Integer.parseInt(request.getParameter("quizCount"));
		int projectCount = Integer.parseInt(request.getParameter("projectCount"));
		int assignmentCount = Integer.parseInt(request.getParameter("assignmentCount"));
		int examCount = Integer.parseInt(request.getParameter("examCount"));
		double converted;String individualWeightage;int totalWeightage = 0,i=1;
		try{
			ArrayList<Integer> Cid = gdd.getCid(courseNo);
			if(Cid.size() > 0){
				totalWeightage = 0;i=1; 
				gdb.setCid(Cid);
				ArrayList<GradingDetailsBean> rs = new ArrayList<GradingDetailsBean>();
				rs = gdd.checkValueExists(gdb);
				if(rs.size()!=0){
					session.setAttribute("deleterows", rs);
					session.setAttribute("gradingbean", gdb);
					rd = request.getRequestDispatcher("pages/jsp/deleteGradingDetails.jsp");
					rd.forward(request,response);
				}
				else{
				if(quizCount > 0){
					totalWeightage = Integer.parseInt(request.getParameter("quizText"));
					gdb.setTotalWeightage(totalWeightage);	
						individualWeightage = request.getParameterValues("quizarray")[0];
						System.out.println(individualWeightage);
						String [] ss = individualWeightage.split(",");
						for(String s: ss){
							gdb.setExamType("Quiz"+i++);
							converted = Conversion.Convert(Integer.parseInt(s), totalWeightage);
							gdb.setIndividualWeightage(converted);
							flag[0] = gdd.addGradingValues(gdb);
						}
				}
				if(projectCount > 0){
					i=1;
					totalWeightage = Integer.parseInt(request.getParameter("projectText"));
					gdb.setTotalWeightage(totalWeightage);
						individualWeightage = request.getParameterValues("projectarray")[0];
						System.out.println(individualWeightage);
						String [] ss = individualWeightage.split(",");
						for(String s: ss){
							gdb.setExamType("Project"+i++);
							converted = Conversion.Convert(Integer.parseInt(s), totalWeightage);
							gdb.setIndividualWeightage(converted);
							flag[1] = gdd.addGradingValues(gdb);
						}
				}
				if(assignmentCount > 0){
					i=1;
					totalWeightage = Integer.parseInt(request.getParameter("assignmentText"));
					gdb.setTotalWeightage(totalWeightage);	
						individualWeightage = request.getParameterValues("assignmentarray")[0];
						System.out.println(individualWeightage);
						String [] ss = individualWeightage.split(",");
						for(String s: ss){
							gdb.setExamType("Homework"+i++);
							converted = Conversion.Convert(Integer.parseInt(s), totalWeightage);
							gdb.setIndividualWeightage(converted);
							flag[2] = gdd.addGradingValues(gdb);
						}
				}
				if(examCount > 0){
					i=1;
					totalWeightage = Integer.parseInt(request.getParameter("examText"));
					gdb.setTotalWeightage(totalWeightage);	
					individualWeightage = request.getParameterValues("examarray")[0];
					System.out.println(individualWeightage);
					String [] ss = individualWeightage.split(",");
					for(String s: ss){
						gdb.setExamType("Exam"+i++);
						converted = Conversion.Convert(Integer.parseInt(s), totalWeightage);
						gdb.setIndividualWeightage(converted);
						flag[3] = gdd.addGradingValues(gdb);
					}
				}
				boolean flag1 = false;
				for(boolean f : flag){
					if(f == true){
						flag1 = true;
						break;
					}
				}
				if(flag1 == true){
					rd = request.getRequestDispatcher("pages/jsp/professorHome.jsp");
					rd.forward(request,response);
				}
				else{
					rd = request.getRequestDispatcher("pages/jsp/Unsuccessfull.jsp");
					rd.forward(request,response);
				}
			}
			}
		}
		catch(Exception e){
			System.out.println("Exception" + e.getMessage());
		}
	}
}
