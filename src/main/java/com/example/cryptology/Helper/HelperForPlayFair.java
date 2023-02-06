package com.example.cryptology.Helper;

public class HelperForPlayFair {
    public static final String ALPHABETICAL = "abcdefghjklmnopqrstuvwxyz";
    static final int NO_OF_CHARS = 256;

    //рефакторинг вхідного слова
    public static String refactoringWord(String word) {
        String noUsedChar = removeUsedChars(ALPHABETICAL, word);
        char[] arrayString = word.toCharArray();
        for (int i = 0; i < arrayString.length - 1; i++) {
            if (arrayString[i] == arrayString[i + 1]) {
                word = new StringBuilder(word).insert(i + 1, noUsedChar.charAt(noUsedChar.length() - 1)).toString();
                noUsedChar = new StringBuilder(noUsedChar).deleteCharAt(noUsedChar.length() - 1).toString();
            }
        }
        if (word.length() % 2 != 0) {
            word = new StringBuilder(word).insert(word.length(), noUsedChar.charAt(0)).toString();
        }
        return word;
    }

    //видалення з ALPHABETICAL існуючих літер
    public static String removeUsedChars(String str, String mask_str) {
        int count[] = getCharCountArray(mask_str);
        int ip_ind = 0, res_ind = 0;
        char arr[] = str.toCharArray();
        while (ip_ind != arr.length) {
            char temp = arr[ip_ind];
            if (count[temp] == 0) {
                arr[res_ind] = arr[ip_ind];
                res_ind++;
            }
            ip_ind++;
        }
        str = new String(arr);
        return str.substring(0, res_ind);
    }

    static int[] getCharCountArray(String str) {
        int count[] = new int[NO_OF_CHARS];
        for (int i = 0; i < str.length(); i++)
            count[str.charAt(i)]++;

        return count;
    }
}
