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
	
	protected int getAddressID(Address address) {
		// TODO: reveer esta query. No sé si es lo mejor. Necesito conseguir el id de la última tupla que agregué.
		String query = "SELECT id FROM direccion WHERE calle = ? AND localidad = ? AND provincia = ? AND numero = ? AND (piso = ? or piso is null) AND (departamento = ? or departamento is null) AND barrio = ? AND id = (SELECT max(id) FROM direccion)";
		DBManager.getInstance();
		Connection con = DBManager.getInstance().getConnection();
		
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
	
	/*private Address getAddressById(int id){
	Address address = null;
	try {
		Connection conn = DBManager.getInstance().getConnection();
		String sql = "SELECT * FROM direccion WHERE id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		
		ResultSet sd = pstmt.executeQuery();
		while ( sd.next() ) {
			address = new Address (sd.getString("calle"),sd.getInt("numero"), sd.getInt("piso"), sd.getString("departamento"), sd.getString("barrio"),sd.getString("localidad"),sd.getString("provincia")); 
		 }
		sd.close();
		pstmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return address;
}*/

	protected int getAddressId(Address address){//String street, int number, String neighborhood, String city, String province, int floor, String apartment) {
		int id = -1;
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
			    id  = rs.getInt("id");
			 }
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

 }
