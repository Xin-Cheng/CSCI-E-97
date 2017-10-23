package cscie97.asn3.housemate.controller;

import cscie97.asn2.housemate.model.ObjectNotFoundException;

public interface Command {
	public void execute() throws ObjectNotFoundException;
}
