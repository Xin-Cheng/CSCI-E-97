package cscie97.asn2.housemate.model;

public class CommandException extends Exception{
	public CommandException(String message) {
		super("Command Exception: " + message);
	}
}
