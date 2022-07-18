package config;
import java.sql.*;

public class DBConfig {

	
		private final String CONNECTION = "jdbc:mysql://localhost:3306/insurance?";
		private final String PASSWORD = "root";
		private static Connection con = null;
		
		static
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		
		public Connection getConnection() throws SQLException
		{
			if( con == null)
			{
				con = (Connection) DriverManager.getConnection(this.CONNECTION,"root",this.PASSWORD);
				System.out.print("Connection Successful.");
			}
			return con;
		}
}