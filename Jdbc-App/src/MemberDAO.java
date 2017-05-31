import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MemberDAO {
	private String url;
	private String dbid;
	private String dbpw;
	
	private void dbPropertiesinit() throws IOException{
		FileInputStream fis = new FileInputStream("d:\\db.Properties"); //외부 (text파일)의 내용을 inputStream을 써서 가져왔다!!.
		Properties pro = new Properties(); // 파일형식이 ?=? 인 형식으로 되있어야함
		pro.load(fis);
		this.url = pro.getProperty("url");
		this.dbid = pro.getProperty("dbid");
		this.dbpw = pro.getProperty("dbpw");
	}
	
	public Member selectMemberById(String id) throws ClassNotFoundException, SQLException, IOException{//멤버값으로 리턴할거임
		this.dbPropertiesinit();
		System.out.println(url);
		System.out.println(dbid);
		System.out.println(dbpw);
		//properties에서 db정보 가져옵니다.(내부적으로 input..
		Class.forName("oracle.jdbc.OracleDriver");		
		Connection conn = DriverManager.getConnection("this.url","this.dbid","this.dbpw");
		String sql= "SELECT * FROM ORACLE_MEMBER WHERE ora_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		Member member = null;
		if(rs.next()){
			member = new Member();
			member.setId(rs.getString("ora_id"));
			member.setPw(rs.getString("ora_pw"));
			member.setLevel(rs.getString("ora_level"));
			member.setName(rs.getString("ora_name"));
			member.setEmail(rs.getString("ora_email"));
		}
		
		
		return member; //멤버값으로 리턴할거임
		
	}
	
	public static void main() throws ClassNotFoundException, SQLException, IOException{
		MemberDAO mdao = new MemberDAO();
		Member m = mdao.selectMemberById("1");
		/*System.out.println(m.getId().equals(arg0));*/
	}
	
}
