package com.example.cryptology.lab1;

import com.example.cryptology.Helper.HelperForLab1;

import java.util.ArrayList;
import java.util.List;

import static com.example.cryptology.Helper.HelperForLab1.fillingMatrix;

public class Hilla {

    public String encryptByHill(String text, char[][] table) {
        String encryptText = "";
        text = HelperForLab1.addingLetterToWordIfNeed(text,3);
        fillingMatrix();
        List<Integer> intEncryptTextList = new ArrayList<>();
        //отримаємно числа з букв відкритого тексту
        for (int i = 0; i < text.length(); i++) {
            char element = text.charAt(i);
            for (int j = 0; j < HelperForLab1.linkedHashMapForAlphabetical.size(); j++) {
                if(HelperForLab1.linkedHashMapForAlphabetical.get(j) == element){
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
            encryptText += HelperForLab1.linkedHashMapForAlphabetical.get(intListAfterMultiple.get(i));
        }

        return encryptText;
    }
}
