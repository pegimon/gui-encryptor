public class Playfair {
    static char [][] matrix = new char[5][5];
    String subcipher(int i1,int j1,int i2,int j2){
        String subcipher = "";
        if (i1 == i2){
            subcipher += matrix[i1][(j1+1)%5];
            subcipher += matrix[i2][(j2+1)%5];
        }
        else if (j1 == j2) {
            subcipher += matrix[(i1+1)%5][j1];
            subcipher += matrix[(i2+1)%5][j2];
        }
        else {
            subcipher += matrix[i1][j2];
            subcipher += matrix[i2][j1];
        }
        return subcipher;
    }
    String subText(int i1,int j1,int i2,int j2){
        String subcipher = "";
        if (i1 == i2){
            if(j1-1<0)j1=5;
            if(j2-1<0)j2=5;
            subcipher += matrix[i1][j1-1];
            subcipher += matrix[i2][j2-1];
        }
        else if (j1 == j2) {
            if(i1-1<0)i1=5;
            if(i2-1<0)i2=5;
            subcipher += matrix[i1-1][j1];
            subcipher += matrix[i2-1][j2];
        }
        else {
            subcipher += matrix[i1][j2];
            subcipher += matrix[i2][j1];
        }
        return subcipher;
    }
    public String encrypt(String p, String key) {
        String s = "";
        for(int i=0;i<26;i++){
            char c = (char)('a'+i);
            boolean x = true;
            for(int j=0;j<key.length();j++){
                if(c == key.charAt(j) || c == 'j')
                    x = false;
            }
            if (x)
                s += c;
        }

        int row = 0,col = 0,limit=0;
        for(int i=0;i<(key.length()%5==0?key.length()/5:key.length()/5+1);i++){
            for(int j = 0;j<5;j++){
                if(limit>=key.length())break;
                matrix [i][j] = key.charAt(limit);
                limit++;
                col=(col+1)%5;
            }
            if(limit<key.length())
                row++;
        }
        int idx = 0;
        while (row < 5){
            while (col < 5){
                matrix [row][col] = s.charAt(idx);
                idx++;
                col++;
            }
            col = 0;
            row++;
        }
        if (p.length()%2==1){
            p+='x';
        }
        StringBuilder output= new StringBuilder();
        for (int i = 0; i < p.length(); i+=2){
            int i1=0,i2=0,j1=0,j2=0;
            for (int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    if(matrix[j][k]==p.charAt(i)){
                        i1=j;j1=k;
                    }else if(matrix[j][k]==p.charAt(i+1)){
                        i2=j;j2=k;
                    }
                }
            }
            output.append(subcipher(i1, j1, i2, j2));
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(matrix[i][j]+" ");
            }
        }
        return output.toString();
    }

    public String decrypt(String p, String key) {
        StringBuilder s = new StringBuilder();
        for(int i=0;i<26;i++){
            char c = (char)('a'+i);
            boolean x = true;
            for(int j=0;j<key.length();j++){
                if(c == key.charAt(j) || c == 'j')
                    x = false;
            }
            if (x)
                s.append(c);
        }

        int row = 0,col = 0,limit=0;
        for(int i=0;i<(key.length()%5==0?key.length()/5:key.length()/5+1);i++){
            for(int j = 0;j<5;j++){
                if(limit>=key.length())break;
                matrix [i][j] = key.charAt(limit);
                limit++;
                col=(col+1)%5;
            }
            if(limit<key.length())
                row++;
        }
        int idx = 0;
        while (row < 5){
            while (col < 5){
                matrix [row][col] = s.charAt(idx);
                idx++;
                col++;
            }
            col = 0;
            row++;
        }
        if (p.length()%2==1){
            p+='x';
        }
        StringBuilder output= new StringBuilder();
        for (int i = 0; i < p.length(); i+=2){
            int i1=0,i2=0,j1=0,j2=0;
            for (int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    if(matrix[j][k]==p.charAt(i)){
                        i1=j;j1=k;
                    }else if(matrix[j][k]==p.charAt(i+1)){
                        i2=j;j2=k;
                    }
                }
            }
            String ci = subText(i2,j2,i1,j1);
            output.append(ci.charAt(1)).append(ci.charAt(0));
        }
        if(output.charAt(output.length()-1)=='x'){
            output = new StringBuilder(output.substring(0, output.length() - 1));
        }
        return output.toString();
    }
}
