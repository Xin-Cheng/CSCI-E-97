package cscie97.asn4.housemate.entitlement;

public class CredentialVisitior implements InventoryVisitor{
	Credential credential;
	public void visit(User user){
		this.credential = user.getCredential();
	}
	public boolean checkCredential(String credential){
		String inputCredential = credential;
		if(!credential.startsWith("--"))
			inputCredential = String.valueOf(credential.hashCode());
		return this.credential.getValue().equals(inputCredential);
	}
}
