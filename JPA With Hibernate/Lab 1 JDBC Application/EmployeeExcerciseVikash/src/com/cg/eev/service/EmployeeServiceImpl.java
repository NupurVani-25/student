package com.cg.eev.service;

import java.util.List;

import com.cg.eev.bean.EmployeeBean;
import com.cg.eev.dao.EmployeeDaoImpl;
import com.cg.eev.dao.IEmployeeDao;
import com.cg.eev.exception.EmployeeExcpetion;

public class EmployeeServiceImpl implements IEmployeeService {

	IEmployeeDao dao = null;

	public EmployeeServiceImpl() {
		dao = new EmployeeDaoImpl();
	}

	@Override
	public boolean validateName(String name) throws EmployeeExcpetion {
		boolean result = true;
		if (!(name.matches("[A-Za-z]{1,20}"))) {
			throw new EmployeeExcpetion("Name not Valid");
		}
		return result;
	}

	@Override
	public int addEmployee(EmployeeBean emp) throws EmployeeExcpetion {
		int esal = emp.getSalary();
		if (esal > 5000 && esal < 20000) {
			emp.setDesignation("System Associate");
			emp.setInsuranceScheme("C");
		} else if (esal >= 20000 && esal < 40000) {
			emp.setDesignation("Programmer");
			emp.setInsuranceScheme("B");
		} else if (esal > 40000) {
			emp.setDesignation("Manager");
			emp.setInsuranceScheme("A");
		} else if (esal < 5000) {
			emp.setDesignation("Clerk");
			emp.setInsuranceScheme("No Scheme");
		}
		return dao.addEmployee(emp);
	}

	@Override
	public List<EmployeeBean> getEmployeeDetails(String scheme) throws EmployeeExcpetion {
		return dao.getEmployeeDetails(scheme);
	}

	@Override
	public void deleteEmployee(int empId) throws EmployeeExcpetion {
		 dao.deleteEmployee(empId);
	}

	@Override
	public EmployeeBean getEmployeeDetailsByID(int empId) throws EmployeeExcpetion {
		// TODO Auto-generated method stub
		return dao.getEmployeeDetailsByID(empId);
	}

	@Override
	public List<EmployeeBean> getSortedEmployeeDetails() throws EmployeeExcpetion {
		// TODO Auto-generated method stub
		return dao.getSortedEmployeeDetails();
	}

}
