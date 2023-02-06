package com.example.cryptology.lab1;

import com.example.cryptology.Helper.HelperForLab1;

public class PlayFair {
    public static char matrixForEncryptionByPlayFair[][];

    public String encryptionText(String word) {
        fillingMatrixForEncryption();
        word = HelperForLab1.refactoringWord(word,2);
        String encryptWord = "";

        for (int i = 0; i < word.length(); i = i + 2) {
            int rowForFirst = 0;
            int rowForSecond = 0;
            int columnForFirst = 0;
            int columnForSecond = 0;
            char firstChar = word.charAt(i);
            char secondChar = word.charAt(i + 1);
            //записуємо індекси(наприклад[2][3]
            for (int j = 0; j < matrixForEncryptionByPlayFair.length; j++) {//стовпчик
                for (int k = 0; k < matrixForEncryptionByPlayFair.length; k++) {//рядок
                    if (matrixForEncryptionByPlayFair[j][k] == firstChar) {
                        rowForFirst = j;
                        columnForFirst = k;
                    }
                    if (matrixForEncryptionByPlayFair[j][k] == secondChar) {
                        rowForSecond = j;
                        columnForSecond = k;
                    }
                }
            }
            //розглядаємо три випадки:
            //1 if - елементи в одному рядку:
            //2 if - елементи в одному стовпчику:
            //3 прямокутник:
            if (rowForFirst == rowForSecond) {
                //перевірка якщо елемент в останньому рядку
                if (columnForFirst == matrixForEncryptionByPlayFair.length - 1) {
                    encryptWord += matrixForEncryptionByPlayFair[rowForFirst][0];
                } else {
                    encryptWord += matrixForEncryptionByPlayFair[rowForFirst][columnForFirst + 1];
                }
                if (columnForSecond == matrixForEncryptionByPlayFair.length - 1) {
                    encryptWord += matrixForEncryptionByPlayFair[rowForSecond][0];
                } else {
                    encryptWord += matrixForEncryptionByPlayFair[rowForSecond][columnForSecond + 1];
                }
            } else if (columnForFirst == columnForSecond) {
                //перевірка якщо елемент в останньому стовпчику
                if (rowForFirst == matrixForEncryptionByPlayFair.length - 1) {
                    encryptWord += matrixForEncryptionByPlayFair[0][columnForFirst];
                } else {
                    encryptWord += matrixForEncryptionByPlayFair[rowForFirst + 1][columnForFirst];
                }
                if (rowForSecond == matrixForEncryptionByPlayFair.length - 1) {
                    encryptWord += matrixForEncryptionByPlayFair[0][columnForSecond];
                } else {
                    encryptWord += matrixForEncryptionByPlayFair[rowForSecond + 1][columnForSecond];
                }
            } else {
                encryptWord += matrixForEncryptionByPlayFair[rowForFirst][columnForSecond];//1
                encryptWord += matrixForEncryptionByPlayFair[rowForSecond][columnForFirst];//2
            }
        }
        return encryptWord;
    }

    //заповнювання матрциці
    public static void fillingMatrixForEncryption() {
        matrixForEncryptionByPlayFair = new char[5][5];
        matrixForEncryptionByPlayFair[0][0] = 'c';
        matrixForEncryptionByPlayFair[0][1] = 'o';
        matrixForEncryptionByPlayFair[0][2] = 'm';
        matrixForEncryptionByPlayFair[0][3] = 'a';
        matrixForEncryptionByPlayFair[0][4] = 'n';

        matrixForEncryptionByPlayFair[1][0] = 'd';
        matrixForEncryptionByPlayFair[1][1] = 'e';
        matrixForEncryptionByPlayFair[1][2] = 'r';
        matrixForEncryptionByPlayFair[1][3] = 'b';
        matrixForEncryptionByPlayFair[1][4] = 'f';

        matrixForEncryptionByPlayFair[2][0] = 'g';
        matrixForEncryptionByPlayFair[2][1] = 'h';
        matrixForEncryptionByPlayFair[2][2] = 'i';
        matrixForEncryptionByPlayFair[2][3] = 'k';
        matrixForEncryptionByPlayFair[2][4] = 'l';

        matrixForEncryptionByPlayFair[3][0] = 'p';
        matrixForEncryptionByPlayFair[3][1] = 'q';
        matrixForEncryptionByPlayFair[3][2] = 's';
        matrixForEncryptionByPlayFair[3][3] = 't';
        matrixForEncryptionByPlayFair[3][4] = 'u';

        matrixForEncryptionByPlayFair[4][0] = 'v';
        matrixForEncryptionByPlayFair[4][1] = 'w';
        matrixForEncryptionByPlayFair[4][2] = 'x';
        matrixForEncryptionByPlayFair[4][3] = 'y';
        matrixForEncryptionByPlayFair[4][4] = 'z';
    }

}
