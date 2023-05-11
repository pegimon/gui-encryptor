import java.util.HashMap;

public class MonoalphabeticCipher {
    private final HashMap<Character, Character> encryptionMap;
    private final HashMap<Character, Character> decryptionMap;

    public MonoalphabeticCipher(String key) {
        encryptionMap = new HashMap<>();
        decryptionMap = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            char plainChar = (char) ('a' + i);
            char cipherChar = key.charAt(i);
            encryptionMap.put(plainChar, cipherChar);
            decryptionMap.put(cipherChar, plainChar);
        }
    }

    public String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            char encryptedChar = encryptionMap.getOrDefault(Character.toLowerCase(c), c);
            ciphertext.append(Character.isUpperCase(c) ? Character.toUpperCase(encryptedChar) : encryptedChar);
        }
        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            char decryptedChar = decryptionMap.getOrDefault(Character.toLowerCase(c), c);
            plaintext.append(Character.isUpperCase(c) ? Character.toUpperCase(decryptedChar) : decryptedChar);
        }
        return plaintext.toString();
    }
}
