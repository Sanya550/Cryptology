package com.example.cryptology.lab1;

import java.util.*;

public class Vigenere {
    public static LinkedHashMap<Integer, Character> linkedHashMapForVigenere = new LinkedHashMap<>();

    public String encryptText(String phrase, String keyWord) {
        String encryptionWord = "";
        keyWord = refactoringKeyWord(phrase, keyWord);
        List<Integer> intValueForPhrase = gettingDigitValueFromCharacter(phrase);
        List<Integer> intValueForKeyWord = gettingDigitValueFromCharacter(keyWord);
        List<Integer> encryptList = new ArrayList<>();
        for (int i = 0; i < intValueForPhrase.size(); i++) {
            encryptList.add(Math.floorMod(intValueForPhrase.get(i) + intValueForKeyWord.get(i), 26));
            //Note: for decryption will be intValueForPhrase.get(i) - intValueForKeyWord.get(i)
        }

        //беремо з мапи по ключу
        for (int i = 0; i < encryptList.size(); i++) {
            encryptionWord += linkedHashMapForVigenere.get(encryptList.get(i));
        }
        return encryptionWord;
    }

    public static List<Integer> gettingDigitValueFromCharacter(String text) {
        fillingMatrix();
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            for (int j = 0; j < linkedHashMapForVigenere.size(); j++) {
                if (a == linkedHashMapForVigenere.get(j)) {
                    resultList.add(j);
                    break;
                }
            }
        }
        return resultList;
    }

    public static String refactoringKeyWord(String phrase, String keyWord) {
        int counter = 0;
        while (phrase.length() > keyWord.length()) {
            keyWord = new StringBuilder(keyWord).insert(keyWord.length(), keyWord.charAt(counter)).toString();
            counter++;
        }
        return keyWord;
    }

    public static void fillingMatrix() {
        linkedHashMapForVigenere.put(0, 'a');
        linkedHashMapForVigenere.put(1, 'b');
        linkedHashMapForVigenere.put(2, 'c');
        linkedHashMapForVigenere.put(3, 'd');
        linkedHashMapForVigenere.put(4, 'e');
        linkedHashMapForVigenere.put(5, 'f');
        linkedHashMapForVigenere.put(6, 'g');
        linkedHashMapForVigenere.put(7, 'h');
        linkedHashMapForVigenere.put(8, 'i');
        linkedHashMapForVigenere.put(9, 'j');
        linkedHashMapForVigenere.put(10, 'k');
        linkedHashMapForVigenere.put(11, 'l');
        linkedHashMapForVigenere.put(12, 'm');
        linkedHashMapForVigenere.put(13, 'n');
        linkedHashMapForVigenere.put(14, 'o');
        linkedHashMapForVigenere.put(15, 'p');
        linkedHashMapForVigenere.put(16, 'q');
        linkedHashMapForVigenere.put(17, 'r');
        linkedHashMapForVigenere.put(18, 's');
        linkedHashMapForVigenere.put(19, 't');
        linkedHashMapForVigenere.put(20, 'u');
        linkedHashMapForVigenere.put(21, 'v');
        linkedHashMapForVigenere.put(22, 'w');
        linkedHashMapForVigenere.put(23, 'x');
        linkedHashMapForVigenere.put(24, 'y');
        linkedHashMapForVigenere.put(25, 'z');
    }
}
