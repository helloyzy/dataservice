package com.perficient.dataservice.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.perficient.dataservice.utils.FileUtils;


public class GetEmployeeInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4118653597171891505L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		File srcFile = new File("\\\\chn571public39\\public\\Temp\\Whitman\\test.xlsx");
		File destFile = new File(request.getServletContext().getRealPath("/") + "WEB-INF\\1.xlsx");
		if (srcFile.isFile() && srcFile.exists()) {
			System.out.println(destFile.getAbsolutePath());
			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("No file exist!!");
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// request.getContextPath();
		this.doPost(request, response);
	}

}
