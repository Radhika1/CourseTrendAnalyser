package edu.utd.ooad.cta.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import edu.utd.ooad.cta.Constants.QueryConstants;
import edu.utd.ooad.cta.dao.GradingDetailsDAO;
import edu.utd.ooad.cta.db.DBUtil;
import edu.utd.ooad.cta.domain.GradingDetailsBean;

public class GradingDetailsDAOImpl implements GradingDetailsDAO{

	public GradingDetailsDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Integer> getCid(int courseId){
		ArrayList<Integer> Cid = new ArrayList<Integer>();
		ResultSet rows = DBUtil.getInstance().executeQuery(String.format(QueryConstants.GET_COURSE_ID,courseId));
		try{
			while(rows.next())
				Cid.add(rows.getInt("C_id"));
		}
		catch (Exception e){
			System.out.println(e.getMessage());;
		}
		return Cid;
	}
	
	public ArrayList<GradingDetailsBean> checkValueExists(GradingDetailsBean g){
		//String ExamType = g.getExamType();
		ArrayList<Integer> Cid = g.getCid();
		ResultSet rows = null;
		GradingDetailsBean gdb;
		ArrayList<GradingDetailsBean> rs = new ArrayList<GradingDetailsBean>();
		try{
			for(int i=0;i<Cid.size();i++){
				rows = DBUtil.getInstance().executeQuery(String.format(QueryConstants.GET_EXAM_TYPE,Cid.get(i)));
				while(rows.next()){
					gdb = new GradingDetailsBean();
					gdb.setExamType(rows.getString(1));
					gdb.setIndividualWeightage(rows.getDouble(2));
					gdb.setTotalWeightage(rows.getInt(3));
					gdb.getCid().add(Cid.get(i));
					rs.add(gdb);
				}
			}
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return rs;
		}
	}
	
	public boolean deleteExamTypeValues(GradingDetailsBean g){
		boolean flag = true;
		ArrayList<Integer> Cid = g.getCid();
		ResultSet rows = null;
		try{
			for(int courseid : Cid){
				int deleted = DBUtil.getInstance().executeUpdate(String.format(QueryConstants.DELETE_EXAM_TYPE,courseid));
				if(deleted > 0)
					flag = true;
				else
					flag = false;
			}
			return flag;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean addGradingValues(GradingDetailsBean g){
		String ExamType = g.getExamType();
		int inserted = 0;
		double IndividualWeightage = g.getIndividualWeightage();
		int TotalWeightage = g.getTotalWeightage();
		ArrayList<Integer> Cid = g.getCid();
		boolean flag = true;
		ResultSet rows = null;
		try{
			for(int courseid : Cid){
				inserted = DBUtil.getInstance().executeUpdate(String.format(QueryConstants.INSERT_EXAM_TYPE,ExamType, IndividualWeightage, TotalWeightage, courseid));
				if(inserted == 0)
					flag = false;
			}
			return flag;
			
		}
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return false;
		 }		
	}
}