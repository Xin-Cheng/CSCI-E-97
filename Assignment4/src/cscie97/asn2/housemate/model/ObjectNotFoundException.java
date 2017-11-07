package cscie97.asn2.housemate.model;

/**
 * This checked exception is thrown when an object cannot be found
 *
 * @author  Xin
 */
public class ObjectNotFoundException extends Exception {
	public ObjectNotFoundException(String message) {
		super("Object Not Found Exception: " + message);
	}
}
