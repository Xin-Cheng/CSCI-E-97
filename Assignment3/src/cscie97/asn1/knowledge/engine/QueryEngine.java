package cscie97.asn1.knowledge.engine;

import java.util.Set;

public class QueryEngine {
	public void executeQuery(String query){
		/* Clean query string.*/
		KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
		
		String cleanQuery = query.trim();
		String[] tripleQuery = cleanQuery.split("\\s+");
		
		tripleQuery[tripleQuery.length - 1] = tripleQuery[tripleQuery.length - 1].substring(0, tripleQuery[tripleQuery.length - 1].length() - 1); /* Remove the terminator. */

		Set<Triple> queryResult = knowledgeGraph.executeQuery(tripleQuery[0], tripleQuery[1], tripleQuery[2]);
		if(queryResult == null)
			System.out.println(queryResult);
		else {
			for (Triple triple : queryResult) {
				System.out.println(triple.getIdentifier() + ".");
			}
			System.out.println();
		}
	}
}

