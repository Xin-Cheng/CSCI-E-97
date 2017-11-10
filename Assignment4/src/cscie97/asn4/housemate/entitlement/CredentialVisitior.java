package cscie97.asn4.housemate.entitlement;

public class CredentialVisitior implements InventoryVisitor{
	Credential credential;
	public void visit(User user){
		this.credential = user.getCredential();
	}
	public String getCredentialValue(){
		return credential.getValue();
	}
}
