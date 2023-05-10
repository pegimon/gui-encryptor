public class RailFence {
    public static String encrypt(String plaintext, int key) {
        int len = plaintext.length();
        char[][] rail = new char[key][len];
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                rail[i][j] = '\n';
            }
        }
        boolean dir_down = false;
        int row = 0, col = 0;
        for (int i = 0; i < len; i++) {
            if (row == 0 || row == key - 1)
                dir_down = !dir_down;
            rail[row][col++] = plaintext.charAt(i);
            row = dir_down ? row + 1 : row - 1;
        }
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                if (rail[i][j] != '\n')
                    ciphertext.append(rail[i][j]);
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int key) {
        int len = ciphertext.length();
        char[][] rail = new char[key][len];
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                rail[i][j] = '\n';
            }
        }
        boolean dir_down = false;
        int row = 0, col = 0;
        for (int i = 0; i < len; i++) {
            if (row == 0)
                dir_down = true;
            if (row == key - 1)
                dir_down = false;
            rail[row][col++] = '*';
            row = dir_down ? row + 1 : row - 1;
        }
        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                if (rail[i][j] == '*' && index < len)
                    rail[i][j] = ciphertext.charAt(index++);
            }
        }
        StringBuilder plaintext = new StringBuilder();
        dir_down = false;
        row = 0;
        col = 0;
        for (int i = 0; i < len; i++) {
            if (row == 0)
                dir_down = true;
            if (row == key - 1)
                dir_down = false;
            if (rail[row][col] != '*')
                plaintext.append(rail[row][col++]);
            row = dir_down ? row + 1 : row - 1;
        }
        return plaintext.toString();
    }

}
