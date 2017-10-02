package cscie97.asn2.housemate.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Sensor {
	protected String name;
	protected Map<String, String> status;
	protected Room location;
		
	public Sensor() {
		
	}
	
	public Sensor(String name) {
		this.name = name;
		this.status = new HashMap<>();
	}
	
	public String getName(){
		return name;
	}
	
	public Map<String, String> getStatus(){
		return status;
	}
	
	public Room getLocation(){
		return location;
	}
	
	public void setStatus(String statusName, String value) throws SettingException{
		status.put(statusName, value);
	}
	public void print(){
		System.out.println("name: " + name); 
	    Iterator it = status.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	    }
	}
}
