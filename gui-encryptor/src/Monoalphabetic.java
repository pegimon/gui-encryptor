public class Monoalphabetic {
    public static String encrypt(String p, String key) {
        p = p.toLowerCase();
        StringBuilder cipher = new StringBuilder();
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)>='a' && p.charAt(i)<='z'){
                cipher.append(key.charAt((int) (p.charAt(i) - 'a')));
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
