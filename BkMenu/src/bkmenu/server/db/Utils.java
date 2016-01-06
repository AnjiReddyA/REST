package bkmenu.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Response.Status;
import javax.xml.ws.WebServiceException;

import bkmenu.model.BkMenu;
import bkmenu.model.BkMenuItem;
import bkmenu.model.links.BkMenuItemLink;
import bkmenu.model.links.BkMenuLink;
import bkmenu.server.exception.ServerException;

public class Utils 
{
	private static DataSource ds = null;
	
	/**
	 * Get the database connection from JNDI
	 * @return
	 * @throws SQLException
	 */
	private static Connection connect() throws SQLException
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
	
	public static void ping()
	{
		try
		{
			try (Connection conn = connect())
			{}
		}
		catch (SQLException e) 
		{ throw new WebServiceException("Database not available."); }
	}

	public static void testConnection()
	{
		try (Connection conn = connect())
		{} 
		catch (SQLException e) 
		{ throw new ServerException(Status.INTERNAL_SERVER_ERROR, "Database not available."); }
	}

	public static BkMenu getById(int id) 
	{
		try (Connection conn = connect())
		{
			String s = "select * from test.bk_menu where id=?";
			try (PreparedStatement stmt = conn.prepareStatement(s))
			{
				stmt.setInt(1, id);
				
				try (ResultSet set = stmt.executeQuery())
				{
					return buildMenu(set);
				}
			}
		} 
		catch (SQLException e) 
		{ throw new ServerException(Status.NOT_FOUND, "Could not retrieve data."); }
	}
	
	private static BkMenu buildMenu(ResultSet set) throws SQLException
	{
		BkMenu menu = new BkMenu();
		BkMenuItem f = null;

		while (set.next())
		{
			f = new BkMenuItem(set.getInt(1), 
					set.getString(2), 
					set.getFloat(3), 
					set.getString(4));
			
			menu.getMenu().add(f);
		}
		
		if (menu.getMenu().size() == 0) 
			throw new ServerException(Status.NOT_FOUND, "Resource not found.");
		
		return menu;
	}

	public static BkMenu getByName(String name) 
	{		
		try (Connection conn = connect())
		{
			String s = "select * from test.bk_menu where name like ?";
			try (PreparedStatement stmt = conn.prepareStatement(s))
			{
				stmt.setString(1, "%" + name + "%");
				
				try (ResultSet set = stmt.executeQuery())
				{
					return buildMenu(set);
				}
			}
		} 
		catch (SQLException e) 
		{ throw new ServerException(Status.INTERNAL_SERVER_ERROR, "Could not retrieve data."); }		
	}

	public static void createItem(BkMenuItem item) 
	{
		try(Connection conn = connect())
		{
			String sqlStmt = "INSERT INTO test.bk_menu VALUES(?, ?, ?, ?)";
			
			try(PreparedStatement stmt = conn.prepareStatement(sqlStmt);)
			{
				stmt.setInt(1, item.getId());
				stmt.setString(2, item.getName());
				stmt.setFloat(3, item.getPrice());
				stmt.setString(4, item.getDesc());

				stmt.executeUpdate();
			}
		} 
		catch (SQLException e) 
		{ throw new ServerException(Status.INTERNAL_SERVER_ERROR, "Could not create menu item."); }		
	}

	public static void updateItem(int id, float price) 
	{
		String sqlStmt = "UPDATE test.bk_menu SET test.bk_menu.price = ? where test.bk_menu.id = ?";
		
		try(Connection conn = connect())
		{
			try(PreparedStatement stmt = conn.prepareStatement(sqlStmt);)
			{
				stmt.setFloat(1, price);
				stmt.setInt(2, id);
				stmt.executeUpdate();
			}
		}
		catch (SQLException e) 
		{ throw new ServerException(Status.INTERNAL_SERVER_ERROR, "Could not update menu item."); }		
	}

	/**
	 * Works for HATEOAS based menus.
	 * @return
	 */
	public static BkMenuLink getAll() 
	{
		BkMenuLink menu = new BkMenuLink();
		BkMenuItemLink f = null;
		
		try (Connection conn = connect())
		{			
			String s = "select * from test.bk_menu";
			Statement stmt = conn.createStatement();

			try (ResultSet set = stmt.executeQuery(s))
			{
				while (set.next())
				{
					f = new BkMenuItemLink(set.getInt(1), 
							set.getString(2), 
							set.getFloat(3), 
							set.getString(4), null);
					
					menu.getMenu().add(f);
				}
				
				if (menu.getMenu().size() == 0) 
					throw new ServerException(Status.NOT_FOUND, "Resource not found.");
			}
		} 
		catch (SQLException e) 
		{ throw new ServerException(Status.NOT_FOUND, "Could not retrieve data."); }
		
		return menu;
	}
}
