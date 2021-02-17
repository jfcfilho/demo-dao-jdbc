package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDAO{
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller dep) {
		
	}

	@Override
	public void update(Seller dep) {
		
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		Seller seller = null;
		
		try {
			String sql = "SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?";

			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				seller = new Seller();
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setBaseSalary(rs.getBigDecimal("BaseSalary"));

				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				
				seller.setDepartment(dep);
				
				return seller;
			}
		} catch (Throwable t) {
			t.printStackTrace();
			throw new DBException(t.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public List<Seller> findAll() {
		return null;
	}
}