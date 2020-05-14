package exceptions;

public class FolderNotEmptyException extends RuntimeException {
    public String getMessage() {
        return "Папка уже содержит файлы. Введите адрес пустой (новой) папки";
    }
}