package com.example.cryptology.lab2;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class BBS {
    public List<Integer> generateListOfDigit() {
        Random random = new Random();
        int p = generatePrimeNumber(random, 16, 100);
        int q = generatePrimeNumber(random, 16, 100);
        int quantity = generatePrimeNumber(random, 10000, 20000);
        int s = random.nextInt(p * q); // Генерация случайного числа s
        var list = new ArrayList<Integer>();
        long n = p * q;
        int x0 = Math.toIntExact(Math.floorMod((long) Math.pow(s, 2), n));
        list.add(x0);
        for (int i = 0; i < quantity - 1; i++) {
            x0 = Math.toIntExact(Math.floorMod(Math.floorMod(System.currentTimeMillis(), random.nextInt(11) + 10) + (long) Math.pow(x0, 2), n));
            list.add(x0);
        }
        int randomInt = random.nextInt(3)+7;
        var result = new ArrayList<Integer>();
        for (int i = 0; i < randomInt; i++) {
            result.add(list.get(i));
        }
        return result;
    }

    public String getTextViaBBS(List<Integer> list){
        Map<Integer, Character> alphabetMap = createAlphabetMap();
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            int num = Math.floorMod(list.get(i), 25);
            str += String.format("%s",alphabetMap.get(num));
        }
        return str;
    }


    public static Map<Integer, Character> createAlphabetMap() {
        Map<Integer, Character> alphabetMap = new HashMap<>();

        int asciiValue = 97;
        for (int i = 0; i < 26; i++) {
            char letter = (char) (asciiValue + i);
            alphabetMap.put(i, letter);
        }

        return alphabetMap;
    }

    public static int generatePrimeNumber(Random random, int min, int max) {
        int number = random.nextInt(max - min + 1) + min;

        while (!isPrime(number)) {
            number = random.nextInt(max - min + 1) + min;
        }

        return number;
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
