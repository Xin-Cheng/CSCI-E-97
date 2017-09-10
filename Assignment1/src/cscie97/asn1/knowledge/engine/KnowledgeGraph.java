package cscie97.asn1.knowledge.engine;

import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

public class KnowledgeGraph {
	private Map<String, Node> nodeMap;
	private Map<String, Predicate> predicateMap;
	private Map<String, Triple> tripleMap;
	private Map<String, Set<Triple>> queryMapSet;
	
	public void importTriple(String subject, String predicate, String object) {
		
	}
	
	public Set<Triple> executeQuery(String subject, String predicate, String object) {
		Set<Triple> queryResult = null;
		return queryResult;
	}
	
	public KnowledgeGraph getInstance(String identifier) {
		return this;
	}

	public Node getNode(String identifier){
		Node node = null;
		return node;
	}
	
	public Predicate getPredicate(String identifier) {
		Predicate predicate = null;
		return predicate;
	}
	
	public Triple getTriple(Node subject, Predicate predicate, Node object) {
		Triple triple = null;
		return triple;
	}
}
