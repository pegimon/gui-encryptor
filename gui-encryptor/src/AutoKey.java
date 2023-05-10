import java.util.Random;

public class AutoKey {
    public static String encrypt(String plaintext) {
        Random rnd = new Random();
        rnd.setSeed(5);
        String key = "";
        while (key.length() < plaintext.length()){
            key +=(char) (Math.abs(rnd.nextInt())%26 + 'a');
        }
        String ciphertext = "";
        for(int i=0; i < plaintext.length(); i++){
            ciphertext += (char)((plaintext.charAt(i) ^ key.charAt(i))+'A');
        }
        return ciphertext;
    }

    public static String decrypt(String ciphertext) {
        Random rnd = new Random();
        rnd.setSeed(5);
        String key = "";
        while (key.length() < ciphertext.length()){
            key +=(char) (Math.abs(rnd.nextInt())%26 + 'a');
        }
        String plaintext = "";
        for(int i=0; i < ciphertext.length(); i++){
            plaintext += (char)((ciphertext.charAt(i) - 'A') ^ key.charAt(i));
        }
        return plaintext;
    }

}
