package com.perficient.dataservice.utils;

import java.io.File;

public class GlobalVars {

	private static GlobalVars sharedIntance;

	private String dataLoadingTime;

	private String dataFileLocation;
	
	private String log4jPropLocation;

	private GlobalVars() {
	}

	public static void initialize() {
		sharedIntance = new GlobalVars();
	}

	public String getDataLoadingTime() {
		return dataLoadingTime;
	}

	public void setDataLoadingTime(String dataLoadingTime) {
		this.dataLoadingTime = dataLoadingTime;
	}

	public String getDataFileLocation() {
		return dataFileLocation;
	}

	public void setDataFileLocation(String dataFileLocation) {
		this.dataFileLocation = dataFileLocation;
	}
	
	public String getLog4jPropLocation() {
		return log4jPropLocation;
	}

	public void setLog4jPropLocation(String log4jPropLocation) {
		this.log4jPropLocation = replaceFileSeparator(log4jPropLocation);
	}
	
	private String replaceFileSeparator(String src) {
		String result = src.replace("\\", File.separator);
		result = result.replace("/", File.separator);
		return result;
	}

	public String getLocalDataStoreLocation() {
		return String.format("data%slocalEmployees.json", File.separator);
	}
	
	public static GlobalVars sharedIntance() {
		return sharedIntance;
	}

	

}
