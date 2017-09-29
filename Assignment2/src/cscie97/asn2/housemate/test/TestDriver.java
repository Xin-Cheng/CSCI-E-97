package cscie97.asn2.housemate.test;

import cscie97.asn2.housemate.model.CommandException;
import cscie97.asn2.housemate.model.CommandLineInterface;

public class TestDriver {
	public static void main(String [] args)
	{	
		String fileName = "housesetup.txt";
		CommandLineInterface commandLineInterface = new CommandLineInterface();
		try {
			commandLineInterface.importFile(fileName);
		} catch (CommandException ce) {
			System.out.println(ce.getMessage());
		}
		
	}
}
