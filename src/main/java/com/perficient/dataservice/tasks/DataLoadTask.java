package com.perficient.dataservice.tasks;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

import com.perficient.dataservice.model.Employee;
import com.perficient.dataservice.utils.EmpImportResult;
import com.perficient.dataservice.utils.EmpImportResult.StatusCode;
import com.perficient.dataservice.utils.EmpImporter;
import com.perficient.dataservice.utils.EmpMgr;
import com.perficient.dataservice.utils.FileUtils;
import com.perficient.dataservice.utils.GlobalVars;

public class DataLoadTask extends TimerTask {
	
	// indicates whether this is a weekly running routine or just a task ran when the web app is first started
	private boolean isRoutine; 
	
	public DataLoadTask() {
		this(false);
	}
	
	public DataLoadTask(boolean isRoutine) {
		this.isRoutine = isRoutine;
	}
	
	private void scheduleForNextDay() {
		
	}
	
	private void scheduleForNextHour() {
		
	}
	
	private void writeToLocal(List<Employee> employees) throws Exception {
		Employee[] empArray = employees.toArray(new Employee[0]);
		FileUtils.writeObjectToFile(GlobalVars.sharedIntance().getLocalDataStoreLocation(), empArray);
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
			// check for Monday?
		}
		EmpImportResult importResult = EmpImporter.employeesFromExcel(GlobalVars.sharedIntance().getDataFileLocation());
		if (importResult.getStatusCode() == StatusCode.success) {
			try {
				writeToLocal(importResult.getEmployees());
			} catch (Exception e) {
				e.printStackTrace();
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
				e.printStackTrace();
			}
			if (localCopies != null && localCopies.length > 0) {
				EmpMgr.sharedInstance().setEmployees(Arrays.asList(localCopies));
			}
			// schedule the import process an hour later for retry
			scheduleForNextHour();
		}
	}

}
