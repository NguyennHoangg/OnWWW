package iuh.fit.nguyenhoang_ontap.controller;

import iuh.fit.nguyenhoang_ontap.dao.EmployeeDAO;
import iuh.fit.nguyenhoang_ontap.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public List<Employee> getAll() throws SQLException {
        List<Employee> listAll = employeeDAO.getAllEmp();
        return listAll;
    }

    public Employee getById(int id) throws SQLException {
        return employeeDAO.getById(id);
    }

    public List<Employee> searchByName(String name) throws SQLException {
        return employeeDAO.searchByName(name);
    }

    public boolean updateEmp(Employee emp){
        return employeeDAO.updateEmp(emp);
    }

    public boolean insertEmp(Employee employee){
        return employeeDAO.insertEmp(employee);
    }
}
