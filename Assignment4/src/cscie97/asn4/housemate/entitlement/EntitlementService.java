package cscie97.asn4.housemate.entitlement;

import java.util.HashMap;

public class EntitlementService {
	private HashMap<String, Role> roleMap = new HashMap<String, Role>();
	private HashMap<String, Permission> permissionMap = new HashMap<String, Permission>();
	private HashMap<String, Resource> resourceMap = new HashMap<String, Resource>();
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
    
    public void defineRole(String[] roleValues){
    	Role role = new Role();
    	role.setID(roleValues[1]);
    	role.setName(roleValues[2]);
    	role.setDescription(roleValues[3]);
    	roleMap.put(role.getID(), role);
    }
    
    public void definePermission(String[] permissionVlaues){
    	Permission permission = new Permission();
    	permission.setID(permissionVlaues[1]);
    	permission.setName(permissionVlaues[2]);
    	permission.setDescription(permissionVlaues[3]);
    	permissionMap.put(permission.getID(), permission);
    }
    
    public void addPermissionToRole(String[] prValues){
    	Role role = roleMap.get(prValues[1]);
    	Permission permission = permissionMap.get(prValues[2]);
    	role.addEntitlement(permission);
    }
    
    public void createResource(String[] resourceValues){
    	Resource resource = new Resource();
    	resource.setID(resourceValues[1]);
    	resource.setName(resourceValues[2]);
    	resourceMap.put(resource.getID(), resource);
    }
    
    public void createResourceRole(String[] rrValues) {
    	ResourceRole resourceRole = new ResourceRole();
    	resourceRole.setID(rrValues[1]);
    	resourceRole.setName(rrValues[2]);
    	Resource resource = resourceMap.get(rrValues[4]);
    	Role role = roleMap.get(rrValues[3]);
    	resourceRole.setResource(resource);
    	resourceRole.setRole(role);
    }
}
