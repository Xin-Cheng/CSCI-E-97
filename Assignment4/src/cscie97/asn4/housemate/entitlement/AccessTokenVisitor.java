package cscie97.asn4.housemate.entitlement;

public class AccessTokenVisitor implements InventoryVisitor{
	AccessToken accessToken;
	public void visit(User user){
		this.accessToken = user.getAccessToken();
	}
	public boolean checkAccessToken(AccessToken accessToken){
		return this.accessToken.getID().equals(accessToken.getID());
	}
}
