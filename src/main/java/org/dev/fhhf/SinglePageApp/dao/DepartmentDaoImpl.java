package org.dev.fhhf.SinglePageApp.dao;

import org.dev.fhhf.SinglePageApp.model.Department;
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
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Department> findAllDepartments() {
        String sql = "SELECT * FROM tbl_departments ORDER BY dp_name";
        return namedParameterJdbcTemplate.query(sql, new DepartmentMapper());
    }

    @Override
    public Department findDepartmentById(int dpId) {
        String sql = "SELECT * FROM tbl_departments "
                + " WHERE dp_id = :dpId";
        SqlParameterSource namedParams = new MapSqlParameterSource("dpId", dpId);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParams, new DepartmentMapper());
    }

    @Override
    public int insertDepartment(Department department) {
        String sql = "INSERT INTO tbl_departments (dp_id, dp_name) "
                + "VALUES (DEFAULT, :dpName)";
        SqlParameterSource namedParams = new MapSqlParameterSource("dpName", department.getDpName());
        return namedParameterJdbcTemplate.update(sql, namedParams);
    }

    @Override
    public int updateDepartment(Department department) {
        String sql = "UPDATE tbl_departments "
                + "SET dp_name = :dpName "
                + "WHERE dp_id = :dpId";
        SqlParameterSource namedParams = new MapSqlParameterSource("dpId", department.getDpId())
                                                    .addValue("dpName", department.getDpName());
        return namedParameterJdbcTemplate.update(sql, namedParams);
    }

    @Override
    public int deleteDepartment(int dpId) {
        String sql = "DELETE FROM tbl_departments "
                + "WHERE dp_id = :dpId";
        SqlParameterSource namedParams = new MapSqlParameterSource("dpId", dpId);
        return namedParameterJdbcTemplate.update(sql, namedParams);
    }

    private static final class DepartmentMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();
            department.setDpId(rs.getInt("dp_id"));
            department.setDpName(rs.getString("dp_name"));
            return department;
        }
    }
}
