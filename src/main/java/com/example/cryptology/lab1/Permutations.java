package com.example.cryptology.lab1;

import com.example.cryptology.Helper.HelperForLab1;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public String encryptTextByPermutations(String text, String keyString) {
        String encryptText = "";
        var listOfKeyDigit = refactorKeyToListOfNumbers(keyString);
        text = HelperForLab1.addingLetterToWordIfNeed(text, listOfKeyDigit.size());
        int quantityOfRows = text.length() / listOfKeyDigit.size();
        char[][] textDividedByCharacters = new char[quantityOfRows][listOfKeyDigit.size()];

        //записати відкритий текст у двовимірний масив:
        int counter = 0;//temporary
        for (int i = 0; i < quantityOfRows; i++) {
            for (int j = 0; j < listOfKeyDigit.size(); j++) {
                textDividedByCharacters[i][j] = text.charAt(counter);
                counter++;
            }
        }

        //шифрування:
        for (int i = 0; i < listOfKeyDigit.size(); i++) {
            counter = listOfKeyDigit.get(i) - 1;
            for (int j = 0; j < quantityOfRows; j++) {
                encryptText += textDividedByCharacters[j][counter];
            }
        }
        return encryptText;
    }


    public String deEncryptTextByPermutations(String text, String keyString) {
        String deEncryptText = "";
        var listOfKeyDigit = refactorKeyToListOfNumbers(keyString);
        text = HelperForLab1.addingLetterToWordIfNeed(text, listOfKeyDigit.size());
        int quantityOfRows = text.length() / listOfKeyDigit.size();
        char[][] textDividedByCharacters = new char[listOfKeyDigit.size()][quantityOfRows];

        int counter = 0;//temporary
        for (int i = 0; i < listOfKeyDigit.size(); i++) {
            for (int j = 0; j < quantityOfRows; j++) {
                textDividedByCharacters[i][j] = text.charAt(counter);
                counter++;
            }
        }

        char[][] deEncryptArray = new char[quantityOfRows][listOfKeyDigit.size()];
        for (int i = 0; i < listOfKeyDigit.size(); i++) {
            counter = listOfKeyDigit.get(i) - 1;
            for (int j = 0; j < quantityOfRows; j++) {
                deEncryptArray[j][counter] = textDividedByCharacters[i][j];
            }
        }
        for (int i = 0; i < quantityOfRows; i++) {
            for (int j = 0; j < listOfKeyDigit.size(); j++) {
                deEncryptText += deEncryptArray[i][j];
            }
        }
        return deEncryptText;
    }

    //отримання ключа і записування в лист окремі цифри
    public static List<Integer> refactorKeyToListOfNumbers(String keyString) {
        var listOfRefactorNumber = new ArrayList<Integer>();
        for (int i = 0; i < keyString.length(); i++) {
            listOfRefactorNumber.add(Integer.parseInt(String.valueOf(keyString.charAt(i))));
        }
        return listOfRefactorNumber;
    }
}
