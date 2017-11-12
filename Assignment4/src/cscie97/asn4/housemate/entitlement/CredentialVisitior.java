package cscie97.asn4.housemate.entitlement;

/**
 * CredentialVisitior class is an implementation of InventoryVisitor Interface. 
 * CredentialVisitior associated with a Credential and visit user who has a given Credential.
 *
 * @author Xin Cheng
 */
public class CredentialVisitior implements InventoryVisitor{
	Credential credential;
	public void visit(User user){
		this.credential = user.getCredential();
	}
	
    /**
     * Public method that checks if a user matches a given Credential. Returns true if matches.
     *
     * @param credential target credential to match
     * @return boolean true if a user matches the input Credential, false otherwise.
     */
	public boolean checkCredential(String credential){
		String inputCredential = credential;
		if(!credential.startsWith("--"))
			inputCredential = String.valueOf(credential.hashCode());
		return this.credential.getValue().equals(inputCredential);
	}
}
