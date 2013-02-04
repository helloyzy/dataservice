package com.perficient.dataservice.utils;

import java.io.File;

public class TestUtils {

	static String getTestResDir() {
		return String.format("src%stest%sresources%s", File.separator, File.separator, File.separator);
	}
	
}
