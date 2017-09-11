package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Importer {
	public void importTripleFile(String fileName) throws ImportException {
		KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
            	try {
            		String[] triple = parse(line);
            		knowledgeGraph.importTriple(triple[0], triple[1], triple[2]);
            	} catch (ImportException ie) {
					System.out.println(ie.getMessage());
				}
            }   
            bufferedReader.close();  
		} catch(FileNotFoundException ex) { /* FileReader exception */
			throw new ImportException(ex.getMessage());
		} catch(IOException ioex) { /* readline exception */
			throw new ImportException(ioex.getMessage());
		}
	}
	
	/* Help function:Parse individual line and throw line parse exception */
	private String[] parse(String line) throws ImportException {
		/* Trim leading and trailing whitespace. */
		String cleanLine = line.trim();
		String[] triple = cleanLine.split("\\s+");
		if(triple.length != 3)
			throw new ImportException("Not a triple.");
		if(!triple[2].substring(triple[2].length() - 1).equals("."))
			throw new ImportException("Missing a terminator.");
		
		triple[triple.length - 1] = triple[triple.length - 1].substring(0, triple[triple.length - 1].length() - 1); /* Remove the terminator. */
		for (String string : triple) {
			System.out.print(string + "+");
		}
		System.out.println();
		return triple;
	}
}
