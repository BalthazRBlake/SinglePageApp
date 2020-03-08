package org.dev.fhhf.SinglePageApp.dao;

import org.dev.fhhf.SinglePageApp.model.Department;
import org.dev.fhhf.SinglePageApp.model.Employee;
import org.dev.fhhf.SinglePageApp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public int countTotalEmployees() {
        String sql = "SELECT COUNT(*) FROM tbl_employees";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<Employee> findAllEmployees() {
        String sql = "SELECT * FROM tbl_employees "
                   + "LEFT JOIN tbl_departments "
                   + "ON emp_dpid = dp_id";

        return jdbcTemplate.query(sql, new EmployeeMapper());
    }

    @Override
    public List<Employee> findEmployeesNameStartsWith(String empName) {
        String sql = "SELECT * FROM tbl_employees "
                   + "LEFT JOIN tbl_departments "
                   + "ON emp_dpid = dp_id "
                   + "WHERE emp_name ILIKE '"+empName+"%'";

        return jdbcTemplate.query(sql, new EmployeeMapper());
    }

    @Override
    public Employee findEmployeeById(int empId) {
        String sql = "SELECT * FROM tbl_employees "
                   + "LEFT JOIN tbl_departments "
                   + "ON emp_dpid = dp_id "
                   + " WHERE emp_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{empId}, new EmployeeMapper());
    }

    private static final class EmployeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

            Employee employee = new Employee();
            Department department = new Department();

            employee.setEmpId(rs.getInt("emp_id"));
            employee.setEmpName(rs.getString("emp_name"));
            employee.setEmpActive(rs.getBoolean("emp_active"));
            department.setDpIp( rs.getInt("dp_id") );
            department.setDpName(rs.getString("dp_name"));
            employee.setEmp_dpId(department);

            return employee;
        }
    }
}
