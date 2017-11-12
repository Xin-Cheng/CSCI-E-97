package cscie97.asn4.housemate.entitlement;

/**
 * AdministratorFactory class is implementation of UserFactory Interface. It creates Role of a child resident.

 *
 * @author Xin Cheng
 */
import java.util.HashMap;

public class ChildResidentFactory implements UserFactory{
	public Role createRole(){
		HashMap<String, Role> roleMap = EntitlementService.getInstance().getRoles();
		Role role = roleMap.get("child_resident");
		return role;
	}
}
