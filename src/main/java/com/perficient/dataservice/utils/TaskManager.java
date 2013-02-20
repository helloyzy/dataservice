package com.perficient.dataservice.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.perficient.dataservice.tasks.DataLoadTask;

public class TaskManager {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public enum DelayType {
		Immediately, DelayAnHour, DelayADay, DelayUntilNextDayMorning, DelayForRoutineDataLoading
	}

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

	long timeToNextAppointedTime(Date fromTime, String appointedTime,
			int defAppointedHour, int defAppointedMin, int defAppointedSec) {
		String[] dateParts = appointedTime.split(":");
		int hour = defAppointedHour;
		int minute = defAppointedMin;
		int second = defAppointedSec;
		if (dateParts.length == 3) {
			try {
				hour = Integer.parseInt(dateParts[0]);
				minute = Integer.parseInt(dateParts[1]);
				second = Integer.parseInt(dateParts[2]);
			} catch (NumberFormatException e) {   
				logger.error("The configuration of dataLoading time is invalid:" + appointedTime, e);
			}
		}

		long fromMilliSeconds = fromTime.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fromTime);
		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		long appointedTimeInSameDay = calendar.getTimeInMillis();

		if (appointedTimeInSameDay > fromMilliSeconds) { // the appointed time
															// is later than
															// current time.
															// E.g.
															// 23:59:59(appointed
															// time) >
															// 13:30:00(now)
			return appointedTimeInSameDay - fromMilliSeconds;
		} else {
			long dayInterval = 1000L * 60 * 60 * 24; // in milliseconds
			return appointedTimeInSameDay - fromMilliSeconds + dayInterval;
		}
	}

	private long timeToNextAppointedTime(String appointedTime) {
		return timeToNextAppointedTime(new Date(), appointedTime, 23, 59, 59);
	}

	private long timeToNextMorning() {
		return timeToNextAppointedTime("23:59:59");
	}

	private long timeToNextDataLoadingRoutine() {
		return timeToNextAppointedTime(GlobalVars.sharedIntance()
				.getDataLoadingTime());
	}

	public void submitTask(TimerTask task, DelayType delayType) {
		long delay = 0L;
		switch (delayType) {
		case Immediately:
			delay = 0L;
			break;
		case DelayAnHour:
			delay = 1000L * 60 * 60;
			break;
		case DelayADay:
			delay = 1000L * 24 * 60 * 60;
			break;
		case DelayUntilNextDayMorning:
			delay = timeToNextMorning();
			break;
		case DelayForRoutineDataLoading:
			delay = timeToNextDataLoadingRoutine();
			break;
		default:
			break;
		}
		submitTask(task, delay);
	}

	public void submitTask(TimerTask task, long delay) {
		taskTimer.schedule(task, delay);
	}

	public static void initialize() {
		sharedIntance = new TaskManager();
	}

	public static TaskManager sharedInstance() {
		return sharedIntance;
	}

	public static void clear() {
		if (sharedIntance != null) {
			sharedIntance.cancelAllPendingTasks();
		}
	}

}
