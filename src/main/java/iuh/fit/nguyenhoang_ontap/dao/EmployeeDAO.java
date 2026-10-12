package iuh.fit.nguyenhoang_ontap.dao;

import iuh.fit.nguyenhoang_ontap.model.Department;
import iuh.fit.nguyenhoang_ontap.model.Employee;
import iuh.fit.nguyenhoang_ontap.model.Poitsion;
import iuh.fit.nguyenhoang_ontap.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private DBUtil dbUtil;

    public EmployeeDAO() {
        // Use DBUtil with null DataSource to fall back to DriverManager
        this.dbUtil = new DBUtil(null);
    }

    public List<Employee> getAllEmp() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e.id AS emp_id, e.name AS emp_name, e.role, e.salary, "
                + "d.id AS dept_id, d.name AS dept_name, p.id AS pos_id, p.title AS pos_title "
                + "FROM Employees e "
                + "JOIN Departments d ON e.department_id = d.id "
                + "JOIN positions p ON e.position_id = p.id";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()){
                int id = rs.getInt("emp_id");
                String name = rs.getString("emp_name");
                String role = rs.getString("role");
                double salary = rs.getDouble("salary");

                int department_id = rs.getInt("dept_id");


                int positon_id = rs.getInt("pos_id");


                Employee employee = new Employee(id, name, role, salary, department_id, positon_id);
                list.add(employee);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while loading employees from DB: " + e.getMessage(), e);
        }
        return list;
    }

    public Employee getById(int id) throws SQLException {
        String sql = "SELECT e.id AS emp_id, e.name AS emp_name, e.role, e.salary, "
                + "d.id AS dept_id, d.name AS dept_name, p.id AS pos_id, p.title AS pos_title "
                + "FROM Employees e "
                + "JOIN Departments d ON e.department_id = d.id "
                + "JOIN positions p ON e.position_id = p.id "
                + "WHERE e.id = ?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int employeeId = rs.getInt("emp_id");
                    String name = rs.getString("emp_name");
                    String role = rs.getString("role");
                    double salary = rs.getDouble("salary");

                    int departmentId = rs.getInt("dept_id");


                    int positionId = rs.getInt("pos_id");


                    return new Employee(employeeId, name, role, salary, departmentId, positionId);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while searching employee by id: " + e.getMessage(), e);
        }

        return null;
    }

    public List<Employee> searchByName(String name) throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e.id AS emp_id, e.name AS emp_name, e.role, e.salary, "
                + "d.id AS dept_id, d.name AS dept_name, p.id AS pos_id, p.title AS pos_title "
                + "FROM Employees e "
                + "JOIN Departments d ON e.department_id = d.id "
                + "JOIN positions p ON e.position_id = p.id "
                + "WHERE LOWER(e.name) LIKE LOWER(?)";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int employeeId = rs.getInt("emp_id");
                    String employeeName = rs.getString("emp_name");
                    String role = rs.getString("role");
                    double salary = rs.getDouble("salary");

                    int departmentId = rs.getInt("dept_id");


                    int positionId = rs.getInt("pos_id");


                    list.add(new Employee(employeeId, employeeName, role, salary, departmentId, positionId));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while searching employees by name: " + e.getMessage(), e);
        }

        return list;
    }

    public boolean updateEmp(Employee employee){
        String sql = "UPDATE Employees SET name = ?, role = ?, salary = ?, department_id = ?, position_id = ? WHERE id = ?";

        try(Connection conn = dbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getRole());
            statement.setDouble(3, employee.getSalary());
            statement.setInt(4, employee.getDepartment());
            statement.setInt(5, employee.getPoitsion());
            statement.setInt(6, employee.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertEmp(Employee employee){
        String sql = "INSERT INTO Employees (name, role, salary, department_id, position_id) VALUES (?,?,?,?,?)";
        try(Connection conn = dbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getRole());
            statement.setDouble(3, employee.getSalary());
            statement.setInt(4, employee.getDepartment());
            statement.setInt(5, employee.getPoitsion());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
