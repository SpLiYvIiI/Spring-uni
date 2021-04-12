package ge.tsu.lecture4.exercise.student.util;

public class RecordAlreadyExistsException extends Exception{
    public RecordAlreadyExistsException() {
    }

    public RecordAlreadyExistsException(String message) {
        super(message);
    }

    public RecordAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
