package labs.pm.data;

public class ProductManagerException extends Exception {
    static final long serialVersionUID = (long) 123;

    public ProductManagerException() {
        super();
    }

    public ProductManagerException(String message) {
        super(message);
    }

    public ProductManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
