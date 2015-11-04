import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.itba.it.paw.DBManager;

public class TryToConnect {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		dbConnection = db.getConnection();
		
		//System.out.println(DATE.getCurrentDate());
		try {
			
				/*  EJEMPLO DE INSERT DATE (el que no tiene tiempo)
				String input = "19/12/1992" ;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ;
				LocalDate localDate = LocalDate.parse( input, formatter ) ;
				System.out.println(localDate);
				java.sql.Date sqlDate = java.sql.Date.valueOf( localDate );
				PreparedStatement pstmt = dbConnection.prepareStatement("INSERT INTO usuario (userid, nombre, apellido, nacimiento, dirid) values (?,?,?,?,?)");
				pstmt.setInt(1, 2);
				pstmt.setString(2, "María Eugenia");
				pstmt.setString(3, "Sakuda");
				pstmt.setDate(4, sqlDate);
				pstmt.setInt(5, 7);
				pstmt.executeUpdate();*/
			
			/*
			PreparedStatement pstmt = dbConnection.prepareStatement("INSERT INTO restaurante (gerid, dirid, nombre, descripcion, desde, hasta, montomin, regis) values (?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, 2);
			pstmt.setInt(2, 1);
			pstmt.setString(3, "Taco Box");
			pstmt.setString(4, "Taco Box es una Restaurant que inaugura un modo diferente de disfrutar la comida Mexicana. Con un estilo refinado Taco box opta por una ambientacion que genera la atmósfera de un delicado bistró.");
			pstmt.setFloat(5, (float) 20.0);
			pstmt.setFloat(6, (float) 24.0);
			pstmt.setFloat(7, (float) 160);
			pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			
			pstmt.executeUpdate();*/
			
			
			
			//dbConnection.createStatement().execute("INSERT INTO tipos (restid, tipo) values (4,'mexicana')");
			//dbConnection.createStatement().execute("INSERT INTO tipos (restid, tipo) values (1,'norteamericana')");
			
			//dbConnection.createStatement().execute("DROP TABLE direccion;");
			//dbConnection.createStatement().execute("ALTER TABLE pedido ADD COLUMN estado INTEGER;");
			//dbConnection.createStatement().execute("UPDATE credencial SET rol='admin' WHERE mail='mvictoria.cavo@gmail.com';");
			//dbConnection.createStatement().execute("ALTER TABLE pedido ALTER COLUMN estado SET NOT NULL;");
			//dbConnection.createStatement().execute("INSERT INTO direccion (calle, provincia, localidad, numero, barrio) values ('Av. Olazábal', 'Buenos Aires', 'Capital Federal', 5151,'Villa Urquiza');");				
			//dbConnection.createStatement().execute("DELETE FROM direccion WHERE barrio='hibernate neigh';");
			//dbConnection.createStatement().execute("INSERT INTO usuario values (1,'María Victoria', 'Cavo', 6, );");
			/**/
			//dbConnection.createStatement().execute("UPDATE direccion SET barrio='Flores' WHERE barrio='Buenos'");
			//dbConnection.createStatement().execute("UPDATE direccion SET barrio='Puerto Madero' WHERE barrio='mares'");
			//dbConnection.createStatement().execute("DELETE FROM direccion WHERE barrio='No' OR barrio='SDFASDF' OR barrio='dasda'");
			//dbConnection.createStatement().execute("INSERT INTO barrio(nombre) values ('Caballito')");
			//dbConnection.createStatement().execute("INSERT INTO barrio(nombre) values ('Floresta')");
			//dbConnection.createStatement().execute("INSERT INTO barrio(nombre) values ('Puerto Madero')");
			//dbConnection.createStatement().execute("INSERT INTO barrio(nombre) values ('Palermo')");
			//dbConnection.createStatement().execute("INSERT INTO barrio(nombre) values ('Almagro')");
			//dbConnection.createStatement().execute("INSERT INTO barrio(nombre) values ('San Cristobal')");
			//dbConnection.createStatement().execute("INSERT INTO barrio(nombre) values ('Villa Urquiza')");
			//dbConnection.createStatement().execute("INSERT INTO barrio(nombre) values ('Balvanera')");
			//dbConnection.createStatement().execute("INSERT INTO barrio(nombre) values ('Nordelta')");
			//dbConnection.createStatement().execute("INSERT INTO barrio(nombre) values ('Belgrano')");
			//dbConnection.createStatement().execute("INSERT INTO barrio(nombre) values ('Flores')");
			//dbConnection.createStatement().execute("ALTER TABLE ");

			//ResultSet set = dbConnection.createStatement().executeQuery("SELECT * FROM pedido;");
			//dbConnection.createStatement().execute("DELETE FROM credencial WHERE id=42;");
			
			//ResultSet set = dbConnection.createStatement().executeQuery("SELECT * FROM pedido where restid = 1");
			ResultSet set = dbConnection.createStatement().executeQuery("SELECT * FROM information_schema.columns WHERE table_name  = 'delivery'");
			//dbConnection.createStatement().execute("ALTER TABLE restaurante ALTER COLUMN descripcion DROP NOT NULL;");
			//dbConnection.createStatement().execute("DELETE FROM plato WHERE id=3;");
			//dbConnection.createStatement().execute("ALTER TABLE restaurante ALTER COLUMN regis SET DEFAULT CURRENT_TIMESTAMP;");
			//ResultSet set = dbConnection.createStatement().executeQuery("SELECT * FROM restaurante");
			//System.out.println("DIRECCION");
			System.out.println("---------");
			while(set.next()) {
				for(int i=1 ; i<=5; i++)
					System.out.print(set.getString(i)+" | ");
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
