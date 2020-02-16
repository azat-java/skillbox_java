public class Main {
    public static void main(String[] args) {
        String[][] x = new String[7][7];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                if (i == j || i + j == 6) {
                    x[i][j] = "x";
                } else {
                    x[i][j] = " ";
                }
                System.out.print(x[i][j]);
            }
            System.out.println();
        }
    }
}
