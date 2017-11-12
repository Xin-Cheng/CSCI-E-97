package cscie97.asn4.housemate.entitlement;

/**
 * Instances of this checked exception class are thrown by the EntitlementService 
 * when object can not be found in the system.
 *
 * @author Xin Cheng
 */
public class EntityNotFoundException extends Exception{
	private String filename;
    private int lineNumber;
    private String entityName;
    
    public EntityNotFoundException() {

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
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public String toString() {
        return "EntityNotFoundException{" +
                "filename='" + filename + '\'' +
                ", lineNumber=" + lineNumber +
                ", errorContent='" + "The entity: " + entityName + " cannot be found in the system!" + '\'' +
                '}';
    }
}
