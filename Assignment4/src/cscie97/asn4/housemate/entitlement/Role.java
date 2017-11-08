package cscie97.asn4.housemate.entitlement;

import java.util.List;

public class Role extends Entitlement implements Visitor{
	private List<Entitlement> permissions;
	private int index = -1;
	
	/**
     * Public default constructor
     */
	public Role() {
		super();
	}
	
	/**
     * Public method add an entitlement to a role
     * 
     * @param entitlement new entitlement to be added
     */
	public void addEntitlement(Entitlement entitlement){
		permissions.add(entitlement);
	}
	
	/**
     * Public method delete an entitlement from a role
     * 
     * @param entitlement new entitlement to be deleted
     */
	public void deleteEntitlement(Entitlement entitlement) {
		permissions.remove(entitlement);
	}
	
	@Override
	public boolean hasNext(){
		return index < permissions.size() - 1;
	}
	
	@Override
	public Entitlement getNext(){
		index++;
		return permissions.get(index);
	}
}
