package hello.jdbc.repository.ex;

public class MuDuplicateKeyException extends MyDbException{
    public MuDuplicateKeyException() {
    }

    public MuDuplicateKeyException(String message) {
        super(message);
    }

    public MuDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MuDuplicateKeyException(Throwable cause) {
        super(cause);
    }
}
