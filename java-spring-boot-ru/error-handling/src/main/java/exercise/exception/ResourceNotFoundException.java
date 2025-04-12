package exercise.exception;

// BEGIN

// Имя класса исключения не принципиально
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
// END
