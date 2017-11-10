package cscie97.asn4.housemate.entitlement;

import java.util.HashMap;

public class ChildResidentFactory implements UserFactory{
	public Role createRole(){
		HashMap<String, Role> roleMap = EntitlementService.getInstance().getRoles();
		Role role = roleMap.get("child_resident");
		return role;
	}
}
