package com.example.cryptology.lab1;

import com.example.cryptology.Helper.HelperForLab1;

import java.util.*;

import static com.example.cryptology.Helper.HelperForLab1.fillingMatrix;

public class Vigenere {

    public String encryptText(String phrase, String keyWord) {
        String encryptionWord = "";
        keyWord = refactoringKeyWord(phrase, keyWord);
        List<Integer> intValueForPhrase = gettingDigitValueFromCharacter(phrase);
        List<Integer> intValueForKeyWord = gettingDigitValueFromCharacter(keyWord);
        List<Integer> encryptList = new ArrayList<>();
        for (int i = 0; i < intValueForPhrase.size(); i++) {
            encryptList.add(Math.floorMod(intValueForPhrase.get(i) + intValueForKeyWord.get(i), 26));
        }

        //беремо з мапи по ключу
        for (int i = 0; i < encryptList.size(); i++) {
            encryptionWord += HelperForLab1.linkedHashMapForAlphabetical.get(encryptList.get(i));
        }
        return encryptionWord;
    }

    public static List<Integer> gettingDigitValueFromCharacter(String text) {
        fillingMatrix();
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            for (int j = 0; j < HelperForLab1.linkedHashMapForAlphabetical.size(); j++) {
                if (a == HelperForLab1.linkedHashMapForAlphabetical.get(j)) {
                    resultList.add(j);
                    break;
                }
            }
        }
        return resultList;
    }

    public String deEncryptText(String phrase, String keyWord) {
        String deEncryptionWord = "";
        keyWord = refactoringKeyWord(phrase, keyWord);
        List<Integer> intValueForPhrase = gettingDigitValueFromCharacter(phrase);
        List<Integer> intValueForKeyWord = gettingDigitValueFromCharacter(keyWord);
        List<Integer> encryptList = new ArrayList<>();
        for (int i = 0; i < intValueForPhrase.size(); i++) {
            encryptList.add(Math.floorMod(intValueForPhrase.get(i) - intValueForKeyWord.get(i), 26));
        }

        //беремо з мапи по ключу
        for (int i = 0; i < encryptList.size(); i++) {
            deEncryptionWord += HelperForLab1.linkedHashMapForAlphabetical.get(encryptList.get(i));
        }
        return deEncryptionWord;
    }

    public static String refactoringKeyWord(String phrase, String keyWord) {
        int counter = 0;
        while (phrase.length() > keyWord.length()) {
            keyWord = new StringBuilder(keyWord).insert(keyWord.length(), keyWord.charAt(counter)).toString();
            counter++;
        }
        return keyWord;
    }
}
