import java.math.BigInteger;

public class CeaserCipher {
    public static BigInteger factorial(long n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(new BigInteger(String.valueOf(i)));
        }
        return result;
    }
    public static String encrypt(String p, String key) {
        StringBuilder s= new StringBuilder();
        p = p.toLowerCase();
        for(int i=0;i<p.length();i++){
            if (p.charAt(i)>='a' && p.charAt(i)<='z')
                s.append((char) ((p.charAt(i) - 'a' + Integer.parseInt(key)) % 26 + 'A'));
            else
                s.append(p.charAt(i));
        }
        return s.toString();
    }

    public static String decrypt(String c, String key) {

        StringBuilder p = new StringBuilder();
        c = c.toLowerCase();
        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i)>='a' && c.charAt(i)<='z'){
                int val = c.charAt(i)-'a'-Integer.parseInt(key);
                p.append((char) ((val < 0 ? val + 26 : val) % 26 + 'a'));
            }
            else
                p.append(c.charAt(i));
        }

        return p.toString();
    }
}
