package bkmenu.model.links;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement (name = "menu_item")
@XmlAccessorType (XmlAccessType.FIELD)
public class BkMenuItemLink 
{
	private int id;
	private String name;
	private float price;
	@XmlElement (name = "description")
	private String desc;

	@XmlJavaTypeAdapter (Link.JaxbAdapter.class)
	private Link link;


	public void setLink(Link link) 
	{ this.link = link; }

	public int getId() 
	{ return id; }

	public String getName() 
	{ return name; }

	public float getPrice() 
	{ return price; }

	public String getDesc() 
	{ return desc; }

	public Link getLink() 
	{ return link; }
	
	public BkMenuItemLink() {}
	
	public BkMenuItemLink(int id, String name, float price, String desc, Link link)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.desc = desc;
		this.link = link;
	}
	
	@Override
	public String toString()
	{ return id + "/" + name + "/" + price + "/" + desc + "/" + link.toString(); }
}
