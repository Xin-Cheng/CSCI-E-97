package cscie97.asn1.knowledge.engine;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class KnowledgeGraph {
	private static KnowledgeGraph instance;
	private Map<String, Node> nodeMap;
	private Map<String, Predicate> predicateMap;
	private Map<String, Triple> tripleMap;
	private Map<String, Set<Triple>> queryMapSet;
	
	private KnowledgeGraph() {
		nodeMap = new HashMap<>();
		predicateMap = new HashMap<>();
		tripleMap = new HashMap<>();
		queryMapSet = new HashMap<>();		
	}
	
	public void importTriple(String subject, String predicate, String object) {
		Node sNode = new Node(subject);
		Predicate p = new Predicate(predicate);
		Node oNode = new Node(object);
		Triple triple = new Triple(sNode, p, oNode);
		if(!nodeMap.containsKey(sNode.getIdentifier()))
			nodeMap.put(sNode.getIdentifier(), sNode);
		if(!nodeMap.containsKey(oNode.getIdentifier()))
			nodeMap.put(oNode.getIdentifier(), oNode);
		if(!predicateMap.containsKey(p.getIdentifier()))
			predicateMap.put(p.getIdentifier(), p);
		if(!tripleMap.containsKey(triple.getIdentifier()))
			tripleMap.put(triple.getIdentifier(), triple);
		
		
	}
	
	public Set<Triple> executeQuery(String subject, String predicate, String object) {
		Set<Triple> queryResult = null;
		return queryResult;
	}
	
	public static KnowledgeGraph getInstance() {
		if(instance == null)
			instance = new KnowledgeGraph();
		return instance;
	}

	public Node getNode(String identifier){
		if(!nodeMap.containsKey(identifier))
			nodeMap.put(identifier, new Node(identifier));
		return nodeMap.get(identifier);
	}
	
	public Predicate getPredicate(String identifier) {
		if(!predicateMap.containsKey(identifier))
			predicateMap.put(identifier, new Predicate(identifier));
		return predicateMap.get(identifier);
	}
	
	public Triple getTriple(Node subject, Predicate predicate, Node object) {
		Triple triple = new Triple(subject, predicate, object);
		if(!tripleMap.containsKey(triple.getIdentifier()))
			tripleMap.put(triple.getIdentifier(), triple);
		return tripleMap.get(triple.getIdentifier());
	}
}
