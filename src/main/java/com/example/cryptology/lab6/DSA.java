package com.example.cryptology.lab6;

import com.example.cryptology.lab4.MD5;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Random;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DSA {

    private static BigInteger p;
    private static BigInteger q;
    private static BigInteger g;
    private static BigInteger h;
    private static BigInteger secretKey;
    private static BigInteger openKey;

    private static byte[] getHash(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(message.getBytes());
    }

    public static void generateSignature(TextField parameterP, TextField parameterG,TextField parameterQ, TextField secret, TextField open, TextField hashField, TextField rField, TextField sField, TextField message) throws NoSuchAlgorithmException {
        if (message.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Text is empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            setParameters();
            secretKey = new BigInteger(q.bitLength(), new SecureRandom()).add(BigInteger.ONE).mod(q);
            openKey = g.modPow(secretKey, p);
//            byte[] hash = getHash(message.getText());
            byte[] hash = MD5.generateMD5Int(message.getText());
            BigInteger h = new BigInteger(1, hash);

            BigInteger k;
            do {
                k = new BigInteger(q.bitLength(), new SecureRandom()).mod(q);
            } while (k.equals(BigInteger.ZERO));

            BigInteger r = g.modPow(k, p).mod(q);

            BigInteger x = secretKey;
            BigInteger kInv = k.modInverse(q);
            BigInteger s = kInv.multiply(h.add(x.multiply(r))).mod(q);

            parameterP.setText(p.toString());
            parameterG.setText(g.toString());
            parameterQ.setText(q.toString());
            open.setText(openKey.toString());
            secret.setText(secretKey.toString());
            hashField.setText(h.toString());
            rField.setText(r.toString());
            sField.setText(s.toString());
        }
    }

    public static boolean verifySignature(TextField openKey, TextField rField, TextField sField, TextField message) throws NoSuchAlgorithmException {
        byte[] hash = MD5.generateMD5Int(message.getText());
        BigInteger h = new BigInteger(1, hash);

        BigInteger r = new BigInteger(rField.getText());
        BigInteger s = new BigInteger(sField.getText());

        BigInteger w = s.modInverse(q);

        BigInteger u1 = h.multiply(w).mod(q);
        BigInteger u2 = r.multiply(w).mod(q);

        BigInteger open = new BigInteger(openKey.getText());

        BigInteger v = g.modPow(u1, p).multiply(open.modPow(u2, p)).mod(p).mod(q);
        return v.equals(r);
    }

    public static void setParameters() {
        while (true) {
            SecureRandom random = new SecureRandom();
            Random rnd = new Random();
            int qInt = generatePrime(rnd);
            int pInt = 2 * qInt + 1;
            int counter = 0;
            while (!isPrime(pInt) || (pInt - 1) % qInt != 0) {
                if(counter == 50){
                    pInt = 7;
                    qInt = 3;
                    break;
                }
                qInt = generatePrime(rnd);
                pInt = 2 * qInt + 1;
                counter++;
            }
            p = new BigInteger(String.valueOf(pInt));
            q = new BigInteger(String.valueOf(qInt));
            System.out.println("p = " + p);
            System.out.println("q = " + q);

            h = new BigInteger(p.subtract(BigInteger.ONE).subtract(BigInteger.ONE).bitLength(), random).add(BigInteger.ONE);
            g = h.modPow(p.subtract(BigInteger.ONE).divide(q), p);
            if (g.doubleValue() > 1) {
                break;
            }
        }
    }

    private static int generatePrime(Random rnd) {
        int num;
        do {
            num = rnd.nextInt(Integer.MAX_VALUE);
        } while (!isPrime(num));
        return num;
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        if (num == 2 || num == 3) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        int d = num - 1;
        int s = 0;
        while (d % 2 == 0) {
            d /= 2;
            s++;
        }
        for (int i = 0; i < 10; i++) {
            int a = (int) (Math.random() * (num - 3)) + 2;
            int x = powMod(a, d, num);
            if (x == 1 || x == num - 1) {
                continue;
            }
            boolean isProbablePrime = false;
            for (int r = 1; r < s; r++) {
                x = powMod(x, 2, num);
                if (x == num - 1) {
                    isProbablePrime = true;
                    break;
                }
            }
            if (!isProbablePrime) {
                return false;
            }
        }
        return true;
    }

    private static int powMod(int a, int b, int m) {
        int res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (int) ((long) res * a % m);
            }
            a = (int) ((long) a * a % m);
            b >>= 1;
        }
        return res;
    }

}