package com.synechron.prm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CustomDate {




	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date1=new Date();

	//get current Starting Date
	public String getTodayStartTime(){
		Calendar cal = Calendar.getInstance();
		String todayStartTime=sdf.format(cal.getTime()).concat(" 00:00:00");

		return todayStartTime;
	}

	//get current Ending Date
	public String getTodayEndTime(){
		Calendar cal = Calendar.getInstance();
		String todayEndTime=sdf.format(cal.getTime()).concat(" 23:59:59");

		return todayEndTime;
	}

	//starting day of week
	public String getStartWeek(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String startWeekDay=sdf.format(cal.getTime()).concat(" 00:00:00");

		return startWeekDay;
	}

	//last day of week
	public String getEndWeek(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DATE, 6);
		String endWeekDay=sdf.format(cal.getTime()).concat(" 23:59:59"); 

		return endWeekDay;
	}

	public String getMonthYear(String dateString){

		String[] d=dateString.split("-");
		String d1=null;

		String[] strMonths = new String[]{"NA",
				"Jan", "Feb", "Mar", "Apr",  
				"May", "Jun", "Jul", "Aug",     
				"Sep", "Oct",  "Nov",  "Dec"

		};
		try{

			int monthIndex=new Integer(d[1]);
			--monthIndex;
			System.out.println("Inside getMonthYear "+ monthIndex + strMonths[monthIndex]);
			d1=strMonths[monthIndex]+", " + d[0] ;

		}catch (Exception e) {
			System.out.println("Error in Custom Date");
		}


		return d1.trim();
	}

	public String getDateMonthYear(String dateString){

		String[] d=dateString.split(",");
		Date date = null;

		if(d.length==2){
			try {
				date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(d[0]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH);
			month++;

			String d1=d[1]+"-"+ new Integer(month).toString() + "-01" ;
			return d1.trim();
		}else{

			return dateString.trim();
		}

	}


	public String getMonthStartTime(){
		Calendar cal = Calendar.getInstance();

		cal.setTime(date1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String monthStartTime=sdf.format(cal.getTime()).concat(" 00:00:00");
		
		return monthStartTime;

	}

	public String getMonthEndTime(){
		Calendar cal = Calendar.getInstance();

		cal.setTime(date1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		String monthEndTime=sdf.format(cal.getTime()).concat(" 23:59:59");
	
		return monthEndTime;

	}
	//get current Starting Date
	public String getYearStartTime(){
		Calendar cal = Calendar.getInstance();

		cal.setTime(date1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String yearStartTime=sdf.format(cal.getTime()).concat(" 00:00:00");

		return yearStartTime;
	}

	//get current Ending Date
	public String getYearEndTime(){
		Calendar cal = Calendar.getInstance();

		cal.setTime(date1);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);

		String yearEndTime=sdf.format(cal.getTime()).concat(" 23:59:59");

		return yearEndTime;
	}



	/*public static void main(String []args){

		CustomDate date=new CustomDate();

		//System.out.println(date.getMonthYear("2012-08-01"));

		//System.out.println(date.getDateMonthYear("Aug, 2012"));
		//System.out.println(date.getYearStartTime());
		//System.out.println(date.getYearEndTime());
		System.out.println(date.getMonthStartTime());
		System.out.println(date.getMonthEndTime());

	}*/

}
