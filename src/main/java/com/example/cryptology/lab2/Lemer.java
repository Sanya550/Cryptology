package com.example.cryptology.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lemer {
    public List<Integer> generateListOfDigit(int quantity, int m, int a, int c, int x0) {
        var list = new ArrayList<Integer>();
        list.add(x0);
        Random random = new Random();
        for (int i = 0; i < quantity - 1; i++) {
            x0 = Math.floorMod(Math.floorMod(System.currentTimeMillis(), random.nextInt(11) + 10)+ a * x0 + c, m);
            list.add(x0);
        }
        return list;
    }

    public double findPeriod(List<Integer> list) {
        int period = 1;
        int firstNumber = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == firstNumber) {
                break;
            }
            period++;
        }
        return period;
    }
}
