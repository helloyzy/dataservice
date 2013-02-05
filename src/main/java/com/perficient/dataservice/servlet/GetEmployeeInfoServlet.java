package com.perficient.dataservice.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.perficient.dataservice.model.Employee;
import com.perficient.dataservice.utils.EmpMgr;
import com.perficient.dataservice.utils.FileUtils;


public class GetEmployeeInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4118653597171891505L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		/**
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
		*/
		response.setStatus(200);
		response.setContentType("text/html");
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<Employee> empList = EmpMgr.sharedInstance().getEmployees();
		Employee[] employees = empList.toArray(new Employee[0]);
		String responseJson = new Gson().toJson(employees);
		// System.out.println(new);
		out.println(responseJson);
		out.flush();
		Employee[] newEmps = new Gson().fromJson(responseJson, Employee[].class);
		System.out.println(newEmps[1].getRole());
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// request.getContextPath();
		this.doPost(request, response);
	}

}
