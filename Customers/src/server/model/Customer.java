package server.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Customer 
{
	private String name;
	
	public Customer() {}
	
	public Customer(String name)
	{ this.name = name; }
	
	@Override
	public String toString()
	{ return name; }
}
