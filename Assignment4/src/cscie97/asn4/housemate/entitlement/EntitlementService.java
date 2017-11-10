package cscie97.asn4.housemate.entitlement;

import java.util.HashMap;

public class EntitlementService {
	private HashMap<String, Role> roleMap = new HashMap<String, Role>();
	private HashMap<String, Permission> permissionMap = new HashMap<String, Permission>();
	private HashMap<String, Resource> resourceMap = new HashMap<String, Resource>();
	private HashMap<String, ResourceRole> resourceRoleMap = new HashMap<String, ResourceRole>();
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
    
    public void defineRole(String[] roleValues){
    	Role role = new Role();
    	role.setID(roleValues[1]);
    	role.setName(roleValues[2]);
    	role.setDescription(roleValues[3]);
    	roleMap.put(role.getID(), role);
    	System.out.println("Role: " + role.getName() + " created!");
    }
    
    public void definePermission(String[] permissionVlaues){
    	Permission permission = new Permission();
    	permission.setID(permissionVlaues[1]);
    	permission.setName(permissionVlaues[2]);
    	permission.setDescription(permissionVlaues[3]);
    	permissionMap.put(permission.getID(), permission);
    	System.out.println("Permission: " + permission.getName() + " created!");
    }
    
    public void addPermissionToRole(String[] prValues){
    	Role role = roleMap.get(prValues[1]);
    	Permission permission = permissionMap.get(prValues[2]);
    	role.addEntitlement(permission);
    	System.out.println("Permission: " + permission.getName() + " added to Role: " + role.getName());
    }
    
    public void createResource(String[] resourceValues){
    	Resource resource = new Resource();
    	resource.setID(resourceValues[1]);
    	resource.setName(resourceValues[2]);
    	resourceMap.put(resource.getID(), resource);
    	System.out.println("Resource: " + resource.getName() + " created!");
    }
    
    public void createResourceRole(String[] rrValues) {
    	ResourceRole resourceRole = new ResourceRole();
    	resourceRole.setID(rrValues[1]);
    	resourceRole.setName(rrValues[2]);
    	Resource resource = resourceMap.get(rrValues[4]);
    	Role role = roleMap.get(rrValues[3]);
    	resourceRole.setResource(resource);
    	resourceRole.setRole(role);
    	resourceRoleMap.put(resourceRole.getID(), resourceRole);
    	System.out.println("ResourceRole: " + resourceRole.getName() + " created!");
    }
    
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
		System.out.println(user);
    }
}
