package cscie97.asn1.knowledge.engine;

public class QueryEngineException extends Exception {
	public QueryEngineException(String message) {
		super("Query Exception: " + message);
	}
}
