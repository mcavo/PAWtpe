import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.omg.Messaging.SyncScopeHelper;

import ar.edu.itba.it.paw.DAO.DBManager;

public class TryToConnect {

	public static void main(String[] args) {
		Connection dbConnection;
		DBManager db = new DBManager();
		dbConnection = db.getConnection();
		try {
			//dbConnection.createStatement().execute("CREATE TABLE example ();");
			ResultSet set = dbConnection.createStatement().executeQuery("SELECT * FROM direccion");
			while(set.next()) {
				System.out.println(set.getString(1) + " " + set.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
