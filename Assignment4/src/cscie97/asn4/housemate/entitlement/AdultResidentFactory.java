package cscie97.asn4.housemate.entitlement;

/**
 * AdultResidentFactory class is implementation of UserFactory Interface. It creates Role of an adult resident. 
 *
 * @author Xin Cheng
 */
import java.util.HashMap;

public class AdultResidentFactory implements UserFactory{
	public Role createRole(){
		HashMap<String, Role> roleMap = EntitlementService.getInstance().getRoles();
		Role role = roleMap.get("adult_resident");
		return role;
	}
}
