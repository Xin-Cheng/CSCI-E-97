package cscie97.asn3.housemate.controller;

public class InvalidCommandException extends Exception{
    private String command;

    /**
     * Public default constructor
     */
    public InvalidCommandException() {

    }

    /**
     * Command that caused the exception.
     */
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "InvalidCommandException{" +
                ", command='" + command + '\'' +
                '}';
    }
}
