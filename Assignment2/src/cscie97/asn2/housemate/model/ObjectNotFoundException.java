package cscie97.asn2.housemate.model;

public class ObjectNotFoundException extends Exception {
	public ObjectNotFoundException(String message) {
		super("Object Not Found Exception: " + message);
	}
}
