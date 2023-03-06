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
            x0 = Math.toIntExact(Math.floorMod(Math.floorMod(System.currentTimeMillis(), random.nextInt(11) + 10) + (long) Math.pow(x0, 2), n));
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

        result += "\n\nПокер тест: ";
        double x = pokerTest(listZeroOrOne);
        if (x>1.03 && x<57.4){
            result += "pass ";
            result += String.format("\n1.03 <= %.2f <= 57.4", x);
        }else {
            result += "fail";
            result += String.format("\nX = %.2f", x);
        }

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
        result += testSeries(listZeroOrOne);
        return result;
    }

    public String testSeries(List<Integer> list) {
        String result = "";
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

        List<Integer> zeroList = methodForGettingListForBBSTest(list, 0);
        List<Integer> oneList = methodForGettingListForBBSTest(list, 1);
        for (int i = 0; i < zeroList.size(); i++) {
            if (zeroList.get(i) == 2) {
                serie2ForZero++;
            } else if (zeroList.get(i) == 3) {
                serie3ForZero++;
            } else if (zeroList.get(i) == 4) {
                serie4ForZero++;
            } else if (zeroList.get(i) == 5) {
                serie5ForZero++;
            } else if (zeroList.get(i) >= 6) {
                serie6ForZero++;
            }
        }

        for (int i = 0; i < oneList.size(); i++) {
            if (oneList.get(i) == 2) {
                serie2ForOne++;
            } else if (oneList.get(i) == 3) {
                serie3ForOne++;
            } else if (oneList.get(i) == 4) {
                serie4ForOne++;
            } else if (oneList.get(i) == 5) {
                serie5ForOne++;
            } else if (oneList.get(i) >= 6) {
                serie6ForOne++;
            }
        }

        result += "\n\nТест серій:\n";
        result += "Серія 2: ";
        result += "\nДля 0: ";
        if (serie2ForZero > 1114 && serie2ForZero < 1386) {
            result += "pass";
            result += String.format("  1114 <= %d <= 1386", serie2ForZero);
        } else {
            result += "fail";
            result += String.format(" series2 = %d", serie2ForZero);
        }
        result += "\nДля 1: ";
        if (serie2ForOne > 1114 && serie2ForOne < 1386) {
            result += "pass";
            result += String.format("  1114 <= %d <= 1386", serie2ForOne);
        } else {
            result += "fail";
            result += String.format(" series2 = %d", serie2ForOne);
        }

        result += "\nСерія 3: ";
        result += "\nДля 0: ";
        if (serie3ForZero > 527 && serie3ForZero < 723) {
            result += "pass";
            result += String.format("  527 <= %d <= 723", serie3ForZero);
        } else {
            result += "fail";
            result += String.format(" series3 = %d", serie3ForZero);
        }
        result += "\nДля 1: ";
        if (serie3ForOne > 527 && serie3ForOne < 723) {
            result += "pass";
            result += String.format("  527 <= %d <= 723", serie3ForOne);
        } else {
            result += "fail";
            result += String.format(" series3 = %d", serie3ForOne);
        }

        result += "\nСерія 4: ";
        result += "\nДля 0: ";
        if (serie4ForZero > 240 && serie4ForZero < 384) {
            result += "pass";
            result += String.format("  240 <= %d <= 384", serie4ForZero);
        } else {
            result += "fail";
            result += String.format(" series4 = %d", serie4ForZero);
        }
        result += "\nДля 1: ";
        if (serie4ForOne > 240 && serie4ForOne < 384) {
            result += "pass";
            result += String.format("  240 <= %d <= 384", serie4ForOne);
        } else {
            result += "fail";
            result += String.format(" series4 = %d", serie4ForOne);
        }

        result += "\nСерія 5: ";
        result += "\nДля 0: ";
        if (serie5ForZero > 103 && serie5ForZero < 209) {
            result += "pass";
            result += String.format("  103 <= %d <= 209", serie5ForZero);
        } else {
            result += "fail";
            result += String.format(" series5 = %d", serie5ForZero);
        }
        result += "\nДля 1: ";
        if (serie5ForOne > 103 && serie5ForOne < 209) {
            result += "pass";
            result += String.format("  103 <= %d <= 209", serie5ForOne);
        } else {
            result += "fail";
            result += String.format(" series5 = %d", serie5ForOne);
        }

        result += "\nСерія 6: ";
        result += "\nДля 0: ";
        if (serie6ForZero > 103 && serie6ForZero < 209) {
            result += "pass";
            result += String.format("  103 <= %d <= 209", serie6ForZero);
        } else {
            result += "fail";
            result += String.format(" series6 = %d", serie6ForZero);
        }
        result += "\nДля 1: ";
        if (serie6ForOne > 103 && serie6ForOne < 209) {
            result += "pass";
            result += String.format("  103 <= %d <= 209", serie6ForOne);
        } else {
            result += "fail";
            result += String.format(" series6 = %d", serie6ForOne);
        }
        return result;
    }

    public List<Integer> methodForGettingListForBBSTest(List<Integer> inputList, int value) {
        List<Integer> outputList = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i) == value) {
                count++;
            } else if (count > 0) {
                outputList.add(count);
                count = 0;
            }
        }

        if (count > 0) {
            outputList.add(count);
        }
        return outputList;
    }

    public double pokerTest(List<Integer> list) {
        String str0000 = "0000";
        String str0001 = "0001";
        String str0010 = "0010";
        String str0011 = "0011";
        String str0100 = "0100";
        String str0101 = "0101";
        String str0110 = "0110";
        String str0111 = "0111";
        String str1000 = "1000";
        String str1001 = "1001";
        String str1010 = "1010";
        String str1011 = "1011";
        String str1100 = "1100";
        String str1101 = "1101";
        String str1110 = "1110";
        String str1111 = "1111";

        int int0000 = 0;
        int int0001 = 0;
        int int0010 = 0;
        int int0011 = 0;
        int int0100 = 0;
        int int0101 = 0;
        int int0110 = 0;
        int int0111 = 0;
        int int1000 = 0;
        int int1001 = 0;
        int int1010 = 0;
        int int1011 = 0;
        int int1100 = 0;
        int int1101 = 0;
        int int1110 = 0;
        int int1111 = 0;
        for (int i = 0; i < list.size(); i=i+4) {
            String temp = String.format("%d%d%d%d",list.get(i),list.get(i+1),list.get(i+2),list.get(i+3));
            if(temp.equals(str0000)){
                int0000++;
            }else if(temp.equals( str0001)){
                int0001++;
            }else if(temp .equals( str0010)){
                int0010++;
            }else if(temp .equals( str0011)){
                int0011++;
            }else if(temp .equals( str0100)){
                int0100++;
            }

            else if(temp .equals( str0101)){
                int0101++;
            }else if(temp .equals( str0110)){
                int0110++;
            }else if(temp .equals( str0111)){
                int0111++;
            }else if(temp .equals( str1000)){
                int1000++;
            }else if(temp .equals( str1001)){
                int1001++;
            }

            else if(temp .equals( str1010)){
                int1010++;
            }else if(temp .equals( str1011)){
                int1011++;
            }else if(temp .equals( str1100)){
                int1100++;
            }else if(temp .equals( str1101)){
                int1101++;
            }else if(temp .equals( str1110)){
                int1110++;
            }else if(temp .equals( str1111)){
                int1111++;
            }
        }
        List l1 = List.of(int0000,int0001,int0010,int0011,int0100,
                int0101,int0110,int0111,int1000,int1001,
                int1010,int1011,int1100,int1101,int1110,int1111);

        int res = l1.stream().mapToInt(x-> (int) x*(int)x).sum();
        double x = 16*res/5000 -5000;
        return x;
    }
}
