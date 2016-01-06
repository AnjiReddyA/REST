package server.model;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Friend 
{
	private int id = 0;
	private String name = null;
	private String website = null;
	private String profession = null;
	
	public Friend() {}
		
	public Friend(int id, String name, String profession, String website)
	{
		this.id = id;
		this.name = name;
		this.profession = profession;
		this.website = website;
	}
	
	public Friend(String name, String profession, String website)
	{
		this.name = name;
		this.profession = profession;
		this.website = website;
	}
	
	@XmlAttribute (required = true)
	public int getId() 
	{ return id; }
	
	public void setId(int id) 
	{ this.id = id; }
	
	public String getName() 
	{ return name; }
	
	public void setName(String name) 
	{ this.name = name; }
	
	public String getWebsite() 
	{ return website; }
	
	public void setWebsite(String website) 
	{ this.website = website; }
	
	public String getProfession()
	{ return profession; }
	
	public void setProfession(String profession)
	{ this.profession = profession; }
	
	@Override
	public String toString()
	{ return String.valueOf(id) + '/' + name + '/' + profession + '/' + website; }
	
	public static void main(String [] args) throws JsonParseException, JsonMappingException, IOException
	{
		String json = "{\"id\" : 22, \"name\" : \"John Doe\", \"profession\" : \"21\", \"website\" : \"www\"}";
		ObjectMapper mapper = new ObjectMapper();
		Friend friend = mapper.readValue(json.getBytes(), Friend.class); 
		System.out.println("Friend.main() " + friend.toString());
	}
}
