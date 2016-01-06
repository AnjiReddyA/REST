package bkmenu.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "menu_item")
@XmlAccessorType (XmlAccessType.FIELD)
public class BkMenuItem
{
	private int id;
	private String name;
	private float price;
	@XmlElement (name = "description")
	private String desc;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public String getDesc() {
		return desc;
	}
	
	public BkMenuItem() {}
	
	public BkMenuItem(int id, String name, float price, String desc)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.desc = desc;
	}
	
	@Override
	public String toString()
	{ return id + "/" + name + "/" + price + "/" + desc; }
}
