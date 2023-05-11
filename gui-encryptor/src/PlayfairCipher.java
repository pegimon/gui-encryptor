import java.util.Scanner;

public class PlayfairCipher {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a key: ");
        String key = input.nextLine().toUpperCase();
        key = key.replaceAll("[^A-Z]", "").replace("J", "I");
        System.out.print("Enter a message: ");
        String message = input.nextLine().toUpperCase();
        message = message.replaceAll("[^A-Z]", "").replace("J", "I");
        char[][] matrix = createMatrix(key);
        String encryptedMessage = encrypt(matrix, message);
        String decryptedMessage = decrypt(matrix, encryptedMessage);
        System.out.println("Original Message: " + message);
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
        input.close();
    }
    public static String encrypt(char[][] matrix, String message) {
        message = prepareMessage(message);
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < message.length(); i += 2) {
            char c1 = message.charAt(i);
            char c2 = message.charAt(i + 1);
            int[] pos1 = findPosition(matrix, c1);
            int[] pos2 = findPosition(matrix, c2);
            if (pos1[0] == pos2[0]) {
                int j1 = (pos1[1] + 1) % 5;
                int j2 = (pos2[1] + 1) % 5;
                ciphertext.append(matrix[pos1[0]][j1]).append(matrix[pos2[0]][j2]);
            } else if (pos1[1] == pos2[1]) {
                int i1 = (pos1[0] + 1) % 5;
                int i2 = (pos2[0] + 1) % 5;
                ciphertext.append(matrix[i1][pos1[1]]).append(matrix[i2][pos2[1]]);
            } else {
                ciphertext.append(matrix[pos1[0]][pos2[1]]).append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(char[][] matrix, String ciphertext) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char c1 = ciphertext.charAt(i);
            char c2 = ciphertext.charAt(i + 1);
            int[] pos1 = findPosition(matrix, c1);
            int[] pos2 = findPosition(matrix, c2);
            if (pos1[0] == pos2[0]) {
                int j1 = (pos1[1] + 4) % 5;
                int j2 = (pos2[1] + 4) % 5;
                message.append(matrix[pos1[0]][j1]).append(matrix[pos2[0]][j2]);
            } else if (pos1[1] == pos2[1]) {
                int i1 = (pos1[0] + 4) % 5;
                int i2 = (pos2[0] + 4) % 5;
                message.append(matrix[i1][pos1[1]]).append(matrix[i2][pos2[1]]);
            } else {
                message.append(matrix[pos1[0]][pos2[1]]).append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return message.toString();
    }

    public static char[][] createMatrix(String key) {
        char[][] matrix = new char[5][5];
        int k = 0;
        boolean[] used = new boolean[26];
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!used[c - 'A']) {
                used[c - 'A'] = true;
                matrix[k / 5][k % 5] = c;
                k++;
            }
        }
        for (int i = 0; i < 26; i++) {
            char c = (char) ('A' + i);
            if (c == 'J')
                continue;
            if (!used[c - 'A']) {
                matrix[k / 5][k % 5] = c;
                k++;
            }
        }
        return matrix;
    }

    public static int[] findPosition(char[][] matrix, char c) {
        int[] pos = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    pos[0] = i;
                    pos[1] = j;
                    break;
                }
            }
        }
        return pos;
    }

    public static String prepareMessage(String message) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c == 'J')
                c = 'I';
            if (i < message.length() - 1 && c == message.charAt(i + 1)) {
                sb.append(c).append('X');
            } else if (i == message.length() - 1 && message.length() % 2 == 1) {
                sb.append(c).append('X');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}