package com.synechron.prm.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.synechron.prm.util.CustomDate;
import com.synechron.prm.util.HibernateUtil;

public class GraphDao {

	CustomDate date=new CustomDate();
	


	public int getTotalResources(int pid){

		Session session = HibernateUtil.getSessionFactory().openSession();
		int resources=0;
		Transaction transaction=null;
		try {
			transaction=session.beginTransaction();
			Criteria criteria = null;//session.createCriteria(ResourceMaster.class).add(Restrictions.eq("projectmaster", pid)); 
			criteria.setProjection(Projections.rowCount()); 
			resources=((Integer)criteria.list().get(0)).intValue();

			session.flush();
			session.clear();
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{
			transaction.commit();
			session.close();
		}

		return resources;


	}

	//getting count for all project
	public int[] getBugZillaArrayForAll(int arrayLength){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query=null;
		List result=null;
		int[]chartData = null;
		Transaction transaction=null;

			
			chartData=new int[arrayLength];
		try{
			transaction=session.beginTransaction();
			String sqlQuery="SELECT b.projectid, AVG(100-((p1_bug * 10) + (p2_bug * 6) + (p3_bug * 3) + (p4_bug * 1))/c.res_cnt) 'Score'" +
					" FROM" +
					" (SELECT projectid, MAX(modifiedDate) 'Max_Date' FROM bug_master " +
					" WHERE modifiedDate BETWEEN '"+date.getMonthStartTime()+"'  AND  '"+date.getMonthEndTime()+"'" +
					" AND projectid IN (SELECT id FROM projectmaster WHERE isActive='yes')" +
					" GROUP BY projectid, DATE(modifiedDate)) a, bug_master b, (SELECT project_id, COUNT(*) 'res_cnt' FROM resource_master GROUP BY project_id) c" +
					" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
					" AND b.projectid = c.project_id" +
					" GROUP BY b.projectid";
			
			query=session.createSQLQuery(sqlQuery)
					.addScalar("projectid", Hibernate.INTEGER)
					.addScalar("Score", Hibernate.INTEGER);
				

			result=query.list();
			Iterator resultIterator = result.iterator();

			int j=0;
			while(resultIterator.hasNext())
			{
				Object[] obj = (Object[]) resultIterator.next();
				for (int i = 0; i < obj.length; i++) {
					//System.out.print(obj[i] + "\t");

					chartData[j]=(Integer) obj[1];

				}
				j++;
				//System.out.println("");

			}
			session.flush();
			session.clear();
			
		}catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}finally{
			transaction.commit();
			session.close();
		}

		
		return chartData;
	}

	public int[] getRedMindArrayForAll(int arrayLength){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query=null;
		List result=null;
		int[]chartData = null;
		Transaction transaction=null;
		
		chartData=new int[arrayLength];
		
			try{
				transaction=session.beginTransaction();
				
				String sqlQuery="SELECT b.projectid, AVG(100-(bug_count)/c.res_cnt) 'Score'" +
						" FROM" +
						" (SELECT projectid, MAX(modifiedDate) 'Max_Date' FROM redmind_master " +
						" WHERE modifiedDate BETWEEN '"+date.getMonthStartTime()+"'  AND  '"+date.getMonthEndTime()+"'" +
						" AND projectid IN (SELECT id FROM projectmaster WHERE isActive='yes')" +
						" GROUP BY projectid, DATE(modifiedDate)) a, redmind_master b, (SELECT project_id, COUNT(*) 'res_cnt' FROM resource_master GROUP BY project_id) c" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" AND b.projectid = c.project_id" +
						" GROUP BY b.projectid";
				
				query=session.createSQLQuery(sqlQuery)
					.addScalar("projectid", Hibernate.INTEGER)
					.addScalar("Score", Hibernate.INTEGER);


			result=query.list();
			Iterator resultIterator = result.iterator();

			int j=0;
			while(resultIterator.hasNext())
			{
				Object[] obj = (Object[]) resultIterator.next();
				for (int i = 0; i < obj.length; i++) {
					//System.out.print(obj[i] + "\t");

					chartData[j]=(Integer) obj[1];

				}
				j++;
				//System.out.println("");

			}
			session.flush();
			session.clear();
			}catch (Exception e) {
				// TODO: handle exception
				transaction.rollback();
			}finally{
				transaction.commit();
				session.close();
			}
		
		return chartData;
	}


