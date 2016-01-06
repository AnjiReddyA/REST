package server.controller;

import java.util.ArrayList;
import java.util.Random;

import server.exception.ServerException;
import server.model.Friend;
import server.model.db.DBUtils;

/**
 * Manages the model and request resources.
 * @author pbose
 *
 */
public class FriendsMgr 
{
	public static ArrayList<Friend> getAll()
	{
		ArrayList<Friend> friends;
		try 
		{ friends = DBUtils.getAll(); } 
		catch (ServerException e) 
		{ throw e; }		
	
		return friends;
	}

	
	public static Friend getFriend(int id)
	{ return DBUtils.getById(id); }
	
	public static void addFriend(Friend f)
	{
		f.setId(generateId());
		DBUtils.add(f); 
	}

	protected static int generateId()
	{
		Random r = new Random();
		return r.nextInt(10000);
	}
}
