package cscie97.asn4.housemate.entitlement;

/**
 * Instances of this checked exception class are thrown by the EntitlementService 
 * when a credential does not match a user.
 *
 * @author Xin Cheng
 */
public class InvalidCredentialException extends Exception{
	private String filename;
    private int lineNumber;
    private String userId;
    
    public InvalidCredentialException() {

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
     * User that cause this exception
     */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userName) {
        this.userId = userName;
    }
    
    @Override
    public String toString() {
        return "InvalidCredentialException{" +
                "filename='" + filename + '\'' +
                ", lineNumber=" + lineNumber +
                ", errorContent='" + "The credential dose not match user: " + userId + '\'' +
                '}';
    }
}
