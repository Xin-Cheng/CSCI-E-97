package cscie97.asn4.housemate.entitlement;

public interface Element {
	void accept(InventoryVisitor visitor);
}
