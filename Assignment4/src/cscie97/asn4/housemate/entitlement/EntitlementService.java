package cscie97.asn4.housemate.entitlement;

/**
 * The EntitlementService is used to control access to the House Mate System.
 * It verifies the user credential and checks the user's permission.
 * The EntitlementService is a singleton, meaning there is only one instance of this class.  A special static method
 * (getInstance()) is provided to access the single HouseMateModelService instance. 
 *
 * @author Xin Cheng
 */
import java.util.HashMap;
import java.util.List;

public class EntitlementService implements IEntitlementService{
	private HashMap<String, Role> roleMap = new HashMap<String, Role>();
	private HashMap<String, Permission> permissionMap = new HashMap<String, Permission>();
	private HashMap<String, Resource> resourceMap = new HashMap<String, Resource>();
	private HashMap<String, ResourceRole> resourceRoleMap = new HashMap<String, ResourceRole>();
	private HashMap<String, User> userMap = new HashMap<String, User>();
    
	/**
     * Private static singleton holder
     */
    private static class EntitlementServiceSingletonHolder {
        public static final EntitlementService INSTANCE = new EntitlementService();
    }
    
    /**
     * This method returns a reference to the single static instance of the EntitlementService.
     *
     * @return single instance of EntitlementService.
     */
    public static EntitlementService getInstance() {
        return EntitlementServiceSingletonHolder.INSTANCE;
    }
    
    public HashMap<String, Role> getRoles(){
    	return roleMap;
    }
    
	/**
     * Public method to create a role and store the role into system. 
     * Input array content: roleID, role name, role description.
     *
     * @param roleValues information of role to be created
     */
    public void defineRole(String[] roleValues){
    	Role role = new Role();
    	role.setID(roleValues[1]);
    	role.setName(roleValues[2]);
    	role.setDescription(roleValues[3]);
    	roleMap.put(role.getID(), role);
    	System.out.println(">> Role: " + role.getName() + " created!");
    }
    
	/**
     * Public method to create a permission using given information and store permission in system. 
     * Input is a string array contains the permission information. 
     * Input array content: permissionID, permission name, permission description.
     *
     * @param permissionVlaues information of permission to be created
     */
    public void definePermission(String[] permissionVlaues){
    	Permission permission = new Permission();
    	permission.setID(permissionVlaues[1]);
    	permission.setName(permissionVlaues[2]);
    	permission.setDescription(permissionVlaues[3]);
    	permissionMap.put(permission.getID(), permission);
    	System.out.println(">> Permission: " + permission.getName() + " created!");
    }
    
	/**
     * Public method to create a resource and store the resource into system. 
     * Input array content: resource id, resource name
     *
     * @param prValues permission id and role id
     */
    public void addPermissionToRole(String[] prValues){
    	Role role = roleMap.get(prValues[1]);
    	Permission permission = permissionMap.get(prValues[2]);
    	role.addEntitlement(permission);
    	System.out.println(">> Permission: " + permission.getName() + " added to Role: " + role.getName());
    }
    
	/**
     * Public method that add permission to a role. 
     * Input array content: role id, permission id.
     *
     * @param resourceValues information of resource to be created
     */
    public void createResource(String[] resourceValues){
    	Resource resource = new Resource();
    	resource.setID(resourceValues[1]);
    	resource.setName(resourceValues[2]);
    	resourceMap.put(resource.getID(), resource);
    	System.out.println(">> Resource: " + resource.getName() + " created!");
    }
    
	/**
     * Public method to create a resource role and store the resource role into system. 
     * Input array content: resourceRole id, resource role name, role id, resource id
     *
     * @param rrValues information of resource role to be created
     */
    public void createResourceRole(String[] rrValues) {
    	ResourceRole resourceRole = new ResourceRole();
    	resourceRole.setID(rrValues[1]);
    	resourceRole.setName(rrValues[2]);
    	Resource resource = resourceMap.get(rrValues[4]);
    	Role role = roleMap.get(rrValues[3]);
    	resourceRole.setResource(resource);
    	resourceRole.setRole(role);
    	resourceRoleMap.put(resourceRole.getID(), resourceRole);
    	System.out.println(">> " + resourceRole + " created!");
    }
    
	/**
     * Public method to create a user using given information and stores user in system. 
     * Input is a string array contains user information. 
     * Input array content: roleID(role  of user), userID(id of user), username(name of user).
     *
     * @param userValues information of user to be created
     */
    public void createUser(String[] userValues){
    	String role = userValues[1];
    	User user;
    	UserFactory userFactory;
    	switch (role) {
		case "admin_role":		
			userFactory = new AdministratorFactory();
			break;
		case "adult_resident":		
			userFactory = new AdultResidentFactory();
			break;
		default:
			userFactory = new ChildResidentFactory();
			break;
		}  	
		user = new User(userFactory);
		user.create(userValues[2], userValues[3]);
		System.out.println(">> " + user);
		userMap.put(user.getID(), user);
    }
    
	/**
     * Public method that gives user a credential. 
     * Input array content: user id, credential name, credential value.
     *
     * @param credValues userid and credential information
     */
    public void addUserCredential(String[] credValues){
    	User user = userMap.get(credValues[1]);
    	Credential credential;
    	String cred = credValues[2];
    	switch (cred) {
		case "password":
			credential = new UsernamePassworkCredential();
			break;
		default:
			credential = new VoiceCredential();
			break;
		}
    	credential.setName(cred);
    	credential.setValue(credValues[3]);
    	user.setCredential(credential);
    	System.out.println(">> " + user);
    }
    
