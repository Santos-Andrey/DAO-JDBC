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
import db.DbException;
import model.dao.SellerDAO;
import model.entities.Departament;
import model.entities.Seller;
//PACOTE DE IMPLEMENTAÇÃO DO DAO
public class SellerDaoJDBC implements SellerDAO{
	
	private Connection conn;
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
	}

	@Override
	public void update(Seller obj) {

	}

	@Override
	public void deleteById(Integer id) {
	
	}

	@Override
	public Seller findById(Integer id) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement(
						"SELECT seller.*,departament.Name as DepName "
						+ "FROM seller INNER JOIN departament "
						+ "ON seller.DepartamentId = departament.Id "
						+ "WHERE seller.Id = ? ");
				
				st.setInt(1, id);
				rs = st.executeQuery();
				
				if(rs.next()) {
					Departament dep = instantiateDepartament(rs);
					Seller sel = instantiateSeller(rs, dep);
					
					return sel;
					
				}
				return null;
				}
				catch(SQLException e) {
					throw new DbException(e.getMessage());
				}
				finally {
					DB.closeStatement(st);
					DB.closeResultSet(rs);
				}
				
			}
		

	private Seller instantiateSeller(ResultSet rs, Departament dep) throws SQLException {
		Seller sel = new Seller();
		sel.setId(rs.getInt("Id"));
		sel.setName(rs.getString("Name"));
		sel.setEmail(rs.getString("Email"));
		sel.setBaseSalary(rs.getDouble("baseSalary"));
		sel.setDate(rs.getDate("birthDate"));
		sel.setDepartament(dep);
		return sel;
	}

	private Departament instantiateDepartament(ResultSet rs) throws SQLException {
		Departament dep = new Departament();
		dep.setId(rs.getInt("DepartamentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,departament.Name as DepName "
					+ "FROM seller INNER JOIN departament "
					+ "ON seller.DepartamentId = departament.Id "
					+ "ORDER BY Name ");
			
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Departament> map = new HashMap<>();
			
			while(rs.next()) {
				Departament dep = map.get(rs.getInt("DepartamentId"));
				
				if(dep == null) {
					dep = instantiateDepartament(rs);
					map.put(rs.getInt("DepartamentId"), dep);
				}
				
				Seller sel = instantiateSeller(rs, dep);
				list.add(sel);
				
				return list;
				
			}
			return null;
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
		
		
	}

	public List<Seller> findByDepartament(Departament departament) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,departament.Name as DepName "
					+ "FROM seller INNER JOIN departament "
					+ "ON seller.DepartamentId = departament.Id "
					+ "WHERE seller.DepartamentId = ? "
					+ "ORDER BY Name ");
			
			st.setInt(1, departament.getId());
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Departament> map = new HashMap<>();
			
			while(rs.next()) {
				Departament dep = map.get(rs.getInt("DepartamentId"));
				
				if(dep == null) {
					dep = instantiateDepartament(rs);
					map.put(rs.getInt("DepartamentId"), dep);
				}
				
				Seller sel = instantiateSeller(rs, dep);
				list.add(sel);
				
				return list;
				
			}
			return null;
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
	}

	
	
	
}
