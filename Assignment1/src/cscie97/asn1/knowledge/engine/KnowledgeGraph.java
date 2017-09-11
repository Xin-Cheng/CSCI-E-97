package cscie97.asn1.knowledge.engine;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;

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
		
		/* Store triple to a list for query generation. */
		List<String> words = new ArrayList<>(Arrays.asList(subject, predicate, object));
		List<List<String>> queries = new ArrayList<>();
		queries.add(new ArrayList<>());
		preCompute(words, queries);
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
	
	/* Help function: generate all possible queries of a single triple and update queryMapSet. */
	private void preCompute(List<String> words, List<List<String>> queries){
		if(queries.get(0).size() == 3)
			return;
		
		List<List<String>> result = new ArrayList<>();
		for (int i = 0; i < queries.size(); i++) {
			List<String> addWord = new ArrayList<>(queries.get(i));
			addWord.add(words.get(0));
			List<String> addSymbol = new ArrayList<>(queries.get(i));
			addSymbol.add("?");
			result.add(addWord);
			result.add(addSymbol);
			System.out.println(Arrays.toString(addWord.toArray()));
			System.out.println(Arrays.toString(addSymbol.toArray()));
		}
		preCompute(words.subList(1, words.size()), result);
	}
}
