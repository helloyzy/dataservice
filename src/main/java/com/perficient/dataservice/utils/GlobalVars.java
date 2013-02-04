package com.perficient.dataservice.utils;

import java.io.File;

public class GlobalVars {

	private static GlobalVars sharedIntance;

	private String dataLoadingTime;

	private String dataFileLocation;

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
	
	public String getLocalDataStoreLocation() {
		return String.format("data%slocalEmployees.json", File.separator);
	}

	public static GlobalVars sharedIntance() {
		return sharedIntance;
	}

}
