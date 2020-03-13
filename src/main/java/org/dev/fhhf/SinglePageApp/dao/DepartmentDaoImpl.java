package org.dev.fhhf.SinglePageApp.dao;

import org.dev.fhhf.SinglePageApp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
    public String findDepartmentNameById(int dpId) {
        String sql = "SELECT dp_name FROM tbl_departments WHERE dp_id = ?";
        String dpName = namedParameterJdbcTemplate
                        .getJdbcTemplate()
                        .queryForObject(sql, new Object[]{dpId}, String.class);
        return dpName;
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
