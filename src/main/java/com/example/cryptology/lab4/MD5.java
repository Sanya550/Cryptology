package com.example.cryptology.lab4;

public class MD5 {
    //ініціалізація буфера
    private static final int A = 0x67452301;
    private static final int B = 0xEFCDAB89;
    private static final int C = 0x98BADCFE;
    private static final int D = 0x10325476;

    //AND, OR, NOT, XOR
    private static int F(int X, int Y, int Z) {
        return (X & Y) | (~X & Z);
    }

    private static int G(int X, int Y, int Z) {
        return (X & Z) | (Y & ~Z);
    }

    private static int H(int X, int Y, int Z) {
        return X ^ Y ^ Z;
    }

    private static int I(int X, int Y, int Z) {
        return Y ^ (X | ~Z);
    }


    private static byte[] padMessage(byte[] message) {
        int messageLength = message.length;
        int paddingLength = (messageLength % 64 < 56) ? 64 - (messageLength % 64) : 128 - (messageLength % 64);
        byte[] paddedMessage = new byte[messageLength + paddingLength + 8];
        System.arraycopy(message, 0, paddedMessage, 0, messageLength);
        paddedMessage[messageLength] = (byte) 0x80;//одиничний байт
        long lengthInBits = messageLength * 8;
        for (int i = 0; i < 8; i++) {
            paddedMessage[messageLength + paddingLength + i] = (byte) ((lengthInBits >> (8 * i)) & 0xFF);
        }
        return paddedMessage;
    }

    private static int[] md5Algorithm(byte[] message) {
        int messageLength = message.length;
        int paddedLength = (messageLength + 8) / 64 * 64 + 64;
        byte[] paddedMessage = new byte[paddedLength];
        System.arraycopy(message, 0, paddedMessage, 0, messageLength);
        paddedMessage[messageLength] = (byte) 0x80;
        long bitLength = messageLength * 8L;
        for (int i = 0; i < 8; i++) {
            paddedMessage[paddedLength - 8 + i] = (byte) (bitLength >>> (i * 8));
        }

        //білий шум
        int[] T = new int[64];
        for (int i = 0; i < 64; i++) {
            T[i] = (int) (Math.abs(Math.sin(i + 1)) * (1L << 32));
        }

        int[] X = new int[16];
        int[] buffer = new int[]{A, B, C, D};
        for (int i = 0; i < message.length; i += 64) {
            for (int j = 0; j < 16; j++) {
                int index = j * 4;
                X[j] = ((message[index] & 0xFF)) | ((message[index + 1] & 0xFF) << 8) |
                        ((message[index + 2] & 0xFF) << 16) | ((message[index + 3] & 0xFF) << 24);
            }

            int AA = buffer[0];
            int BB = buffer[1];
            int CC = buffer[2];
            int DD = buffer[3];

            // Round 1
            for (int j = 0; j < 16; j++) {
                int temp = AA + F(BB, CC, DD) + X[j] + T[j];
                AA = DD;
                DD = CC;
                CC = BB;
                BB = BB + Integer.rotateLeft(temp, 7);
            }

            // Round 2
            for (int j = 0; j < 16; j++) {
                int index = j * 5 + 1;
                if (index >= X.length){
                    index = 0;
                }
                int temp = DD + G(AA, BB, CC) + X[index] + T[j + 16];
                DD = CC;
                CC = BB;
                BB = BB + Integer.rotateLeft(temp, 12);
                AA = temp + Integer.rotateLeft(AA, 9);
            }

            // Round 3
            for (int j = 0; j < 16; j++) {
                int index = j * 3 + 5;
                if (index >= X.length){
                    index = 0;
                }
                int temp = DD + H(AA, BB, CC) + X[index] + T[j + 32];
                DD = CC;
                CC = BB;
                BB = BB + Integer.rotateLeft(temp, 17);
                AA = temp + Integer.rotateLeft(AA, 11);
            }
            // Round 4
            for (int j = 0; j < 16; j++) {
                int index = j * 7;
                if (index >= X.length){
                    index = 0;
                }
                int temp = DD + I(AA, BB, CC) + X[index] + T[j + 48];
                DD = CC;
                CC = BB;
                BB = BB + Integer.rotateLeft(temp, 22);
                AA = temp + Integer.rotateLeft(AA, 15);
            }
            buffer[0] += AA;
            buffer[1] += BB;
            buffer[2] += CC;
            buffer[3] += DD;
        }

        return buffer;
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String generateMD5(String input) {
        try {
            byte[] message = input.getBytes("UTF-8");
            byte[] paddedMessage = padMessage(message);
            int[] hashArray = md5Algorithm(paddedMessage);
            byte[] hashBytes = new byte[hashArray.length * 4];
            for (int i = 0; i < hashArray.length; i++) {
                hashBytes[i * 4] = (byte) (hashArray[i] & 0xFF);
                hashBytes[i * 4 + 1] = (byte) ((hashArray[i] >> 8) & 0xFF);
                hashBytes[i * 4 + 2] = (byte) ((hashArray[i] >> 16) & 0xFF);
                hashBytes[i * 4 + 3] = (byte) ((hashArray[i] >> 24) & 0xFF);
            }
            return toHexString(hashBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] generateMD5Int(String input) {
        try {
            byte[] message = input.getBytes("UTF-8");
            byte[] paddedMessage = padMessage(message);
            int[] hashArray = md5Algorithm(paddedMessage);
            byte[] hashBytes = new byte[hashArray.length * 4];
            for (int i = 0; i < hashArray.length; i++) {
                hashBytes[i * 4] = (byte) (hashArray[i] & 0xFF);
                hashBytes[i * 4 + 1] = (byte) ((hashArray[i] >> 8) & 0xFF);
                hashBytes[i * 4 + 2] = (byte) ((hashArray[i] >> 16) & 0xFF);
                hashBytes[i * 4 + 3] = (byte) ((hashArray[i] >> 24) & 0xFF);
            }
            return hashBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}