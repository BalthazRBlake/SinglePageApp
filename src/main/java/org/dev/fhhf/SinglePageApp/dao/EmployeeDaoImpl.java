package org.dev.fhhf.SinglePageApp.dao;

import org.dev.fhhf.SinglePageApp.model.Department;
import org.dev.fhhf.SinglePageApp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
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
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int countTotalEmployees() {
        String sql = "SELECT COUNT(*) FROM tbl_employees";
        return namedParameterJdbcTemplate.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public List<Employee> findAllEmployees() {
        String sql = "SELECT * FROM tbl_employees "
                   + "LEFT JOIN tbl_departments "
                   + "ON emp_dpid = dp_id "
                   + "ORDER BY emp_id ";
        return namedParameterJdbcTemplate.query(sql, new EmployeeMapper());
    }

    @Override
    public List<Employee> findAllEmployeesPaginated(int page, int size) {
        String sql = "SELECT * FROM tbl_employees "
                   + "LEFT JOIN tbl_departments "
                   + "ON emp_dpid = dp_id "
                   + "ORDER BY emp_id "
                   + "OFFSET :page LIMIT :size";
        SqlParameterSource namedParams = new MapSqlParameterSource("page", (page-1)*size)
                                                        .addValue("size", size);
        return namedParameterJdbcTemplate.query(sql, namedParams, new EmployeeMapper());
    }

    @Override
    public List<Employee> findEmployeesNameStartsWith(String empName, int page, int size) {

        String sql = "SELECT * FROM tbl_employees "
                   + "LEFT JOIN tbl_departments "
                   + "ON emp_dpid = dp_id "
                   + "WHERE emp_name ILIKE :empName "
                   + "ORDER BY emp_id ";
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
                   + " WHERE emp_id = :empId";
        SqlParameterSource namedParams = new MapSqlParameterSource("empId", empId);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParams, new EmployeeMapper());
    }

    @Override
    public int insertEmployee(Employee employee) {
        String sql = "INSERT INTO tbl_employees (emp_id, emp_name, emp_active, emp_dpid) "
                   + "VALUES (DEFAULT, :empName, :empActive, :emp_dpId)";
        SqlParameterSource namedParams = new MapSqlParameterSource("empName", employee.getEmpName())
                                                .addValue("empActive", employee.getEmpActive())
                                                .addValue("emp_dpId",employee.getEmp_dpId().getDpId());
        return namedParameterJdbcTemplate.update(sql, namedParams);
    }

    @Override
    public int updateEmployee(Employee employee) {
        String sql = "UPDATE tbl_employees "
                   + "SET emp_name = :empName, emp_active = :empActive, emp_dpid = :emp_dpId "
                   + "WHERE emp_id = :empId";
        SqlParameterSource namedParams = new MapSqlParameterSource("empId", employee.getEmpId())
                                            .addValue("empName", employee.getEmpName())
                                            .addValue("empActive", employee.getEmpActive())
                                            .addValue("emp_dpId",employee.getEmp_dpId().getDpId());
        return namedParameterJdbcTemplate.update(sql, namedParams);
    }

    @Override
    public int deleteEmployee(int empId) {
        String sql = "DELETE FROM tbl_employees "
                   + "WHERE emp_id = :empId";
        SqlParameterSource namedParams = new MapSqlParameterSource("empId", empId);
        return namedParameterJdbcTemplate.update(sql, namedParams);
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
