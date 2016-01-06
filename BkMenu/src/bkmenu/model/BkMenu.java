package bkmenu.model;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "breakfast_menu")
@XmlAccessorType (XmlAccessType.FIELD)
public class BkMenu 
{
	@XmlElement (name = "food")
	private ArrayList<BkMenuItem> menu = new ArrayList<BkMenuItem>();
	
	public ArrayList<BkMenuItem> getMenu() 
	{ return menu; }

	public BkMenu() {}
	
	public BkMenu(ArrayList<BkMenuItem> menu)
	{ this.menu = menu; }
	
	public static void main(String [] args) throws JAXBException
	{
		JAXBContext ctx = JAXBContext.newInstance(BkMenu.class);
		
		Unmarshaller reader = ctx.createUnmarshaller();
		BkMenu menu = (BkMenu)reader.unmarshal(new File("./scripts/menu.xml"));
		
		for (BkMenuItem item : menu.getMenu())
			System.out.println(item.toString());
		
		testWriter();
	}
	
	private static void testWriter() throws JAXBException
	{
		BkMenuItem item = new BkMenuItem(100, "bread", (float)5.00, "Plain slice bread");
		ArrayList<BkMenuItem> items = new ArrayList<BkMenuItem>();
		items.add(item);
		BkMenu menu = new BkMenu(items);
		JAXBContext ctx = JAXBContext.newInstance(BkMenu.class);
		Marshaller writer = ctx.createMarshaller();
		writer.marshal(menu, new File("./scripts/test.xml"));		
	}
}
