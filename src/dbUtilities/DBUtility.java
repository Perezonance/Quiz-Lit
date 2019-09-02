package dbUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Set;

public class DBUtility {
	
	private Properties prop = new Properties();
	private static Connection connection;
	private File propFile;
	
	public DBUtility() {
		this(new File("db_default.properties"));
	}
	public DBUtility(File f) {
		try {
			if(f.exists()) {
				propFile = f;
				InputStream input = new FileInputStream(f);
				prop.load(input);
				
				connection = DBConnection.connect(f);
			}else {
				
				OutputStream output = new FileOutputStream(f);
				/*
				 * Change the Default db.user value and db.pass value to accomodate your own MySQL Server
				 */
				f.createNewFile();
				prop.setProperty("db.driver", "com.mysql.cj.jdbc.Driver");
				prop.setProperty("db.url", "jdbc:mysql://localhost:3306/");
				prop.setProperty("db.user", "root");
				prop.setProperty("db.pass", "root");
				
				prop.store(output, "");
				propFile = f;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getDBInstance() {
		return connection;
	}
	public static String testConnection(Connection con){
		if(con == null){
			return "Not Connected";
		}else
			return "Connected";
	}
	public static String testConnection(){
		if(connection == null){
			return "Not Connected";
		}else
			return "Connected";
	}
	public static String closeConnection(Connection connection){
		if(connection == null){
			return "No connection available to close!";
		}else{
			try {
				connection.close();
				return "Connection to DB closed";
			}catch(SQLException e){
				e.printStackTrace();
				return "Connection Failed";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "Connection Failed";
			}
		}
	}
	public static String closeConnection(){
		if(connection == null){
			return "No connection available to close!";
		}else{
			try {
				connection.close();
				return "Connection to DB closed";
			}catch(SQLException e){
				e.printStackTrace();
				return "Connection Failed";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "Connection Failed";
			}
		}
	}
	public static String saveData(String tableName, HashMap<String, String> hm, Connection con){
		String query = "INSERT INTO " + tableName + "( ";
		String values = "(";
		int x = 0;
		
		Set<String> keys = hm.keySet();
		Iterator iter = keys.iterator();
		int counter = 0;
		while(iter.hasNext()) {
			if(counter > 0) {
				Object keyLast = iter.next();
				query += ", " + keyLast;
				values += "', '"  + hm.get(keyLast);
			}else {
				Object keyLast = iter.next();
				counter++;
				query += keyLast;
				values += "'" + hm.get(keyLast);
			}	
		}
		query += ") VALUES ";
		values += "');";
		query += values;
		System.out.println(query);
		
		
		Statement st;
		try {
			st = con.createStatement();
			x = st.executeUpdate(query);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (x + " Rows affected!");
	}
	public static String saveData(String tableName, HashMap<String, String> hm){
		String query = "INSERT INTO " + tableName + "( ";
		String values = "(";
		int x = 0;
		
		Set<String> keys = hm.keySet();
		Iterator iter = keys.iterator();
		int counter = 0;
		while(iter.hasNext()) {
			if(counter > 0) {
				Object keyLast = iter.next();
				query += ", " + keyLast;
				values += "', '"  + hm.get(keyLast);
			}else {
				Object keyLast = iter.next();
				counter++;
				query += keyLast;
				values += "'" + hm.get(keyLast);
			}	
		}
		query += ") VALUES ";
		values += "');";
		query += values;
		System.out.println(query);
		
		
		Statement st;
		try {
			st = connection.createStatement();
			x = st.executeUpdate(query);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (x + " Rows affected!");
	}
	public static boolean updateQuery(String query, Connection con) {
		Statement st;
		try {
			
			 st = con.createStatement();
			 st.executeUpdate(query);    
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static int updateQuery(String query) {
		System.out.println(query);
		Statement st;
		int rowsAffected = 0;
		try {
			 st = connection.createStatement();
			 rowsAffected = st.executeUpdate(query);    
		} catch(SQLException e) {
			e.printStackTrace();
			rowsAffected = -1;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rowsAffected = -1;
		}
		return rowsAffected;
	}
	public static ResultSet executeQuery(String query, Connection con) {
		ResultSet rs = null;
		Statement st;
		try {
			 st = con.createStatement();
			 rs = st.executeQuery(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	public static ResultSet executeQuery(String query) {
		ResultSet rs = null;
		Statement st;
		try {
			 st = connection.createStatement();
			 rs = st.executeQuery(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	public static String printResultSet(ResultSet rs) {
		String str = "";
		ResultSetMetaData rsmd;
		
		try {
			rsmd = rs.getMetaData();
			while(rs.next()) {
				str = str + "<br/>:";
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					str = str + (rs.getString(i) + " : ");
				}
			}
			return str;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
	public static boolean existsinDB(String table, String value, String columnType) {
		String query = "SELECT `" + columnType + "` FROM " + table + " WHERE `" + columnType + "` = '" + value + "';";
		System.out.println(query);
		ResultSet rs = executeQuery(query);
		System.out.println(printResultSet(rs));
		boolean response = false;
		try {
			if(rs.getString(1).equals(value))
				response = true;
			else
				response = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
