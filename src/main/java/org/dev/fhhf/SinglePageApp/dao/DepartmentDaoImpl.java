package org.dev.fhhf.SinglePageApp.dao;

import org.dev.fhhf.SinglePageApp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String findDepartmentNameById(int dpId) {

        String sql = "SELECT dp_name FROM tbl_departments WHERE dp_id = ?";

        String dpName = jdbcTemplate.queryForObject(sql, new Object[]{dpId}, String.class);

        System.out.println("dpId: " + dpId + " name: " + dpName);
        return dpName;
    }

    /*private static final class DepartmentMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();
            department.setDpIp(rs.getInt("dp_id"));
            department.setDpName(rs.getString("dp_name"));
            return department;
        }
    }*/
}
