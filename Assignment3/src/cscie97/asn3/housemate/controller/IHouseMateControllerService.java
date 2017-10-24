package cscie97.asn3.housemate.controller;

/**
 * The IHouseMateControllerService Interface is responsible for declaring interface for HouseMateControllerService.
 * It takes a command that tells HouseMateControllerService the corresponding action to take.
 *
 * @author Xin Cheng
 */

public interface IHouseMateControllerService {
	public void update(String command);
}
