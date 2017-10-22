package cscie97.asn2.housemate.test;

import cscie97.asn2.housemate.model.CommandException;
import cscie97.asn2.housemate.model.CommandLineInterface;

/**
 * Test Driver class to invoke the command line interface class.
 *
 * @author Xin
 */
public class TestDriver {
	public static void main(String [] args)
	{	
		String fileName = args[0];
		CommandLineInterface commandLineInterface = new CommandLineInterface();
		try {
			commandLineInterface.importFile(fileName);
		} catch (CommandException ce) {
			System.out.println(ce.getMessage());
		}
	}
}
