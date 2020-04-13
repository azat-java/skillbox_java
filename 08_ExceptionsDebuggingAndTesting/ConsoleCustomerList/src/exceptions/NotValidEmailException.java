package exceptions;

public class NotValidEmailException extends RuntimeException {
    public String getMessage() {
        return "Wrong format of email! Available format example:\n" +
                "vasily.petrov@gmail.com";
    }
}