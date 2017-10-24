package cscie97.asn3.housemate.controller;

/**
 * The ICommandLineInterpreter Interface is responsible for declaring interface for CommandLineInterpreter.
 * It takes a file name as parameter and throw ImportException when error happens during processing the file.
 *
 * @author Xin Cheng
 */
public interface ICommandLineInterpreter {
	public void importFile(String fileName) throws ImportException;
}
