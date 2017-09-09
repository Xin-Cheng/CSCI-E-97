package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Importer {
	public void importTripleFile(String fileName) throws ImportException {
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
//                try {
//                	
//                } catch (Exception e) {
//					
//				}
//                
//                if(true) {
//                	throw new ImportException("Processing Error!");
//                }
            }   
            bufferedReader.close();  
		} catch(FileNotFoundException ex) { /* FileReader exception */
			throw new ImportException(ex.getMessage());
		} catch(IOException ioex) { /* readline exception */
			throw new ImportException(ioex.getMessage());
		}
	}
	
	/* Help function:Parse individual line and throw line parse exception */
}
