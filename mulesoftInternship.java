package mulesoftTask;

import java.sql.*;

class databaseOperations {
	
	public void printLine() {
		System.out.println("----------------------------------------------------------------------------------------------\n");		
	}
	
	public void createDatabase(String dbName) {
		String url = "jdbc:sqlite:" + dbName + ".db";
		try {
			Connection conn = DriverManager.getConnection(url);
            if (conn != null) {  
                System.out.println("A new database has been created.");
            }
            conn.close();
        }
        catch (SQLException e) {  
        	System.out.println(e.getMessage());
        }
	}
	
	public void createTable(String dbName, String tableName) {
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoft.db");
			Statement statement = con.createStatement();
	        statement.setQueryTimeout(30);
	        
	        String query = "CREATE TABLE IF NOT EXISTS movies(id integer PRIMARY KEY AUTOINCREMENT,aname text NOT NULL, amovie text NOT NULL, UNIQUE(aname, amovie));";
	        statement.execute(query);
	        System.out.println("The Table has been created.");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertData(String aname, String amovie) {
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoft.db");
			Statement statement = con.createStatement();
	        statement.setQueryTimeout(30);
	        String query = "INSERT INTO movies(aname, amovie) VALUES(?,?)";
	        
	        PreparedStatement pstmt = con.prepareStatement(query);
	        pstmt.setString(1, aname);
	        pstmt.setString(2, amovie);
	        pstmt.executeUpdate();        
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void retrieveData(String aname) {
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoft.db");
			Statement statement = con.createStatement();
	        statement.setQueryTimeout(30);
	        String query = "SELECT aname, amovie FROM movies";
	        if(aname != null) {
	        	query = query + " where aname='" + aname + "'";
	        }
	        ResultSet rs = statement.executeQuery(query);
	        String format = "%-20s-\t%s\n";
	        System.out.printf(format, "Artist Name", "Acted Movie");
	        while (rs.next()) {
	        	System.out.printf(format,rs.getString(1),rs.getString(2));
            }			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

public class mulesoftInternship {
	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			// Database Connection Section
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoft.db");
            System.out.println("SQLite Database Connected successfully");
            databaseOperations dbo = new databaseOperations();
            dbo.printLine();
            
            // Database Creation Section
            dbo.createDatabase("abhinav");
            dbo.printLine();
            
            
            // Table Creation Section
            dbo.createTable("abhinav", "movies");
            dbo.printLine();
            
            
            // Data Insertion Section
            dbo.insertData("mahesh babu", "spyder");
            dbo.insertData("mahesh babu", "dookudu");
            dbo.insertData("prabhas", "bahubali");
            dbo.insertData("pawan kalyan", "panjaa");
            dbo.insertData("ram charan", "rangasthalam");
            dbo.insertData("tom cruise", "mission impossible");
            dbo.insertData("mahesh babu", "sarileru neekevvaru");
            dbo.insertData("junior ntr", "temper");
            dbo.insertData("prabhas", "saaho");
            dbo.insertData("nithin", "maestro");
            System.out.println("Data Inserted Into The Table.");
            dbo.printLine();
            
            
            // Data Querying Section
            System.out.println("Querying Details:");
            dbo.retrieveData(null);
            dbo.printLine();
            System.out.println("Querying Details With A Particular Actor Name:");
            dbo.retrieveData("mahesh babu");
            dbo.printLine();
            
            con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
