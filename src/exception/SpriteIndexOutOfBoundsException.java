package exception;

public class SpriteIndexOutOfBoundsException extends Exception {
    public String message;

    public SpriteIndexOutOfBoundsException(String message) {
        super(message);
        this.message = message;
    }
}
