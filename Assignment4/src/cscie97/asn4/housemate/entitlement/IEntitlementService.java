package cscie97.asn4.housemate.entitlement;

/**
 * The IEntitlementService is an interface that provides public APIs for users. 
 * All APIs meets the functional requirements of the Entitlement Service, and fits the House Mate System. 
 * House Mate Model Service and House Mate Controller Service can use the Entitlement Service to verify users that trying to access the system.
 *
 * @author Xin Cheng
 */

public interface IEntitlementService {
	public AccessToken identify(String credential) throws EntityNotFoundException;
	public boolean checkAccess(AccessToken accessToken, String resourceID, String permissionID) throws EntityNotFoundException, InvalidAccessTokenException;
	public void createUser(String[] userValues);
	public void definePermission(String[] permissionVlaues);
	public void defineRole(String[] roleValues);
	public void createResource(String[] resourceValues);
	public void createResourceRole(String[] rrValues);
	public void addPermissionToRole(String[] prValues);
	public void addUserCredential(String[] credValues);
	public void addResourceRole(String[] resourceRoleValues);
	public void login(String[] logInfo) throws EntityNotFoundException, InvalidCredentialException;
	public void logout(String userId);
}
