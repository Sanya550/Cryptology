package com.example.cryptology.Helper;

import java.util.LinkedHashMap;

public class HelperForLab1 {
    public static LinkedHashMap<Integer, Character> linkedHashMapForAlphabetical = new LinkedHashMap<>();
    public static final String ALPHABETICAL = "abcdefghjklmnopqrstuvwxyz";
    static final int NO_OF_CHARS = 256;

    //рефакторинг вхідного слова
    public static String refactoringWord(String word, int num) {
        String noUsedChar = removeUsedChars(ALPHABETICAL, word);
        char[] arrayString = word.toCharArray();
        for (int i = 0; i < arrayString.length - 1; i++) {
            if (arrayString[i] == arrayString[i + 1]) {
                word = new StringBuilder(word).insert(i + 1, noUsedChar.charAt(noUsedChar.length() - 1)).toString();
                noUsedChar = new StringBuilder(noUsedChar).deleteCharAt(noUsedChar.length() - 1).toString();
            }
        }
        if (word.length() % num != 0) {
            word = new StringBuilder(word).insert(word.length(), noUsedChar.charAt(0)).toString();
        }
        return word;
    }

    public static String addingLetterToWordIfNeed(String word, int num) {
        int counter = ALPHABETICAL.length() - 1;
        if (word.length() % num != 0) {
            word = new StringBuilder(word).insert(word.length(), ALPHABETICAL.charAt(counter)).toString();
            counter--;
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

    public static void fillingMatrix() {
        linkedHashMapForAlphabetical.put(0, 'a');
        linkedHashMapForAlphabetical.put(1, 'b');
        linkedHashMapForAlphabetical.put(2, 'c');
        linkedHashMapForAlphabetical.put(3, 'd');
        linkedHashMapForAlphabetical.put(4, 'e');
        linkedHashMapForAlphabetical.put(5, 'f');
        linkedHashMapForAlphabetical.put(6, 'g');
        linkedHashMapForAlphabetical.put(7, 'h');
        linkedHashMapForAlphabetical.put(8, 'i');
        linkedHashMapForAlphabetical.put(9, 'j');
        linkedHashMapForAlphabetical.put(10, 'k');
        linkedHashMapForAlphabetical.put(11, 'l');
        linkedHashMapForAlphabetical.put(12, 'm');
        linkedHashMapForAlphabetical.put(13, 'n');
        linkedHashMapForAlphabetical.put(14, 'o');
        linkedHashMapForAlphabetical.put(15, 'p');
        linkedHashMapForAlphabetical.put(16, 'q');
        linkedHashMapForAlphabetical.put(17, 'r');
        linkedHashMapForAlphabetical.put(18, 's');
        linkedHashMapForAlphabetical.put(19, 't');
        linkedHashMapForAlphabetical.put(20, 'u');
        linkedHashMapForAlphabetical.put(21, 'v');
        linkedHashMapForAlphabetical.put(22, 'w');
        linkedHashMapForAlphabetical.put(23, 'x');
        linkedHashMapForAlphabetical.put(24, 'y');
        linkedHashMapForAlphabetical.put(25, 'z');
    }
}
