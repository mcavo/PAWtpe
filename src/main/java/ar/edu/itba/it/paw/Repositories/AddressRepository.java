package ar.edu.itba.it.paw.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.itba.it.paw.DAO.DBManager;
import ar.edu.itba.it.paw.models.Address;

public class AddressRepository extends AbstractHibernateRepository{

	@Autowired
	public AddressRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public Address getAddressById(int addressId) {
		String query = "SELECT * FROM direccion WHERE id = ?";
		DBManager.getInstance();
		Connection con = DBManager.getInstance().getConnection();
		Address address = null;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, addressId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String street = rs.getString("calle");
				String city = rs.getString("localidad");
				String province = rs.getString("provincia");
				int floor = rs.getInt("piso");
				int number = rs.getInt("numero");
				String apartment = rs.getString("departamento");
				String neighborhood = rs.getString("barrio");
				address = new Address(street, number, floor, apartment,neighborhood, city, province);
				address.setApartment(apartment);
				address.setFloor(floor); //TODO:checkear que pasa si el piso es null ?? 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return address;
	}
	
	public int setAddress(Address address) {
		Connection con = DBManager.getInstance().getConnection();
		String query = "INSERT INTO direccion (calle,provincia, localidad, numero, piso, departamento, barrio) VALUES (?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, address.getStreet());
			pstmt.setString(2, address.getProvince());
			pstmt.setString(3, address.getCity());
			pstmt.setInt(4, address.getNumber());
			if (address.getFloor() != -1) { 
				pstmt.setInt(5, address.getFloor());
			} else {
				pstmt.setNull(5, java.sql.Types.INTEGER);
			}
			if (address.getApartment() != null && !address.getApartment().isEmpty()) { 
				pstmt.setString(6, address.getApartment()); 
			} else {
				pstmt.setNull(6, java.sql.Types.VARCHAR);
			}
			pstmt.setString(7, address.getNeighborhood());
			
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO: si falla hay que canselar todo y tirar el error correspondiente
			e.printStackTrace();
		}
		return this.getAddressID(address);
	}
	
	public int getAddressID(Address address) {
		// TODO: reveer esta query. No sé si es lo mejor. Necesito conseguir el id de la última tupla que agregué.
		String query = "SELECT id FROM direccion WHERE calle = ? AND localidad = ? AND provincia = ? AND numero = ? AND (piso = ? or piso is null) AND (departamento = ? or departamento is null) AND barrio = ? AND id = (SELECT max(id) FROM direccion)";
		DBManager.getInstance();
		Connection con = DBManager.getInstance().getConnection();
		
		int addressId = -1;
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, address.getStreet());
			pstmt.setString(2, address.getCity());
			pstmt.setString(3, address.getProvince());
			pstmt.setInt(4, address.getNumber());
			if (address.getFloor() != -1) { 
				pstmt.setInt(5, address.getFloor());
			} else {
				pstmt.setNull(5, java.sql.Types.INTEGER);
			}
			if (address.getApartment() != null && !address.getApartment().isEmpty()) { 
				pstmt.setString(6, address.getApartment()); 
			} else {
				pstmt.setNull(6, java.sql.Types.VARCHAR);
			}
			pstmt.setString(7, address.getNeighborhood());
			
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				addressId = rs.getInt("id");
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addressId;
	}

	public List<Integer> getIds(Address address){//String street, int number, String neighborhood, String city, String province, int floor, String apartment) {
		List<Integer> ids = new LinkedList<Integer>();
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "SELECT id FROM direccion WHERE calle like ? and numero = ? and barrio like ? and localidad like ? and provincia like ? and (piso = ? or piso is null) and (departamento like ? or departamento is null);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, address.getStreet());
			pstmt.setInt(2, address.getNumber());
			pstmt.setString(3, address.getNeighborhood());
			pstmt.setString(4, address.getCity());
			pstmt.setString(5, address.getProvince());
			pstmt.setInt(6, address.getFloor());
			pstmt.setString(7, address.getApartment());
			
			ResultSet rs = pstmt.executeQuery();
			while ( rs.next() ) {
			    ids.add(rs.getInt("id"));
			 }
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ids;
	}

	public ArrayList<Integer> getAddressesIds(Address address) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "SELECT id FROM direccion WHERE calle like ? and numero = ? and barrio like ? and localidad like ? and provincia like ? and (piso = ? or piso is null) and (departamento like ? or departamento is null);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, address.getStreet());
			pstmt.setInt(2, address.getNumber());
			pstmt.setString(3, address.getNeighborhood());
			pstmt.setString(4, address.getCity());
			pstmt.setString(5, address.getProvince());
			pstmt.setInt(6, address.getFloor());
			pstmt.setString(7, address.getApartment());
			
			ResultSet rs = pstmt.executeQuery();
			while ( rs.next() ) {
			    ans.add(Integer.valueOf(rs.getInt("id")));
			 }
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;	
	}
}
