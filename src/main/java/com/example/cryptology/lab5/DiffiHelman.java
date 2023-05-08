package com.example.cryptology.lab5;

import javafx.scene.control.TextField;
import javax.swing.*;

public class DiffiHelman {

    public void calculationKeys(int n, int g, int xa, int xb, TextField openAlisa, TextField openBob, TextField calcAlisa, TextField calcBob) {
        if(xa >= n || xb >= n) {
            JOptionPane.showMessageDialog(null, "Error! Числа Алисы и Боба должны быть меньше p");
        } else {
            int ya = modPow(g, xa, n);
            int yb = modPow(g, xb, n);
            openAlisa.setText(String.valueOf(ya));
            openBob.setText(String.valueOf(yb));
            calcAlisa.setText(String.valueOf(modPow(yb, xa, n)));
            calcBob.setText(String.valueOf(modPow(ya, xb, n)));
        }
    }

    private int modPow(int base, int exponent, int modulus) {
        int result = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent >>= 1;
        }
        return result;
    }
}