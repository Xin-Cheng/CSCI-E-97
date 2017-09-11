package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class QueryEngine {
	public void executeQuery(String query) throws QueryEngineException {
		/* Clean query string.*/
		KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
		if(query == null)
			throw new QueryEngineException("Null query.");
		
		String cleanQuery = query.trim();
		String[] tripleQuery = cleanQuery.split("\\s+");
		
		if(tripleQuery.length != 3)
			throw new QueryEngineException("Not a well formed query.");
		if(!tripleQuery[2].substring(tripleQuery[2].length() - 1).equals("."))
			throw new QueryEngineException("Missing a terminator.");
		
		tripleQuery[tripleQuery.length - 1] = tripleQuery[tripleQuery.length - 1].substring(0, tripleQuery[tripleQuery.length - 1].length() - 1); /* Remove the terminator. */

		Set<Triple> queryResult = knowledgeGraph.executeQuery(tripleQuery[0], tripleQuery[1], tripleQuery[2]);
		if(queryResult == null)
			System.out.println(queryResult);
		else {
			System.out.println(query);
			for (Triple triple : queryResult) {
				System.out.println(triple.getIdentifier() + ".");
			}
			System.out.println();
		}
	}
	
	public void executeQueryFile(String fileName) throws QueryEngineException {
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
            	try {
            		executeQuery(line);
            	} catch (QueryEngineException qeex) {
					System.out.println(qeex.getMessage());
				}
            }   
            bufferedReader.close();  
		} catch(FileNotFoundException ex) { /* FileReader exception */
			throw new QueryEngineException(ex.getMessage());
		} catch(IOException ioex) { /* readline exception */
			throw new QueryEngineException(ioex.getMessage());
		}
	}
}
