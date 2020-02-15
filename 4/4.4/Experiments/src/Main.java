public class Main {
    public static void main(String[] args) {
        String alphabetEn = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetRu = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        Main codes = new Main();
        codes.printCode(alphabetEn);
        codes.printCode(alphabetRu);
    }

    public void printCode(String string) {
        for (int i = 0; i < string.length(); i++) {
            char letter = string.charAt(i);
            System.out.println(letter + " - " + (int) letter);
        }
    }
}
