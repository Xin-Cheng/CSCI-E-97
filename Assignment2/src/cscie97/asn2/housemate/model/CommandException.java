package cscie97.asn2.housemate.model;

/**
 * This checked exception is thrown when an command do not follow the command syntax
 *
 * @author  Xin
 */
public class CommandException extends Exception{
	public CommandException(String message) {
		super("Command Exception: " + message);
	}
}
