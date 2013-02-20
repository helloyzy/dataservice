package com.perficient.dataservice.listener;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.perficient.dataservice.tasks.DataLoadTask;
import com.perficient.dataservice.utils.GlobalVars;
import com.perficient.dataservice.utils.TaskManager;
import com.perficient.dataservice.utils.TaskManager.DelayType;

public class DataServiceContextListener implements ServletContextListener {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public void contextDestroyed(ServletContextEvent arg0) {}
	
	private void initGlobalVariables(ServletContextEvent context) {
		GlobalVars.initialize();
		GlobalVars.sharedIntance().setDataLoadingTime(context.getServletContext().getInitParameter("dataLoadingTime"));
		GlobalVars.sharedIntance().setDataFileLocation(context.getServletContext().getInitParameter("dataFileLocation"));
		GlobalVars.sharedIntance().setLog4jPropLocation(context.getServletContext().getInitParameter("log4jPropLocation"));
	}
	
	private void initTaskMgr() {
		TaskManager.initialize();
		// TaskManager.sharedInstance().submitTask(new DataLoadTask(false), DelayType.Immediately);
		DataLoadTask.scheduleImmediately();
	}
	
	private void initLog(ServletContextEvent context) {
		String logConfigPath = context.getServletContext().getRealPath("/") + GlobalVars.sharedIntance().getLog4jPropLocation();
		PropertyConfigurator.configure(logConfigPath); 
	}
	
	private void init(ServletContextEvent context) {
		initGlobalVariables(context);
		initLog(context);
		initTaskMgr();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		init(arg0);
	}
	
	/**
	private String dateToString(Date date) {  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        return format.format(date);
    }  
    */

}
