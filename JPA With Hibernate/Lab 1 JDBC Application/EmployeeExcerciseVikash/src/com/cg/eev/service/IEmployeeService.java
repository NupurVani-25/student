package com.cg.eev.service;

import java.util.List;

import com.cg.eev.bean.EmployeeBean;
import com.cg.eev.exception.EmployeeExcpetion;

public interface IEmployeeService {

	public boolean validateName(String name) throws EmployeeExcpetion;
	
	public int addEmployee(EmployeeBean bean) throws EmployeeExcpetion;

	public List<EmployeeBean> getEmployeeDetails(String scheme) throws EmployeeExcpetion;

	public void deleteEmployee(int empId) throws EmployeeExcpetion;

	public EmployeeBean getEmployeeDetailsByID(int empId) throws EmployeeExcpetion;

	public List<EmployeeBean> getSortedEmployeeDetails() throws EmployeeExcpetion;
	
}
