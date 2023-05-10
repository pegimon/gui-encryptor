public class Vigenere {
    public static String encrypt(String p, String key) {
        key += p;
        if(key.length()>p.length()){
            key = key.substring(0, p.length());
        }
        p = p.toLowerCase();
        key = key.toUpperCase();
        StringBuilder cipher = new StringBuilder();
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)>='a' && p.charAt(i)<='z'){
                cipher.append((char) ((p.charAt(i) + (key.charAt(i) - 'A') - 'a') % 26 + 'a'));
            }
            else
                cipher.append(p.charAt(i));
        }
        return cipher.toString();
    }

    public static String decrypt(String cipher, String key) {

        StringBuilder text = new StringBuilder();
        cipher = cipher.toLowerCase();
        key = key.toLowerCase();
        for (int i = 0; i < cipher.length(); i++) {
            if (cipher.charAt(i)>='a' && cipher.charAt(i)<='z'){
                for (int j = 0; j < key.length(); j++) {
                    if(cipher.charAt(i)==key.charAt(j)){
                        text.append((char) ('a' + j));
                        break;
                    }
                }
            }
            else
                text.append(cipher.charAt(i));
        }

        return text.toString();
    }
}
