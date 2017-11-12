package cscie97.asn4.housemate.entitlement;

/**
 * Instances of this checked exception class are thrown by the CommandLineInterpreter 
 * when error occurs processing the authentication data file.
 *
 * @author Xin Cheng
 */
public class AuthenticationSetupException extends Exception{
	private String filename;
    private int lineNumber;
    private String errorContent;

    public AuthenticationSetupException() {

    }

    /**
     * Filename of file being read when exception occurred.
     */
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Line number of file that caused the exception.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Content that caused the exception.
     */
    public String getErrorContent() {
        return errorContent;
    }

    public void setErrorContent(String errorContent) {
        this.errorContent = errorContent;
    }

    @Override
    public String toString() {
        return "AuthenticationSetupException{" +
                "filename='" + filename + '\'' +
                ", lineNumber=" + lineNumber +
                ", errorContent='" + errorContent + '\'' +
                '}';
    }
}
