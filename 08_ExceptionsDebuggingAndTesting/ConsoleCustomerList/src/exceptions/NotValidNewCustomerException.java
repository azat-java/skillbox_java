package exceptions;

public class NotValidNewCustomerException extends RuntimeException {
    public String getMessage() {
        return "Wrong command! Available command example:\n" +
                "add Василий Петров vasily.petrov@gmail.com +79215637722";
    }
}