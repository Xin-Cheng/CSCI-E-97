package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QueryEngine {
	public void executeQuery(String query) throws QueryEngineException {
		/* Clean query string.*/
		if(query == null)
			throw new QueryEngineException("Null query.");
		
		String cleanQuery = query.trim();
		String[] tripleQuery = cleanQuery.split("\\s+");

		if(tripleQuery.length != 3)
			throw new QueryEngineException("Not a well formed query.");
		if(!tripleQuery[2].substring(tripleQuery[2].length() - 1).equals("."))
			throw new QueryEngineException("Missing a terminator.");
		for (String string : tripleQuery) {
			System.out.print(string + "+");
		}
		System.out.println();
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
