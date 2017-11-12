package cscie97.asn4.housemate.entitlement;

/**
 * AccessTokenVisitor class is an implementation of InventoryVisitor Interface. 
 * AccessTokenVisitor associated with an AccessToken and visit user who has a given AccessToken.
 *
 * @author Xin Cheng
 */
public class AccessTokenVisitor implements InventoryVisitor{
	AccessToken accessToken;
	public void visit(User user){
		this.accessToken = user.getAccessToken();
	}
	
    /**
     * Public method that checks if a user matches a given AccessToken. Returns true if matches.
     *
     * @param accessToken target token to match
     * @return boolean true if a user matches the input AccessToken, false otherwise.
     */
	public boolean checkAccessToken(AccessToken accessToken){
		return this.accessToken.getID().equals(accessToken.getID());
	}
}
