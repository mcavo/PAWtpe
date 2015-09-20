import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ar.edu.itba.it.paw.DAO.DBManager;

public class TestDBTables {

	public TestDBTables() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection dbConnection;
//		DBManager db = DBManager.getInstance();
		dbConnection = DBManager.getConnection();
		ResultSet set;
		try {
			Statement st = dbConnection.createStatement(); 
			set = st.executeQuery("SELECT * FROM usuario;");
			
			while(set.next()) {
				for(int i=1 ; i<=3; i++)
//					System.out.print("id: " + set.getInt("id") + '\n');
//					System.out.print("mail: " + set.getString("mail") + '\n');
//					System.out.print("rol: " + set.getString("rol") + '\n');
//					System.out.print("pwd: " + set.getString("psw") + '\n' + '\n');
					
//					System.out.print("id: " + set.getInt("id") + '\n');
//					System.out.print("numero: " + set.getInt("numero") + '\n');
//					System.out.print("id: " + set.getString("localidad") + '\n');
//					System.out.print("piso: " + set.getString("piso") + '\n');
//					System.out.print("depto: " + set.getString("departamento") + '\n');
//					System.out.print("street: " + set.getString("calle") + '\n');
				
					System.out.print("id: " + set.getInt("userid") + '\n');
					System.out.print("nombre: " + set.getString("nombre") + '\n');
					System.out.print("apellido: " + set.getString("apellido") + '\n');
					System.out.print("dir: " + set.getString("dirid") + '\n');
					System.out.print("nacimiento: " + set.getDate("nacimiento") + '\n' + '\n');
					
				System.out.println("");
			}
			set.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
