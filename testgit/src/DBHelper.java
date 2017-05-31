
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {

	public Connection driverDBcon() throws ClassNotFoundException, SQLException{
		Connection recon = null;
		Class.forName("com.mysql.jdbc.Driver");
		String jdbcDriver = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=euckr";
		String dbUser = "root";
		String dbPass = "java0000";
		
		recon = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		return recon;
		}
	
	public void close(PreparedStatement stmt, ResultSet rs){ //stmt, rs를 받아오기위해 데이터타입을 똑같이 해줘야 함
		if(rs != null){
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
		}
		if(stmt != null){
			try{stmt.close();}catch(SQLException e){e.printStackTrace();}
		}
	}
}
 // test 주석 2