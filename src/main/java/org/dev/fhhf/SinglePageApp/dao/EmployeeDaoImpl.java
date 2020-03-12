package org.dev.fhhf.SinglePageApp.dao;

import org.dev.fhhf.SinglePageApp.model.Department;
import org.dev.fhhf.SinglePageApp.model.Employee;
import org.dev.fhhf.SinglePageApp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
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
    public List<Employee> findAllEmployeesPaginated(int page, int size) {
        String sql = "SELECT * FROM tbl_employees "
                   + "LEFT JOIN tbl_departments "
                   + "ON emp_dpid = dp_id "
                   + "OFFSET ? LIMIT ?";
                   //+ "WHERE emp_id >= 3 ORDER BY emp_id ASC LIMIT 2";
        return jdbcTemplate.query(sql, new Object[]{ (page-1)*size, size }, new EmployeeMapper());
    }

    @Override
    public List<Employee> findEmployeesNameStartsWith(String empName, int page, int size) {

        String sql = "SELECT * FROM tbl_employees "
                   + "LEFT JOIN tbl_departments "
                   + "ON emp_dpid = dp_id "
                   + "WHERE emp_name ILIKE :empName";
                   //+ "OFFSET :page LIMIT :size";
        SqlParameterSource params = new MapSqlParameterSource("empName", empName.concat("%"));
        //.addValue("page", (page-1)*size).addValue("size", size);
        return namedParameterJdbcTemplate.query(sql, params, new EmployeeMapper());
    }

    @Override
    public Employee findEmployeeById(int empId) {
        String sql = "SELECT * FROM tbl_employees "
                   + "LEFT JOIN tbl_departments "
                   + "ON emp_dpid = dp_id "
                   + " WHERE emp_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{empId}, new EmployeeMapper());
    }

    @Override
    public int insertEmployee(Employee employee) {
        String sql = "INSERT INTO tbl_employees (emp_id, emp_name, emp_active, emp_dpid) VALUES (DEFAULT, ?, ?, ?)";

        return jdbcTemplate.update(sql, new Object[]{
                employee.getEmpName(),
                employee.getEmpActive(),
                employee.getEmp_dpId().getDpId()});
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        return null;
    }

    private static final class EmployeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

            Employee employee = new Employee();
            Department department = new Department();

            employee.setEmpId(rs.getInt("emp_id"));
            employee.setEmpName(rs.getString("emp_name"));
            employee.setEmpActive(rs.getBoolean("emp_active"));
            department.setDpId( rs.getInt("dp_id") );
            department.setDpName(rs.getString("dp_name"));
            employee.setEmp_dpId(department);

            return employee;
        }
    }
}
