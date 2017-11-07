package cscie97.asn3.housemate.controller;

/**
 * This checked exception is thrown by the CommandLineInterface class when an error is encountered processing
 * an input command file.
 *
 * @author  Xin Cheng
 */
public class ImportException extends Exception{

	private String filename;
    private int lineNumber;
    private String errorContent;

    public ImportException() {

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
        return "ImportException{" +
                "filename='" + filename + '\'' +
                ", lineNumber=" + lineNumber +
                ", errorContent='" + errorContent + '\'' +
                '}';
    }
}
