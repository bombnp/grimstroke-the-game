package exception;

/**
 * The InvalidTowerException is thrown when a tower type is incorrectly selected.
 */
public class InvalidTowerException extends Exception {
    /**
     * The constructor of the InvalidTowerException.
     * @param message The message explaining the exception.
     */
    public InvalidTowerException(String message) {
        super(message);
    }
}
