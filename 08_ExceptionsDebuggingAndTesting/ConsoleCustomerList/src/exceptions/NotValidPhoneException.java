package exceptions;

public class NotValidPhoneException extends RuntimeException {
    public String getMessage() {
        return "Wrong format of phone! Available format example:\n" +
                "+79215637722";
    }
}