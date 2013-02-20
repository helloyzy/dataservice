package com.perficient.dataservice.utils;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TaskManagerTest {
	
	@BeforeClass
	public static void classSetup() {
		TaskManager.initialize();
	}
	
	@AfterClass
	public static void classTeardown() {
		TaskManager.clear();
	}

	@Test
	public void testTimeToNextAppointedTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2013, 1, 5, 11, 59, 59);
		Date date = calendar.getTime();
		assertEquals(43200000L, TaskManager.sharedInstance().timeToNextAppointedTime(date, "23:59:59", 23, 59, 59));
		assertEquals(43200000L, TaskManager.sharedInstance().timeToNextAppointedTime(date, "invalidtime", 23, 59, 59));
		assertEquals(82800000L, TaskManager.sharedInstance().timeToNextAppointedTime(date, "10:59:59", 23, 59, 59));
		
	}

}
