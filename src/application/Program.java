package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("INSERT INTO seller"
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+"VALUES"
					+"(?,?,?,?,?)",
					+ Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, "clara");
			ps.setString(2, "jhonatan@outlok.com");
			ps.setDate(3, new java.sql.Date(sdf.parse("25/35/2000").getTime()));
            ps.setDouble(4, 1500);
            ps.setInt(5, 4);
            
            int rowsAfected = ps.executeUpdate();
            
            if (rowsAfected > 0) {
            	ResultSet rs = ps.getGeneratedKeys();
            	while(rs.next()) {
            		int id = rs.getInt(1);
            		System.out.println("done, id = "+ id);
            	}
            }
            else {
            	System.out.println("nenhuma informação adicionada");
            }
		}
		catch(SQLException e ) {
			e.printStackTrace();
		}
		catch(ParseException r) {
			r.printStackTrace();
		}
		finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}
}