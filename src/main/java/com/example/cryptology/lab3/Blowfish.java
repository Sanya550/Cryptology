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

    public static byte[] encrypt(byte[] data, KeyParameter key) throws Exception {
        BlockCipherPadding padding = new org.bouncycastle.crypto.paddings.PKCS7Padding();
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new BlowfishEngine1(), padding);
        cipher.init(true, key);
        byte[] encryptedBytes = new byte[cipher.getOutputSize(data.length)];
        int outputLength = cipher.processBytes(data, 0, data.length, encryptedBytes, 0);
        outputLength += cipher.doFinal(encryptedBytes, outputLength);
        byte[] outputBytes = new byte[outputLength];
        System.arraycopy(encryptedBytes, 0, outputBytes, 0, outputLength);
        return outputBytes;
    }

    public static byte[] decrypt(byte[] encryptedData, KeyParameter key) throws Exception {
        BlockCipherPadding padding = new org.bouncycastle.crypto.paddings.PKCS7Padding();
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new BlowfishEngine1(), padding);
        cipher.init(false, key);
        byte[] decryptedBytes = new byte[cipher.getOutputSize(encryptedData.length)];
        int outputLength = cipher.processBytes(encryptedData, 0, encryptedData.length, decryptedBytes, 0);
        outputLength += cipher.doFinal(decryptedBytes, outputLength);
        byte[] outputBytes = new byte[outputLength];
        System.arraycopy(decryptedBytes, 0, outputBytes, 0, outputLength);
        return outputBytes;
    }
}