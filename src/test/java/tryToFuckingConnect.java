import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.omg.Messaging.SyncScopeHelper;

public class tryToFuckingConnect {

	public static void main(String[] args) {
		Connection sDbConnection;
		LalaConnection lala = new LalaConnection();
		sDbConnection = lala.getConnection();
		try {
			ResultSet set = sDbConnection.createStatement().executeQuery("SELECT * FROM direccion");
			while(set.next()) {
				System.out.println(set.getString(1) + " " + set.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
