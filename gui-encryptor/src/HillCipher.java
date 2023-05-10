public class HillCipher {
    public static String encrypt(String plaintext, String key) {
        int blockSize = (int) Math.sqrt(key.length());
        int[][] keyMatrix = getKeyMatrix(key, blockSize);
        String paddedText = padPlaintext(plaintext, blockSize);
        StringBuilder ciphertext = new StringBuilder();

        // Encrypt each block of the plaintext using the key matrix
        for (int i = 0; i < paddedText.length(); i += blockSize) {
            String block = paddedText.substring(i, i + blockSize);
            int[] plaintextVector = getVector(block);
            int[] ciphertextVector = multiplyMatrix(keyMatrix, plaintextVector);
            String encryptedBlock = getBlock(ciphertextVector);
            ciphertext.append(encryptedBlock);
        }

        return ciphertext.toString();
    }

    // Helper methods

    public static int[][] getKeyMatrix(String key, int blockSize) {
        int[][] matrix = new int[blockSize][blockSize];
        int index = 0;
        for (int i = 0; i < blockSize; i++) {
            for (int j = 0; j < blockSize; j++) {
                char c = key.charAt(index);
                matrix[i][j] = c - 'A';
                index++;
            }
        }
        return matrix;
    }

    public static String padPlaintext(String plaintext, int blockSize) {
        StringBuilder sb = new StringBuilder(plaintext.toUpperCase());
        int paddingLength = blockSize - (sb.length() % blockSize);
        for (int i = 0; i < paddingLength; i++) {
            sb.append('X');
        }
        return sb.toString();
    }

    public static int[] getVector(String block) {
        int[] vector = new int[block.length()];
        for (int i = 0; i < block.length(); i++) {
            char c = block.charAt(i);
            vector[i] = c - 'A';
        }
        return vector;
    }

    public static int[] multiplyMatrix(int[][] matrix, int[] vector) {
        int[] result = new int[vector.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
            result[i] %= 26;
        }
        return result;
    }

    public static String getBlock(int[] vector) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vector.length; i++) {
            int value = vector[i] % 26;
            sb.append((char) (value + 'A'));
        }
        return sb.toString();
    }


    public static String decrypt(String cipher, String key) {
        cipher = cipher.replaceAll("\\s+", ""); // remove whitespace from ciphertext
        int keySize = (int) Math.sqrt(key.length());
        int[][] keyMatrix = getKeyMatrix(key, keySize);
        int determinant = determinant(keyMatrix);
        int inverseDeterminant = modInverse(determinant, 26);
        if (determinant == 0 || inverseDeterminant == -1) {
            return "Error: Invalid key";
        }
        int[][] inverseKeyMatrix = invertKeyMatrix(keyMatrix, inverseDeterminant);
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < cipher.length(); i += keySize) {
            String block = cipher.substring(i, i + keySize);
            int[][] blockMatrix = getBlock(block, keySize);
            int[][] decryptedBlockMatrix = multiplyMatrix(blockMatrix, inverseKeyMatrix);
            String decryptedBlock = getBlockString(decryptedBlockMatrix);
            decryptedText.append(decryptedBlock);
        }
        return decryptedText.toString();
    }

    // Multiplies two matrices modulo 26 (for decryption)
    public static int[][] multiplyMatrix(int[][] mat1, int[][] mat2) {
        int size = mat1.length;
        int[][] result = new int[size][size];
        int sum;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum = 0;
                for (int k = 0; k < size; k++) {
                    sum += mat1[i][k] * mat2[k][j];
                }
                result[i][j] = sum % 26;
            }
        }
        return result;
    }

    // Calculates the determinant of a 2x2 matrix
    public static int determinant(int[][] matrix) {
        return (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0] + 26) % 26;
    }

    // Converts a string to a matrix block of a specified size
    public static int[][] getBlock(String str, int size) {
        int[][] block = new int[size][size];
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (index < str.length()) {
                    block[i][j] = str.charAt(index) - 'A';
                    index++;
                } else {
                    block[i][j] = 0; // Padding with zeros if necessary
                }
            }
        }
        return block;
    }

    // Calculates the modular multiplicative inverse of a number a modulo m using the extended Euclidean algorithm
    public static int modInverse(int a, int m) {
        a = (a % m + m) % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }

    // Inverts a key matrix using the modular multiplicative inverse of its determinant
    public static int[][] invertKeyMatrix(int[][] matrix, int inverseDeterminant) {
        int size = matrix.length;
        int[][] inverseMatrix = new int[size][size];
        inverseMatrix[0][0] = (matrix[1][1] * inverseDeterminant) % 26;
        inverseMatrix[0][1] = (-matrix[0][1] * inverseDeterminant) % 26;
        inverseMatrix[1][0] = (-matrix[1][0] * inverseDeterminant) % 26;
        inverseMatrix[1][1] = (matrix[0][0] * inverseDeterminant) % 26;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (inverseMatrix[i][j] < 0) {
                    inverseMatrix[i][j] += 26;
                }
            }
        }
        return inverseMatrix;
    }

    // Converts a matrix back into a string
    public static String getBlockString(int[][] matrix) {
        StringBuilder block = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                block.append((char) (matrix[i][j] + 'A'));
            }
        }
        return block.toString();
    }

}
