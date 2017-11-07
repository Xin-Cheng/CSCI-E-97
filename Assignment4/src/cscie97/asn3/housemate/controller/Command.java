package cscie97.asn3.housemate.controller;

import cscie97.asn2.housemate.model.ObjectNotFoundException;

/**
 * The Command Interface is responsible for declaring interface for all commands.
 * A command is invoked through its execute() method, which ask HouseMateModeService to perform an action.
 *
 * @author Xin Cheng
 */
public interface Command {
	public void execute() throws ObjectNotFoundException;
}
