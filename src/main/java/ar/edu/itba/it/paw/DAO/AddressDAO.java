package ar.edu.itba.it.paw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.itba.it.paw.models.Address;

public class AddressDAO {
	
	private static AddressDAO instance;
	
	public AddressDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static AddressDAO getInstance() {
		if (instance == null) {
			instance = new AddressDAO();
		}
		return instance;
	}
	
	public Address getAddress(int addressId) {
		String query = "SELECT * FROM direccion WHERE id = ?";
		DBManager.getInstance();
		Connection con = DBManager.getConnection();
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
				
				address = new Address(street, number, city, province, neighborhood);
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
		Connection con = DBManager.getConnection();
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
			if (address.getApartment() != null) { 
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
	
	private int getAddressID(Address address) {
		// TODO: reveer esta query. No sé si es lo mejor. Necesito conseguir el id de la última tupla que agregué.
		String query = "SELECT id FROM direccion WHERE calle = ? AND localidad = ? AND provincia = ? AND numero = ? AND piso = ? AND departamento = ? AND barrio = ? AND id = (SELECT max(id) FROM direccion)";
		DBManager.getInstance();
		Connection con = DBManager.getConnection();
		
		int addressId = -1;
		
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
			if (address.getApartment() != null) { 
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
	
 }
