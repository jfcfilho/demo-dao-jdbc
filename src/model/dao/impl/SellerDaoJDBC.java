package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DBException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDAO {
	
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
				Department dep = instanciateDepartment(rs);
				
				seller = instanciateSeller(rs, dep);

				return seller;
			}
			return null;
			
		} catch (Throwable t) {
			t.printStackTrace();
			throw new DBException(t.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}


	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name";

			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
					
				if(dep == null) {
					dep = instanciateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				list.add(instanciateSeller(rs, dep));

			}
			return list;
			
		} catch (Throwable t) {
			t.printStackTrace();
			throw new DBException(t.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
	}
	
	private Seller instanciateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getBigDecimal("BaseSalary"));
		seller.setDepartment(dep);
		return seller;
	}

	private Department instanciateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name";

			st = conn.prepareStatement(sql);
			st.setInt(1, department.getId());
			
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
					
				if(dep == null) {
					dep = instanciateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				list.add(instanciateSeller(rs, dep));

			}
			return list;
		} catch (Throwable t) {
			t.printStackTrace();
			throw new DBException(t.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
	}
}