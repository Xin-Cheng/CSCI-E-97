package cscie97.asn4.housemate.entitlement;

/**
 *  InventoryVisitor is an Interface that is used to traverse an Element. It provides a visit() API.
 *
 * @author Xin Cheng
 */
public interface InventoryVisitor {
	void visit(User user);
}
