package ar.edu.itba.it.paw.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.DAO.DBManager;
import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Restaurant;

@Repository
public class AddressRepository extends AbstractHibernateRepository{

	@Autowired
	public AddressRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public Address getAddressById(int addressId) {
		Address address = null;
		List<Address> results = find("FROM Address WHERE id = ?", addressId);
		if(!results.isEmpty()){
			address = results.get(0);
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
		int floor = -1;
		String aprt = "";
		if (address.getFloor() != -1) { 
			floor = address.getFloor();
		}
		if (address.getApartment() != null && !address.getApartment().isEmpty()) { 
			aprt = address.getApartment(); 
		}
		List<Address> results = find("FROM Address WHERE calle = ? AND localidad = ? AND provincia = ? AND numero = ? AND "
				+ "(piso = ? or piso is null) AND (departamento = ? or departamento is null) AND barrio = ? AND "
				+ "id = (SELECT max(id) FROM direccion", address.getStreet(), address.getCity(), address.getProvince(), 
				address.getNumber(), floor, aprt, address.getNeighborhood());
		
		if(results.isEmpty()){
			return -1;
		}

		return results.get(0).getId();
	}

	public List<Integer> getIds(Address address){//String street, int number, String neighborhood, String city, String province, int floor, String apartment) {
		List<Integer> ids = new LinkedList<Integer>();
		List<Address> results = find("FROM Address WHERE calle like ? and numero = ? and barrio like ? and localidad like ? "
				+ "and provincia like ? and (piso = ? or piso is null) and (departamento like ? or "
				+ "departamento is null)", address.getStreet(), address.getNumber(), address.getNeighborhood(), 
				address.getCity(), address.getProvince(), address.getFloor(), address.getApartment());
		
		for (Address addr : results) {
			ids.add(addr.getId());
		}	
		return ids;
	}

	public List<Integer> getAddressesIds(Address address) {
		return getIds(address);
		/*ArrayList<Integer> ans = new ArrayList<Integer>();
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
		*/
	}
	
	protected Address getByRestaurant(Restaurant rest){
		List<Address> result = find("FROM Address WHERE id = (select dirid from Restaurant where id = ?)", rest.getId());
		if(result.isEmpty()){
			return null;
		}
		return result.get(0);
	}
}
