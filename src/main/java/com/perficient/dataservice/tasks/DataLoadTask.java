package com.perficient.dataservice.tasks;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.perficient.dataservice.model.Employee;
import com.perficient.dataservice.utils.EmpImportResult;
import com.perficient.dataservice.utils.EmpImportResult.StatusCode;
import com.perficient.dataservice.utils.EmpImporter;
import com.perficient.dataservice.utils.EmpMgr;
import com.perficient.dataservice.utils.FileUtils;
import com.perficient.dataservice.utils.GlobalVars;
import com.perficient.dataservice.utils.TaskManager;
import com.perficient.dataservice.utils.TaskManager.DelayType;

public class DataLoadTask extends TimerTask {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	// indicates whether this is a weekly running routine or just a task ran when the web app is first started
	private boolean isRoutine; 
	
	public DataLoadTask() {
		this(false);
	}
	
	public DataLoadTask(boolean isRoutine) {
		this.isRoutine = isRoutine;
	}
	
	private void scheduleForNextDay() {
		TaskManager.sharedInstance().submitTask(new DataLoadTask(true), DelayType.DelayForRoutineDataLoading);
	}
	
	private void scheduleForNextHour() {
		TaskManager.sharedInstance().submitTask(new DataLoadTask(false), DelayType.DelayAnHour);
	}
	
	public static void scheduleImmediately() {
		TaskManager.sharedInstance().submitTask(new DataLoadTask(false), DelayType.Immediately);
	}
	
	private void writeToLocal(List<Employee> employees) throws Exception {
		/**
		Employee[] empArray = employees.toArray(new Employee[0]);
		FileUtils.writeObjectToFile(GlobalVars.sharedIntance().getLocalDataStoreLocation(), empArray);
		*/
	}
	
	private Employee[] readFromLocal() throws Exception {
		String localDataStoreLocation = GlobalVars.sharedIntance().getLocalDataStoreLocation();
		File localDataStoreFile = new File(localDataStoreLocation);
		if (localDataStoreFile.exists()) {
			return (Employee[])FileUtils.readObjectFromFile(localDataStoreLocation);
		} else {
			return new Employee[0];
		}
		
	}
	
	private void sendErrorNotification() {
		
	}
	
	@Override
	public void run() {
		if (isRoutine) {
			// check for Monday, run the task only on Monday
			Calendar now = Calendar.getInstance();
			if (now.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
				scheduleForNextDay();
				return;
			}
		}
		EmpImportResult importResult = EmpImporter.employeesFromExcel(GlobalVars.sharedIntance().getDataFileLocation());
		if (importResult.getStatusCode() == StatusCode.success) {
			try {
				// TODO write to local
				writeToLocal(importResult.getEmployees());
			} catch (Exception e) {
				logger.error("Failed to write employee information to local.", e);
			}
			EmpMgr.sharedInstance().setEmployees(importResult.getEmployees());
			scheduleForNextDay();
		} else {
			// TODO if error notificatin fails? better to place it in task manager
			sendErrorNotification();
			Employee[] localCopies = null;
			try {
				localCopies = readFromLocal();
			} catch (Exception e) {
				logger.error("Failed to read employee information from local.", e);
			}
			if (localCopies != null && localCopies.length > 0) {
				EmpMgr.sharedInstance().setEmployees(Arrays.asList(localCopies));
			}
			// schedule the import process an hour later for retry
			scheduleForNextHour();
		}
	}

}
