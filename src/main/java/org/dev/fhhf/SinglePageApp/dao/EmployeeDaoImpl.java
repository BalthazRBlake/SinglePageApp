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
    public List<EmployeeDao> findAllEmployees() {
        String sql = "SELECT * FROM tbl_employees";
        return null;
    }

    @Override
    public Employee findEmployeeById(int empId) {

        String sql = "SELECT * FROM tbl_employees WHERE emp_id = ?";
        Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{empId}, new EmployeeMapper());

        Department department = employee.getEmp_dpId();
        String dpName = departmentService.findDepartmentNameById( department.getDpIp() );
        department.setDpName(dpName);

        employee.setEmp_dpId(department);
        return employee;
    }

    private static final class EmployeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

            Employee employee = new Employee();
            Department department = new Department();

            employee.setEmpId(rs.getInt("emp_id"));
            employee.setEmpName(rs.getString("emp_name"));
            employee.setEmpActive(rs.getBoolean("emp_active"));
            department.setDpIp( rs.getInt("emp_dpid") );

            employee.setEmp_dpId(department);

            return employee;
        }
    }
}
