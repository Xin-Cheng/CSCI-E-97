package cscie97.asn4.housemate.entitlement;

/**
 *  Element is an interface that accepts a visitor to traverse itself.
 *
 * @author Xin Cheng
 */
public interface Element {
	void accept(InventoryVisitor visitor);
}
