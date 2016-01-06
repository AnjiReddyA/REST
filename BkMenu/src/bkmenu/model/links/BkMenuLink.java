package bkmenu.model.links;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "breakfast_menu")
@XmlAccessorType (XmlAccessType.FIELD)
public class BkMenuLink 
{
	@XmlElement (name = "food")
	private ArrayList<BkMenuItemLink> menu = new ArrayList<BkMenuItemLink>();
	
	public ArrayList<BkMenuItemLink> getMenu() 
	{ return menu; }

	public BkMenuLink() {}
	
	public BkMenuLink(ArrayList<BkMenuItemLink> menu)
	{ this.menu = menu; }
}
