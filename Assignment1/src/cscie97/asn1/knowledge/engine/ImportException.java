package cscie97.asn1.knowledge.engine;

public class ImportException extends Exception {
	public ImportException(String message) {
		super("Import Exception: " + message);
	}
}
