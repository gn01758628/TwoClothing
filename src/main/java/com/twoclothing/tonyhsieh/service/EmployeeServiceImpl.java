package com.twoclothing.tonyhsieh.service;


import java.util.List;


import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.employee.EmployeeDAO;
import com.twoclothing.model.employee.EmployeeHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	
	public EmployeeServiceImpl() {
		employeeDAO = new EmployeeHibernateDAO(HibernateUtil.getSessionFactory());
		
	}

	
	@Override
	public int delete(Integer empId) {
		// TODO Auto-generated method stub
		Employee emp = employeeDAO.getByPrimaryKey(empId);
		if (emp != null) {
			employeeDAO.delete(empId);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}


	@Override
	public Employee getByPrimaryKey(Integer empId) {
		// TODO Auto-generated method stub
		return employeeDAO.getByPrimaryKey(empId);
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeDAO.getAll() ;
	}


	public Employee insert(Integer deptId,String empName,String phone,String address,String email,String pswdHash, Integer empStatus,byte[] avatar) {
		// TODO Auto-generated method stub
		
		Employee employee = new Employee();
		employee.setDeptId(deptId);
		employee.setEmpName(empName);
		employee.setPhone(phone);
		employee.setAddress(address);
		employee.setEmail(email);
		employee.setPswdHash(pswdHash);
		employee.setEmpStatus(empStatus);
		employee.setAvatar(avatar);
		employeeDAO.insert(employee);
	
		return employee;
	
	}
	
	public	Employee update(Integer empId,Integer deptId,String empName,String phone,String address,String email,String pswdHash, Integer empStatus,byte[] avatar) {
		// TODO Auto-generated method stub
		Employee employee = new Employee();	
		employee.setEmpId(empId);
		employee.setDeptId(deptId);
		employee.setEmpName(empName);
		employee.setPhone(phone);
		employee.setAddress(address);
		employee.setEmail(email);
		employee.setPswdHash(pswdHash);
		employee.setEmpStatus(empStatus);
		employee.setAvatar(avatar);
		employeeDAO.update(employee);
		return employee;
	}


	

	

	
	
   
   
}
