package exceptions;

public class DestinationInSourceException extends RuntimeException {
    public String getMessage() {
        return "Папка назначения не может находиться в исходной папке. Введите адрес другой (новой) папки";
    }
}