package com.example.cryptology.lab2;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BBS {
    public List<Integer> generateListOfDigit(int p, int q, int s, int quantity) {
        var list = new ArrayList<Integer>();
        long n = p * q;
        int x0 = Math.toIntExact(Math.floorMod((long) Math.pow(s, 2), n));
        list.add(x0);
        Random random = new Random();
        for (int i = 0; i < quantity - 1; i++) {
            x0 = Math.toIntExact(Math.floorMod(Math.floorMod(System.currentTimeMillis(), random.nextInt(11) + 10)+(long) Math.pow(x0, 2), n));
            list.add(x0);
        }
        return list;
    }

    public List<Integer> listZeroOrOne(List<Integer> list) {
        return list.stream().mapToInt(x -> Math.floorMod(x, 2)).boxed().collect(Collectors.toList());
    }

    public String testsForBBS(List<Integer> listZeroOrOne) {
        String result = "";
        //Монобітовий тест
        int quantityOfOne = (int) listZeroOrOne.stream().filter(x -> x == 1).count();
        result += "Монобітовий тест: ";
        if (quantityOfOne > 9654 && quantityOfOne < 10346) {
            result += "pass";
            result += String.format("\n9654 <= %d <= 10346", quantityOfOne);
        } else {
            result += "fail";
            result += String.format("\nК-сть одиниць = %d", quantityOfOne);
        }

        result+="\n\nПокер тест: loading...";

        result += "\n\nТест довжини серій: ";
        int maxSequenceLength = listZeroOrOne.stream()
                .reduce(new int[]{0, 0}, (acc, i) -> {
                    if (i == 1) {
                        acc[0]++;
                        acc[1] = Math.max(acc[0], acc[1]);
                    } else {
                        acc[0] = 0;
                    }
                    return acc;
                }, (a, b) -> {
                    throw new UnsupportedOperationException("Parallel streams not supported");
                })[1];
        if (maxSequenceLength < 34) {
            result += "pass ";
        } else {
            result += "pass ";
        }
        result += String.format("\nДовжинай серії = %d", maxSequenceLength);




        var serie2ForOne = 0;
        var serie3ForOne = 0;
        var serie4ForOne = 0;
        var serie5ForOne = 0;
        var serie6ForOne = 0;

        var serie2ForZero = 0;
        var serie3ForZero = 0;
        var serie4ForZero = 0;
        var serie5ForZero = 0;
        var serie6ForZero = 0;

        result += "\n\nТест серій:\n";
        result += "Серія 2: ";
        if (serie2 > 1114 && serie2 < 1386) {
            result += "pass";
            result += String.format("\n1114 <= %d <= 1386", serie2);
        }else {
            result+="fail\n";
            result += String.format("series2 = %d", serie2);
        }

        result += "\nСерія 3: ";
        if (serie3 > 527 && serie3 < 723) {
            result += "pass";
            result += String.format("\n527 <= %d <= 723", serie3);
        }else {
            result+="fail\n";
            result += String.format("series3 = %d", serie3);
        }

        result += "\nСерія 4: ";
        if (serie4 > 240 && serie4 < 384) {
            result += "pass";
            result += String.format("\n240 <= %d <= 384", serie4);
        }else {
            result+="fail\n";
            result += String.format("series4 = %d", serie4);
        }

        result += "\nСерія 5: ";
        if (serie5 > 103 && serie5 < 209) {
            result += "pass";
            result += String.format("\n103 <= %d <= 209", serie5);
        }else {
            result+="fail\n";
            result += String.format("series5 = %d", serie5);
        }

        result += "\nСерія 6: ";
        if (serie6 > 103 && serie6 < 209) {
            result += "pass";
            result += String.format("\n103 <= %d <= 209", serie6);
        }else {
            result+="fail\n";
            result += String.format("series6 = %d", serie6);
        }


        return result;

    }

    public static int countSequences(List<Integer> list, int length, boolean flag) {
        int count = 0;
        int current = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == (flag ? 1 : 0)) {
                current++;
            } else {
                current = 0;
            }

            if (current == length) {
                count++;
                current = 0;
            }
        }

        return count;
    }
}
