package com.cg.eev.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.cg.eev.bean.EmployeeBean;
import com.cg.eev.exception.EmployeeExcpetion;
import com.cg.eev.util.DBUtil;

public class EmployeeDaoImpl implements IEmployeeDao {
	Connection con = null;

	public EmployeeDaoImpl() {
		try {
			con = DBUtil.getConnect();
		} catch (EmployeeExcpetion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int addEmployee(EmployeeBean employeeBean) throws EmployeeExcpetion {
		int empId = 0;
		try {
			empId = getEmpId();
			employeeBean.setEmpId(empId);
			String sql = "INSERT INTO vik_employee VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empId);
			pstmt.setString(2, employeeBean.getName());
			pstmt.setInt(3, employeeBean.getSalary());
			pstmt.setString(4, employeeBean.getDesignation());
			pstmt.setString(5, employeeBean.getInsuranceScheme());

			int row = pstmt.executeUpdate();
			if (row > 0) {
				// new row entry
			} else {

				throw new EmployeeExcpetion("System Error. Try Again Later.");

			}
		} catch (SQLException e) {
		}

		return empId;
	}

	private int getEmpId() {
		// TODO Auto-generated method stub
		int empId = 0;
		String sql = "SELECT empId_Sequence.NEXTVAL FROM DUAL";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				empId = res.getInt(1);
			}
		} catch (SQLException e) {

		}
		return empId;
	}

	@Override
	public List<EmployeeBean> getEmployeeDetails(String scheme) throws EmployeeExcpetion {
		List<EmployeeBean> myList = new ArrayList<>();
		try {

			String sql = "SELECT * FROM vik_employee WHERE insurancescheme = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, scheme);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				EmployeeBean bean = new EmployeeBean();
				bean.setEmpId(res.getInt(1));
				bean.setName(res.getString(2));
				bean.setSalary(res.getInt(3));
				bean.setDesignation(res.getString(4));
				bean.setInsuranceScheme(res.getString(5));
				myList.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeExcpetion(e.getMessage());
		}

		return myList;
	}

	@Override
	public void deleteEmployee(int empId) throws EmployeeExcpetion {
		try {

			String sql = "DELETE FROM vik_employee WHERE ID = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeExcpetion(e.getMessage());
		}
	}

	@Override
	public EmployeeBean getEmployeeDetailsByID(int empId) throws EmployeeExcpetion {
		EmployeeBean bean = null;

		try {
			String sql = "SELECT * FROM vik_employee WHERE ID = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empId);
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				bean = new EmployeeBean();
				bean.setEmpId(res.getInt(1));
				bean.setName(res.getString(2));
				bean.setSalary(res.getInt(3));
				bean.setDesignation(res.getString(4));
				bean.setInsuranceScheme(res.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeExcpetion(e.getMessage());
		}
		return bean;

	}

	@Override
	public List<EmployeeBean> getSortedEmployeeDetails() throws EmployeeExcpetion {
		List<EmployeeBean> myList = new ArrayList<>();
		try {

			String sql = "SELECT * FROM vik_employee order by SLARAY";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				EmployeeBean bean = new EmployeeBean();
				bean.setEmpId(res.getInt(1));
				bean.setName(res.getString(2));
				bean.setSalary(res.getInt(3));
				bean.setDesignation(res.getString(4));
				bean.setInsuranceScheme(res.getString(5));
				myList.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeExcpetion(e.getMessage());
		}

		return myList;
	}
}