	/**
     * Public method that add resource role to a user. 
     * Input array content: user id, resource role id.
     *
     * @param resourceRoleValues userid and resource role id.
     */
    public void addResourceRole(String[] resourceRoleValues){
    	User user = userMap.get(resourceRoleValues[1]);
    	ResourceRole resourceRole = resourceRoleMap.get(resourceRoleValues[2]);
    	user.setResourceRole(resourceRole);
    	System.out.println(">> " + user);
    }
    
	/**
     * Public method that logs a user into the system. 
     * Input array contet: user id, user credential. 
     * If user is not found in system, throws EntityNotFoundException. 
     * If credential and user do not match, throws InvalidCredentialException.
     *
     * @param logInfo user id and credential
	 * @throws EntityNotFoundException, InvalidCredentialException
     */	
    public void login(String[] logInfo) throws EntityNotFoundException, InvalidCredentialException{
    	String userId = logInfo[1];
    	String credentialValue = logInfo[3];
    	
    	// Your account or password is incorrect.
    	User user;
    	// Check if the user is a registered user
    	if(userMap.containsKey(userId))
    		user = userMap.get(userId);
    	else {
    		EntityNotFoundException e = new EntityNotFoundException();
    		e.setEntityName(userId);
    		throw e;
    	}
    	
    	CredentialVisitior visitor = new CredentialVisitior();
    	user.accept(visitor);
    	
		if(visitor.checkCredential(credentialValue)) {
    		// Credential matches the user
    		// If the access token is inactive, give the user a new access token, else do nothing
    		if(user.getAccessToken().getState().equals("inactive"))
    			user.createAccessToken();
		} else {
    		// Credential does not match the user, InvalidCredentialException
    		InvalidCredentialException e = new InvalidCredentialException();
    		e.setUserId(user.getID());
    		throw e;
		}
		
    	System.out.println(">> " + "Welcom '" + userId + "'! You are logged in successfully!");
    }
    
	/**
     * Public method that logs user out of the system, and marks user’s access token invalid.
     *
     * @param userId id of user
     */	
    public void logout(String userId){
    	User user = userMap.get(userId);
    	user.getAccessToken().setState("inactive");
    	System.out.println(">> " + "User: '" + userId + "' logged out successfully!");
    }
    
	/**
     * Public method for identifying user using input credential.
     * If identified, return user's AccessToken.
     * Else, throw EntityNotFoundException.
     * Visitor pattern is used to check the credential.
     *
     * @param credential credential that needs to be verified.
     * @return the AccessToken of a user
	 * @throws EntityNotFoundException
     */	
    public AccessToken identify(String credential) throws EntityNotFoundException{
    	CredentialVisitior visitor = new CredentialVisitior();
    	for(HashMap.Entry<String, User> entry: userMap.entrySet()) {
    		User user = entry.getValue();
    		user.accept(visitor);
    		
    		if(visitor.checkCredential(credential)) {
    			System.out.println(">> Credential Verified!!");
    			return user.getAccessToken();
    		}	
    	}
    	EntityNotFoundException entityNotFoundException = new EntityNotFoundException();
    	entityNotFoundException.setEntityName(credential);
    	throw entityNotFoundException;
    }
    
	/**
     * Public method that checks whether a given accessToken have permission to a resource. 
     * If the given resource is not found in the system, throw EntityNotFoundException. 
     * If accessToken is not valid, throws InvalidAccessTokenException.
     *
     * @param accessToken accessToke of a user
     * @param resourceID resourceId that user want to access
     * @param permissionID permission id that user want to apply to the resource
     * @return true, if access is allowed, false otherwise
	 * @throws EntityNotFoundException, InvalidAccessTokenException
     */
    public boolean checkAccess(AccessToken accessToken, String resourceID, String permissionID) throws EntityNotFoundException, InvalidAccessTokenException{
    	AccessTokenVisitor accessTokenVisitor = new AccessTokenVisitor();
    	
    	for(HashMap.Entry<String, User> entry: userMap.entrySet()) {
    		User user = entry.getValue();
    		user.accept(accessTokenVisitor);
    		
    		// Check if an accessToken is valid
    		if(accessTokenVisitor.checkAccessToken(accessToken)) {
    			if(user.getAccessToken().getState().equals("inactive")) {
    				InvalidAccessTokenException invalidAccessTokenException = new InvalidAccessTokenException();
    				invalidAccessTokenException.setUserName(user.getName());
    				throw invalidAccessTokenException;
    			} else {
    				// Valid accessToken, check access
    				ResourceRole resourceRole = user.getResourceRole();
    				// Check if user has access to a resource.
    				if(resourceRole.getResource().getID().equals(resourceID)) {
    					// User has access to this resource, find if the user has this permission
    					Role role = user.getRole();
    					List<Entitlement> entitlements = role.getEntitlements();
    					for (Entitlement entitlement : entitlements) {
							if(entitlement.getID().equals(permissionID)) {
								return true;	// User has permission
							}
						}
    					// User do not have permission
    					System.out.println(">> User:" + user.getID() + " dose not have permission:" + permissionID);
    					return false;
    				} else {
    					// User do not have access to this resource
    					System.out.println(">> User:" + user.getID() + " dose not have access to Resource:" + resourceID);
    					return false;
    				}
    				
    			}
    		}	
    	}
    	// AccessToken is not found in system
    	EntityNotFoundException entityNotFoundException = new EntityNotFoundException();
    	entityNotFoundException.setEntityName("AccessToken");
    	throw entityNotFoundException;
    }
}
