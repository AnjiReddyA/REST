package server.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Response.Status;

import server.exception.ServerException;
import server.model.Friend;

public class DBUtils 
{
	private static DataSource ds = null;
	
	/**
	 * Get the database connection from JNDI
	 * @return
	 * @throws SQLException
	 */
	public static Connection connect() throws SQLException
	{
		if (ds == null)
		{
			try 
			{
				Context envCtx = InitialContext.doLookup("java:/comp/env");
				ds = (DataSource)envCtx.lookup("jdbc/TestDB");
			} 
			catch (NamingException e) 
			{ new ServerException(Status.INTERNAL_SERVER_ERROR, "Database not configured."); }
		}
		
		return ds.getConnection();
	}

	public static void testDBConn()
	{
		try (Connection conn = connect())
		{} 
		catch (SQLException e) 
		{ throw new ServerException(Status.INTERNAL_SERVER_ERROR, "Database not available."); }
	}
	
	public static ArrayList<Friend> getAll()
	{
		ArrayList<Friend> friends = new ArrayList<Friend>();
		try (Connection conn = connect())
		{
			String s = "select * from test.friends";
			Statement stmt = conn.createStatement();
			
			try (ResultSet set = stmt.executeQuery(s))
			{
				while (set.next())
				{
					Friend f = new Friend();
					f.setId(set.getInt(1));
					f.setName(set.getString(2));
					f.setProfession(set.getString(3));
					f.setWebsite(set.getString(4));
					
					friends.add(f);
				}
			}
		} 
		catch (SQLException e) 
		{
			throw new ServerException(Status.INTERNAL_SERVER_ERROR, "Could not retrieve data.");
		}
		
		return friends;
	}
	
	public static Friend getById(int id)
	{
		Friend f = new Friend();
		boolean bFound = false;
		
		try (Connection conn = connect())
		{
			String s = "select * from test.friends where id=?";
			try (PreparedStatement stmt = conn.prepareStatement(s))
			{
				stmt.setInt(1, id);
				
				try (ResultSet set = stmt.executeQuery())
				{
					while (set.next())
					{
						f.setId(set.getInt(1));
						f.setName(set.getString(2));
						f.setProfession(set.getString(3));
						f.setWebsite(set.getString(4));
						
						bFound = true;
					}
					
					if (!bFound) 
						throw new ServerException(Status.NOT_FOUND, "Resource not found.");
				}
			}
		} 
		catch (SQLException e) 
		{ throw new ServerException(Status.INTERNAL_SERVER_ERROR, "Could not retrieve data."); }
		
		return f;
	}
		
	public static void add(Friend f)
	{
		try (Connection conn = connect())
		{
			String s = "insert into test.friends values(?, ?, ?, ?)";
			
			try (PreparedStatement p = conn.prepareStatement(s))
			{
				p.setInt(1, f.getId());
				p.setString(2, f.getName());
				p.setString(3, f.getProfession());
				p.setString(4, f.getWebsite());
				
				p.executeUpdate();
			}
		} 
		catch (SQLException e) 
		{ e.printStackTrace(); throw new ServerException(Status.INTERNAL_SERVER_ERROR, "Could not add data."); }
	}
}
