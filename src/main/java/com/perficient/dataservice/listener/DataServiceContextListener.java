package com.perficient.dataservice.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DataServiceContextListener implements ServletContextListener {
	
	private Timer dataLoadingTimer = null;

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		if (dataLoadingTimer != null) {
			dataLoadingTimer.cancel();
			dataLoadingTimer = null;
			System.out.println("Cancel timer...");
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		String dataLoadingTime = arg0.getServletContext().getInitParameter("dataLoadingTime");
		System.out.println(dataLoadingTime);
		
		String[] dateParts = dataLoadingTime.split(":");
		int hour = 3, minute = 0, second = 0;
		if (dateParts.length == 3) {
			try {
				hour = Integer.parseInt(dateParts[0]);
				minute = Integer.parseInt(dateParts[1]);
				second = Integer.parseInt(dateParts[2]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				// ignore
			}
		}
		
		Date now = new Date();
		System.out.println(dateToString(now));

		long dayInterval =  60 * 60 * 24 * 1000; // in milliseconds
		Date tomorrow = new Date(now.getTime() + dayInterval);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tomorrow);
		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		
		Date dataLoadingTimeD = calendar.getTime();
		System.out.println(dateToString(dataLoadingTimeD));
		
		dataLoadingTimer = new Timer("Data Loading", true);
		
		// Date firstLoadingTime = new Date(); 
		// dataLoadingTimer.schedule(new DataLoadingTask(), 5000);
	}
	
	private String dateToString(Date date) {  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        return format.format(date);
    }  
	
	public class DataLoadingTask extends TimerTask {

		@Override
		public void run() {
			System.out.println("Running timer task...");
			dataLoadingTimer.schedule(new DataLoadingTask(), 5000);
		}
		
	}

}
