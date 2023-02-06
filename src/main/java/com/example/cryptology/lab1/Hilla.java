package com.example.cryptology.lab1;

import com.example.cryptology.Helper.HelperForPlayFair;

import java.util.ArrayList;
import java.util.List;

import static com.example.cryptology.Helper.HelperForPlayFair.ALPHABETICAL;

public class Hilla {

    public String encryptByHill(String text, char[][] table) {
        String encryptText = "";
        text = refactorWord(text);
        Vigenere.fillingMatrix();
        List<Integer> intEncryptTextList = new ArrayList<>();
        //отримаємно числа з букв відкритого тексту
        for (int i = 0; i < text.length(); i++) {
            char element = text.charAt(i);
            for (int j = 0; j < Vigenere.linkedHashMapForVigenere.size(); j++) {
                if(Vigenere.linkedHashMapForVigenere.get(j) == element){
                    intEncryptTextList.add(j);
                }
            }
        }

        //множення:
        List<Integer> intListAfterMultiple = new ArrayList<>();
        for (int i = 0; i < intEncryptTextList.size(); i=i+3) {
            intListAfterMultiple.add(Math.floorMod(intEncryptTextList.get(i)*table[0][0]+
                    intEncryptTextList.get(i+1)*table[1][0]+intEncryptTextList.get(i+2)*table[2][0],26));
            intListAfterMultiple.add(Math.floorMod(intEncryptTextList.get(i)*table[0][1]+
                    intEncryptTextList.get(i+1)*table[1][1]+intEncryptTextList.get(i+2)*table[2][1],26));
            intListAfterMultiple.add(Math.floorMod(intEncryptTextList.get(i)*table[0][2]+
                    intEncryptTextList.get(i+1)*table[1][2]+intEncryptTextList.get(i+2)*table[2][2],26));
        }

        //записуємо зашифрований текст
        for (int i = 0; i < intListAfterMultiple.size(); i++) {
            encryptText += Vigenere.linkedHashMapForVigenere.get(intListAfterMultiple.get(i));
        }

        return encryptText;
    }

    public String refactorWord(String text) {
        String noUsedChar = HelperForPlayFair.removeUsedChars(ALPHABETICAL, text);
        char[] arrayString = text.toCharArray();
        for (int i = 0; i < arrayString.length - 1; i++) {
            if (arrayString[i] == arrayString[i + 1]) {
                text = new StringBuilder(text).insert(i + 1, noUsedChar.charAt(noUsedChar.length() - 1)).toString();
                noUsedChar = new StringBuilder(noUsedChar).deleteCharAt(noUsedChar.length() - 1).toString();
            }
        }
        while (text.length() % 3 != 0) {
            text = new StringBuilder(text).insert(text.length(), noUsedChar.charAt(0)).toString();
        }
        return text;
    }
}
