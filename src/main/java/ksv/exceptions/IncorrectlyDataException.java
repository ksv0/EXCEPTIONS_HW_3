package ksv.exceptions;

public class IncorrectlyDataException extends Exception {
    public IncorrectlyDataException() {
        super("некорректно введенные данные");
    }

    public IncorrectlyDataException(String message) {
        super("некорректно введенные данные: " + message);
    }
}
