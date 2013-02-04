package com.perficient.dataservice.utils;

import java.util.Timer;

import com.perficient.dataservice.tasks.DataLoadTask;

public class TaskManager {
	
	private Timer taskTimer = null;
	
	private static TaskManager sharedIntance;
	
	private TaskManager() {
		taskTimer = new Timer("Data Loading", true);
	}
	
	public void cancelAllPendingTasks() {
		taskTimer.cancel();
	}
	
	public void submitDataLoadingTask(long delay) {
		taskTimer.schedule(new DataLoadTask(), delay);
	}
	
	public static void initialize() {
		sharedIntance = new TaskManager();
	}
	
	public TaskManager sharedInstance() {
		return sharedIntance;
	}
	
	public static void destroy() {
		if (sharedIntance != null) {
			sharedIntance.cancelAllPendingTasks();
		}
	} 

}
