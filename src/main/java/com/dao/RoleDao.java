package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.RoleBean;

@Repository
public class RoleDao {

	@Autowired
	JdbcTemplate stmt;
	
	public void addRole(RoleBean roleBean) {
		
		stmt.update("insert into role values (?,?)",roleBean.getRoleId(),roleBean.getRoleName());
	}

	
	public List<RoleBean> listRoles() {
		return stmt.query("select * from role", new RowMapper<RoleBean>() {

			@Override
			public RoleBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				RoleBean roleBean = new RoleBean();
				roleBean.setRoleId(rs.getInt("id"));
				roleBean.setRoleName(rs.getString("name"));
				return roleBean;
			}
			
		});
	}


	public void deleteRole(int id) {
		stmt.update("delete from role where id = (?)",id);
	}


	public void updateRole(int id,RoleBean roleBean) {
		stmt.update("update role set name = ? where id = ?",roleBean.getRoleName(),id);
		
	}
	
	

}
