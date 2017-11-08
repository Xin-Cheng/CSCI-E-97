package cscie97.asn4.housemate.entitlement;

public interface IEntitlementService {
	public User createUser();
	public Entitlement createEntitlement();
	public Role createRole();
	public Credential createCredential();
	public ResourceRole createResourceRole();
}
