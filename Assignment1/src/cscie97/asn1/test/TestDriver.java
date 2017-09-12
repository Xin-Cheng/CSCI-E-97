package cscie97.asn1.test;

import cscie97.asn1.knowledge.engine.ImportException;
import cscie97.asn1.knowledge.engine.Importer;
import cscie97.asn1.knowledge.engine.QueryEngine;
import cscie97.asn1.knowledge.engine.QueryEngineException;

public class TestDriver {
	public static void main(String [] args)
	{	
		if(args.length < 2)
			System.out.println("Invalid command line. Usage: java -cp . cscie97.asn1.test.TestDriver inputTriples.nt inputQueries.txt");
		else {
			String tripleFile = args[0];
			String queryFile = args[1];
			
			Importer importer = new Importer();
			try {
				importer.importTripleFile(tripleFile);
				
				System.out.println();
				
				QueryEngine queryEngine = new QueryEngine();
				System.out.println("Query result:");
				System.out.println();
				
				queryEngine.executeQueryFile(queryFile);
			} catch (ImportException | QueryEngineException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
