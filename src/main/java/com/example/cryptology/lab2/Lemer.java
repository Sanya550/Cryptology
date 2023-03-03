package com.example.cryptology.lab2;

import java.util.ArrayList;
import java.util.List;

public class Lemer {
    public List<Integer> generateListOfDigit(int quantity, int m, int a, int c, int x0) {
        var list = new ArrayList<Integer>();
        list.add(x0);
        for (int i = 0; i < quantity - 1; i++) {
            x0 = Math.floorMod(a * x0 + c, m);
            list.add(x0);
        }
        return list;
    }
}
