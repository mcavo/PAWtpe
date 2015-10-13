package ar.edu.itba.it.paw.DAO;

import java.util.HashMap;

import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;

public interface CalificationDAO {

	public void addCalification(int userId, int restId, int rate, String comment);
	
	public HashMap<Integer, Calification> getCalifications(Restaurant restaurant);
	
	public static void addCalification(int userId, int restId, int rate, String comment){

		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "insert into Calificacion (userid, restid, descripcion, puntaje) VALUES (?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, restId);
			pstmt.setString(3, comment);
			pstmt.setInt(4, rate);
			
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Calification> getCalificationsByRestId(int restId) {
		HashMap<Integer, Calification> qMap = new HashMap<Integer, Calification>();
		
		try {
			Connection conn = DBManager.getInstance().getConnection();
			String sql = "select * from calificacion where restid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, restId);

			ResultSet rs = pstmt.executeQuery();
			int userid;
			String descripcion;
			int puntaje;
			Calification q;
			while(rs.next()){
				userid = rs.getInt("userid");
				descripcion = rs.getString("descripcion");
				puntaje = rs.getInt("puntaje");
				q = new Calification(puntaje, descripcion);
				qMap.put(userid,q);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qMap;
	}
}
