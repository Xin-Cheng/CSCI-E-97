package cscie97.asn1.knowledge.engine;

public class Test {
	public static void main(String [] args)
	{		
		KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
		Importer importer = new Importer();
		try {
			importer.importTripleFile("inputTriples.nt");
		} catch (ImportException ie) {
			System.out.println("Import Exception: " + ie.getMessage());
		}
		System.out.println();
		
		QueryEngine queryEngine = new QueryEngine();
		System.out.println("Query result:");
		System.out.println();
		
		try {
			queryEngine.executeQueryFile("sampleQuery.nt");
		} catch (QueryEngineException qeex) {
			System.out.println("Query Exception: " + qeex.getMessage() + "\n\n");
		}
	}
}
