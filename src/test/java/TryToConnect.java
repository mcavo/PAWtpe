import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.omg.Messaging.SyncScopeHelper;

import ar.edu.itba.it.paw.DAO.DBManager;

public class TryToConnect {

	public static void main(String[] args) {
		Connection dbConnection;
		DBManager db = DBManager.getInstance();
		dbConnection = db.getConnection();
		try {
			
			
			//dbConnection.createStatement().execute("DROP TABLE direccion;");
			
			dbConnection.createStatement().execute("");				
			
			//dbConnection.createStatement().execute("CREATE TABLE example ();");
			//dbConnection.createStatement().execute("INSERT INTO axample (atr1, atr2, atr3) values (val1,val2,val3);");
			ResultSet set = dbConnection.createStatement().executeQuery("SELECT * FROM direccion;");
			System.out.println(set.next());
			while(set.next()) {
				for(int i=1 ; i<=9 ; i++)
					System.out.print(set.getString(i) + " ");
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
