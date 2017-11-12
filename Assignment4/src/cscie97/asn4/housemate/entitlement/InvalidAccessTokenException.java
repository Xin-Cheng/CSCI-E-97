package cscie97.asn4.housemate.entitlement;

/**
 * Instances of this checked exception class are thrown by the EntitlementService 
 * when an AccessToken is inactive.
 *
 * @author Xin Cheng
 */
public class InvalidAccessTokenException extends Exception{
	private String filename;
    private int lineNumber;
    private String userName;

    public InvalidAccessTokenException() {

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
     * User that trying to use the invalid accessToken.
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "InvalidAccessTokenException{" +
                "filename='" + filename + '\'' +
                ", lineNumber=" + lineNumber +
                ", errorContent='" + "The access token of user: " + userName + " is no longer valid!" + '\'' +
                '}';
    }
}
