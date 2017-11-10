package cscie97.asn4.housemate.entitlement;

import java.util.ArrayList;
import java.util.List;

public class Role extends Entitlement{
	private List<Entitlement> permissions;
	
	/**
     * Public default constructor
     */
	public Role() {
		permissions = new ArrayList<>();
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
}