	//getting count for particular project
	public int[] getBugZillaArray(int pid){


		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query=null;
		List result=null;
		int[]chartData = null;
		int resources=getTotalResources(pid);
		Transaction transaction=null;
		chartData=new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0};
		
			try{
				
				transaction=session.beginTransaction();
		
			String sqlQuery="SELECT MONTH(modifiedDate)'monthDate'," +
					" AVG(100-((p1_bug * 10) + (p2_bug * 6) + (p3_bug * 3) + (p4_bug * 1))/" +
					" (SELECT COUNT(*) FROM resource_master WHERE project_id="+pid+")) 'Score'" +
					" FROM" +
					" (SELECT projectid, MAX(modifiedDate) 'Max_Date' " +
					" FROM bug_master " +
					" WHERE modifiedDate BETWEEN '"+date.getYearStartTime()+"'  AND  '"+date.getYearEndTime()+"'" +
					" AND projectid ="+pid+" GROUP BY DATE(modifiedDate)) a, bug_master b" +
					" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
					" GROUP BY MONTH(modifiedDate)";
			
			
			//System.out.println(sqlQuery);
			query=session.createSQLQuery(sqlQuery)
					.addScalar("monthDate", Hibernate.INTEGER)
					.addScalar("Score", Hibernate.INTEGER);

			result=query.list();
			Iterator resultIterator = result.iterator();

	
			while(resultIterator.hasNext())
			{
				Object[] obj = (Object[]) resultIterator.next();
				for (int i = 0; i < obj.length; i++) {
					//System.out.print(obj[i] + "\t");

					int j=(Integer)obj[0];
					chartData[--j]=(Integer) obj[i];


				}
				//System.out.println("");

			}
			session.flush();
			session.clear();
			}catch (Exception e) {
				// TODO: handle exception
				transaction.rollback();
			}finally{
				transaction.commit();
				session.close();
			}
		
		return chartData;

	}

	public int[] getRedMindArray(int pid){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query=null;
		List result=null;
		int[]chartData = null;
		Transaction transaction=null;

		
			chartData=new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0};
		
			try{
				
				transaction=session.beginTransaction();
				
				String sqlQuery="SELECT MONTH(modifiedDate)'Month'," +
						" AVG(100-(bug_count)/(SELECT COUNT(*) FROM resource_master WHERE project_id="+pid+")) 'Score'" +
						" FROM" +
						" (SELECT projectid, MAX(modifiedDate) 'Max_Date' FROM redmind_master " +
						" WHERE modifiedDate BETWEEN '"+date.getYearStartTime()+"'  AND  '"+date.getYearEndTime()+"'" +
						" AND projectid = "+pid+" GROUP BY DATE(modifiedDate)) a, redmind_master b" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" GROUP BY MONTH(modifiedDate)";
				
				
				query=session.createSQLQuery(sqlQuery)
					.addScalar("Month", Hibernate.INTEGER)
					.addScalar("Score", Hibernate.INTEGER);

			result=query.list();
			Iterator resultIterator = result.iterator();

			while(resultIterator.hasNext())
			{
				Object[] obj = (Object[]) resultIterator.next();
				for (int i = 0; i < obj.length; i++) {
					//System.out.print(obj[i] + "\t");

					int j=(Integer)obj[0];
					chartData[--j]=(Integer) obj[i];


				}
				//System.out.println("");

			}
			session.flush();
			session.clear();
			}catch (Exception e) {
				// TODO: handle exception
				transaction.rollback();
			}finally{
				transaction.commit();
				session.close();
			}
			
			return chartData;
	}

	/*public static void main(String []args){
			GraphDao dao=new GraphDao();
			
			//int i=dao.getTotalResources(1);
			//System.out.println(i);
			
			//dao.getBugZillaArray(1);
			dao.getRedMindArray(1);
			//dao.getBugZillaArrayForAll(4);
			//dao.getRedMindArrayForAll(4);
			

			
		}*/


}
