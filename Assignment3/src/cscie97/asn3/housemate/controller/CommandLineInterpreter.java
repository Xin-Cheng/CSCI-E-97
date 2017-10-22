package cscie97.asn3.housemate.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The CommandLineInterpreter class is responsible for reading command file.
 *
 * @author Xin Cheng
 */

public class CommandLineInterpreter implements ICommandLineInterpreter{
	
	/**
     * Public default constructor
     */
	public CommandLineInterpreter() { }
	
	public void importFile(String fileName) throws ImportException {
        int lineNumber = 0;
        String line = null;
        
        try {
    		FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                System.out.println(line);
            }
            bufferedReader.close();  
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        	ImportException ie = new ImportException();
            ie.setLineNumber(lineNumber);
            ie.setErrorContent(e.getMessage());
            throw ie;
        } catch (IOException e) {
            e.printStackTrace();
            ImportException ie = new ImportException();
            ie.setFilename(fileName);
            ie.setErrorContent(e.getMessage());
            throw ie;
        }
    }
}
