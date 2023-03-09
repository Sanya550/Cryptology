package com.example.cryptology.lab3;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.engines.BlowfishEngine;
//import org.bouncycastle.crypto.modes.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Blowfish {

    public static void main(String[] args) throws Exception {

        String originalString = "aaa";
        String secretKeyString = "mysecretkey";

        byte[] keyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        KeyParameter key = new KeyParameter(keyBytes);

        byte[] encryptedBytes = encrypt(originalString.getBytes(StandardCharsets.UTF_8), key);

        byte[] decryptedBytes = decrypt(encryptedBytes, key);

        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
        String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);

        System.out.println("Original string: " + originalString);
        System.out.println("Secret key: " + secretKeyString);
        System.out.println("Encrypted string: " + encryptedString);
        System.out.println("Decrypted string: " + decryptedString);
    }

    public static byte[] encrypt(byte[] data, KeyParameter key) throws Exception {
        // Создаем объект PKCS7Padding для дополнения входных данных.
        BlockCipherPadding padding = new org.bouncycastle.crypto.paddings.PKCS7Padding();
        // Создаем буферизированный блочный шифр с использованием движка Blowfish и объекта дополнения.
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new BlowfishEngine1(), padding);
        // Инициализируем шифр ключом для шифрования.
        cipher.init(true, key);
        // Создаем байтовый массив для хранения зашифрованных данных.
        byte[] encryptedBytes = new byte[cipher.getOutputSize(data.length)];
        // Вычисляем длину выходных данных после обработки входных данных.
        int outputLength = cipher.processBytes(data, 0, data.length, encryptedBytes, 0);
        // Добавляем дополнительные данные, если такие имеются, и завершаем шифрование.
        outputLength += cipher.doFinal(encryptedBytes, outputLength);
        // Создаем байтовый массив нужной длины и копируем в него зашифрованные данные.
        byte[] outputBytes = new byte[outputLength];
        System.arraycopy(encryptedBytes, 0, outputBytes, 0, outputLength);
        // Возвращаем зашифрованные данные.
        return outputBytes;
    }

    public static byte[] decrypt(byte[] encryptedData, KeyParameter key) throws Exception {
        // Создаем объект PKCS7Padding для дополнения входных данных.
        BlockCipherPadding padding = new org.bouncycastle.crypto.paddings.PKCS7Padding();
        // Создаем буферизированный блочный шифр с использованием движка Blowfish и объекта дополнения.
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new BlowfishEngine1(), padding);
        // Инициализируем шифр ключом для расшифровки.
        cipher.init(false, key);
        // Создаем байтовый массив для хранения расшифрованных данных.
        byte[] decryptedBytes = new byte[cipher.getOutputSize(encryptedData.length)];
        // Вычисляем длину выходных данных после обработки входных данных.
        int outputLength = cipher.processBytes(encryptedData, 0, encryptedData.length, decryptedBytes, 0);
        // Добавляем дополнительные данные, если такие имеются, и завершаем расшифровку.
        outputLength += cipher.doFinal(decryptedBytes, outputLength);
        // Создаем байтовый массив нужной длины и копируем в него расшифрованные данные.
        byte[] outputBytes = new byte[outputLength];
        System.arraycopy(decryptedBytes, 0, outputBytes, 0, outputLength);
        // Возвращаем расшифрованные данные.
        return outputBytes;
    }
}