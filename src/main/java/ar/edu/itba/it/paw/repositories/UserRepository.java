package ar.edu.itba.it.paw.repositories;

import java.time.DateTimeException;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.exceptions.NoCredentialException;
import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.User;

@Repository
public class UserRepository extends AbstractHibernateRepository{

	private CredentialRepository credentialRepository;
	private AddressRepository addressRepository;
	
	@Autowired
	public UserRepository(SessionFactory sessionFactory, CredentialRepository credentialRepository, AddressRepository addressRepository){
		super(sessionFactory);
		this.credentialRepository = credentialRepository;
		this.addressRepository = addressRepository;
	}
	
	public User getUser(Credential cred){
		User user = getUserById(cred.getId());
		user.setManager(cred.getRol().equals("gerente"));
		user.setIsAdmin(cred.getRol().equals("admin"));
		return user;
	}
	
	public User getUserById(int id){
		return get(User.class, id);
	}

	public int getUserId(String mail) {
		//int userid = credentialDAO.getCredentialID(mail);
		int userid;
		try {
			userid = credentialRepository.getCredentialID(mail);
		} catch (NoCredentialException e1) {
			// TODO Auto-generated catch block
			userid = -1;
		}
		return get(User.class, userid).getId();
	}
	
	public User setUser(User user, String pwd) throws Exception {
		String role;
		role = user.getIsManager() ? "manager" : "usuario"; 
		int userid = -1;
		//int userid = credentialDAO.setCredentials(user.getEmail(), pwd, role); //Excpetion use to give feedback to the user if the email is still used
		Credential credential = new Credential();
		credential.setMail(user.getEmail());
		credential.setRol(role);
		credentialRepository.add(credential);
		userid = credentialRepository.getCredentialID(user.getEmail());
		user.setId(userid);
		save(user);
		
		return user;
		/*String query = "INSERT INTO usuario (userid, nombre, apellido, nacimiento, dirid ) VALUES (? , ?, ?, ?, ?);";
		DBManager.getInstance();
		Connection con = DBManager.getInstance().getConnection();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userid);
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setDate(4, Date.valueOf(user.getBirth()));
			pstmt.setInt(5, addressid); 
			
			pstmt.execute();
	        pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setId(userid);
		return user;
		*/
	}
	
	@SuppressWarnings("deprecation")
	public User signUp(String email, String pwd, String firstName, String lastName, String day, String month, String year, boolean isManager, String street, String number, String city, String province, String neighborhood, String floor, String apartment) throws DateTimeException, NumberFormatException, Exception {
		//Get day
		Date birth = null;
		short dayV = Integer.valueOf(day).shortValue();
		short monthV = Integer.valueOf(month).shortValue();
		int yearV = Integer.valueOf(year).intValue();
		birth = new Date(yearV, monthV, dayV);	
		
		//Get the value
		int numberV = Integer.valueOf(number);

		int floorV;
		try {
			floorV = Integer.valueOf(floor);
		} catch (Exception e) {
			floorV = -1;
		}
		Address address = new Address(street, numberV, floorV, apartment, neighborhood, city, province);
		address.setFloor(floorV);	
		address.setApartment(apartment);
		
		//User user = new User(email, firstName, lastName, birth, isManager, address);
		User user = createUser(email, firstName, lastName, birth);//new User(firstName, lastName, birth);
		user.setManager(isManager);
		user.setAddress(address);
		//user.setDirId(address.getId());
		return setUser(user, pwd); //TODO: esta exceptción debería ser cambiada por una más personalizada.
	}
	
	
	private User createUser(String mail, String firstName, String lastName, Date birth) {
		return new User(firstName, lastName, birth);
	}

	public static void setIfManager(User user, String rol) {
		user.setManager(rol.equals("gerente"));
		user.setIsAdmin(rol.equals("admin"));
	}
}
