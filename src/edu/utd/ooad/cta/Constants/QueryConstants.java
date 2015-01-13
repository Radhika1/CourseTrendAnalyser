package edu.utd.ooad.cta.Constants;

public class QueryConstants {
	public static final String GET_NORMALIZED_DATA_FOR_ALL_STUDENT_ONE_EXAM = "select exam_wrap,per, count(stud_id) as count from "
			+ "(select exam_wrap,sum((((exam_marks * 100) / exam_total) * "
			+ "(select Exam_individual_weightage from exam_type e where e.exam_type = ct.exam_type)) / 100) "
			+ "as per, stud_id from Course_Trend ct where C_id =%s  and exam_wrap='%s' group by stud_id,exam_wrap) a group by a.stud_id,exam_wrap;";

	public static final String GET_COURSE_DATA = "select course_id, Course_sect_no , c_id from course where Prof_id='%s'";

	public static final String GET_EXAM_WRAP_DATA = "select distinct(exam_wrap) as exam_wrap from Course_Trend where Prof_id='%s' and C_id=%s;";

	public static final String GET_NORMALIZED_DATA_FOR_ONE_STUDENT_ALL_EXAM = "select ((((exam_marks * (select Exam_individual_weightage from exam_type e where "
			+ "e.exam_type = ct.exam_type)) / exam_total) * (select  Exam_total_weightage from exam_type e where e.exam_type = ct.exam_type)) / (select"
			+ " Exam_individual_weightage from exam_type e where  e.exam_type = ct.exam_type)) as per,stud_id,exam_type,(select Exam_total_weightage from exam_type"
			+ " e where e.exam_type = ct.exam_type) as total from Course_Trend ct where C_id=%s and Stud_id='%s'";
	
	public static final String GET_OSAE_METADATA = "select e.Exam_individual_weightage, e.Exam_total_weightage, c.Exam_marks, c.Exam_total, e.Exam_type from exam_type e inner join "
			+ "(select * from Course_Trend where Stud_id='%s' and C_id=%s and exam_type like '%s') as c on e.C_id=c.C_id and e.Exam_type like c.Exam_type";

	public static final String DO_LOGIN = "select Prof_id,Prof_pwd from Professor";
	
	public static final String GET_NORMALIZED_DATA_FOR_ALL_STUDENT_ALL_EXAM = "select sum(c*(Exam_individual_weightage)/100)  as average,d.exam_wrap from exam_type e "
			+ "inner join (select (sum(exam_marks)/sum(exam_total))*100 as c, exam_type,Exam_wrap from Course_Trend p where Prof_id='%s' and C_id=%s group by exam_type) d on "
			+ "e.exam_type like d.exam_type group by d.exam_wrap";
	
	public static final String GET_COURSE_ID="select C_id from course where Course_id = '%s'";
	
	public static final String GET_EXAM_TYPE = "Select * from exam_type where C_id =%s";
	
	public static final String DELETE_EXAM_TYPE = "delete from exam_type where C_id=%s";
	
	public static final String INSERT_EXAM_TYPE = "insert into exam_type(Exam_type, Exam_individual_weightage, Exam_total_weightage, C_id) "
						+ "values('%s',%s,%s,%s)";
	
	public static final String SELECT_DISTICT_COURSE = "select distinct course_id from course where prof_id='%s'";
	
}
